package com.mchale.investmentapp.spring.rest.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mchale.investmentapp.rest.model.csv.DataRow;
import com.mchale.investmentapp.rest.model.csv.DataSheet;
import com.mchale.investmentapp.spring.data.datadynamodb.model.Account;
import com.mchale.investmentapp.spring.data.datadynamodb.model.StockMarketAsset;
import com.mchale.investmentapp.spring.data.datadynamodb.model.StockMarketTransaction;
import com.mchale.investmentapp.spring.data.datadynamodb.repositories.AccountRepository;
import com.mchale.investmentapp.spring.data.datadynamodb.repositories.StockMarketAssetRepository;
import com.mchale.investmentapp.spring.data.datadynamodb.repositories.StockMarketTransactionRepository;
import com.mchale.investmentapp.util.CSVUtil;
import com.mchale.investmentapp.util.DateUtil;


@RestController
public class StockMarketTransactionService {

	@Autowired
	private StockMarketTransactionRepository repository;
	
	@Autowired
	private AccountService accountService;

	@Autowired
	private StockMarketAssetService assetService;
	
	public void insertTransactions(List<StockMarketTransaction> transactions) {
		
		repository.save(transactions); 
	}
	
	public void loadFidelityCSV(int userId, MultipartFile file, int accountId) {
		List<String[]> csvSheet = CSVUtil.readCSV(file);
		List<StockMarketTransaction> txList = readFidelityCSVSheet(csvSheet, userId, accountId);
		this.insertTransactions(txList);
	}
	

	public void loadCapitalOneCSV(int userId, int accountId, MultipartFile file) {
		List<String[]> csvSheet = CSVUtil.readCSV(file);
		List<StockMarketTransaction> txList = readCapitalOneCSVSheet(csvSheet, userId, accountId);
		this.insertTransactions(txList);
	}
	
	private long generateBatchID() {
		return DateTime.now().getMillis();
	}
	
	public void loadTransactions(int userId, int accountId, MultipartFile file) {
		Account account = this.accountService.retrieveAccountById(accountId); 
		if (account==null)
			throw new IllegalStateException("couldn't find account by id " + accountId); 
		
		int accountUserId = account.getUserId();
		
//		if (accountUserId != userId) {
//			throw new IllegalAccessError("user: " + userId + " " + " accountId: " + accountId); 
//		}

		String institution = account.getInstitution();
		if (StringUtils.equals(institution, "Fidelity")) {
			this.loadFidelityCSV(userId, file, accountId);   
		}
		else {
			this.loadCapitalOneCSV(userId, accountId, file); 			
		}
		
		
	}	
	
    public List<StockMarketTransaction> readCapitalOneCSVSheet(List<String[]> csvSheet, int userId, int accountId) {
		List<StockMarketTransaction> boList = new ArrayList<>(); 
		
        Account accountBo = accountService.retrieveAccountById(accountId);
         
        DataSheet dataSheet = CSVUtil.read(csvSheet);
        List<DataRow> rows = dataSheet.getSpreadsheet();

        for (DataRow row : rows) {			
        	
            try
            {
                String strDate = row.getStringValue("Date");
                String strInvestment = row.getStringValue("Security");
                String strTransactionType = row.getStringValue("Action");
                String strAmount = row.getStringValue("Amount");
                String strShares = row.getStringValue("Quantity");
                String strMemo = row.getStringValue("Memo");
                String strCommission = row.getStringValue("Commission");

              System.out.println("strDate: " + strDate 
        		+ " strInvestment: " + strInvestment
        		+ " strTransactionType: " + strTransactionType
        		+ " strAmount: " + strAmount
        		+ " strShares: " + strShares
        		+ " strMemo: " + strMemo
        		+ " strCommission: " + strCommission

            		  );
                
                StockMarketTransaction bo = new StockMarketTransaction();

                String cleanedInvestment = BrokerageCSVUtil.cleanCapitalOneInvestment(strInvestment);
                StockMarketAsset assetBO = assetService.retrieveAssetBySymbol(cleanedInvestment);
                
                if (assetBO != null)
                	bo.setSymbol(assetBO.getSymbol()); 
                
                bo.setAssetRaw(strInvestment);
                bo.setAccountId(accountBo.getAccountId());
                bo.setType("StockMarket");
                DateTime txDate = DateUtil.getDateFromMonthDayYear(strDate);
                bo.setTxDate(txDate.getMillis());
                bo.setTxDateISO(txDate.toDate()); 
                
                if (strShares !=null && !strShares.trim().equals(""))
                    bo.setShares(Double.parseDouble(strShares));


                double amount = Double.parseDouble(strAmount);
				bo.setAmount(amount);
                bo.setMemo(strMemo);

                if (strCommission != null && !strCommission.trim().equals(""))
                    bo.setFees(Double.parseDouble(strCommission));

                if (bo.getShares() !=null && bo.getShares() > 0) {
                    bo.setPrice(amount / bo.getShares());
                }
                
                BrokerageCSVUtil.rewriteCapitalOneTransactionType(bo);
                boList.add(bo);
            }
            catch (RuntimeException e)
            {
                e.printStackTrace();
                throw e;
            }
        }
		
		return boList;
    }
	
	public List<StockMarketTransaction> readFidelityCSVSheet(List<String[]> csvSheet, int userId, int accountId) {
		
		List<StockMarketTransaction> boList = new ArrayList<>(); 
        int planRowNum = CSVUtil.findRowWithValue(csvSheet, 0, "Plan name:");
        
        if (planRowNum == -1) {
        	throw new IllegalStateException("CSV doesn't match Fidelity Format");
        }
        
        String accountName = csvSheet.get(planRowNum)[1];
        
        int headerRowNum = CSVUtil.findRowWithValue(csvSheet, 0, "Date");
                
        Account account = accountService.retrieveAccountById(accountId); 
        //TODO: add validation and exception thrown if account is not found. 
        
        List<String[]> shortenedSheet = csvSheet.subList(headerRowNum, csvSheet.size());
        DataSheet dataSheet = CSVUtil.read(shortenedSheet);
        
        List<DataRow> rows = dataSheet.getSpreadsheet();
        long batchId = generateBatchID();
        for (DataRow row : rows) {
			
            try
            {
                String strDate = row.getStringValue("Date");
                
                String strInvestment = row.getStringValue("Investment");
                String strTransactionType = row.getStringValue("Transaction Type");
                String strAmount = row.getStringValue("Amount");
                String strShares = row.getStringValue("Shares/Unit");
//                System.out.println("strDate: " + strDate 
//                		+ " strInvestment: " + strInvestment
//                		+ " strTransactionType: " + strTransactionType
//                		+ " strAmount: " + strAmount
//                		+ " strShares: " + strShares);
                
                double amount = 0;
                strAmount = StringUtils.replace(strAmount, ",", "");
                strShares = StringUtils.replace(strShares, ",", "");
                
                amount = Double.parseDouble(strAmount);

                StockMarketTransaction bo = new StockMarketTransaction();
                StockMarketAsset asset = assetService.retrieveAssetByShortName(strInvestment);
                bo.setBatchId(batchId); 
                bo.setSymbol(asset.getSymbol());
                bo.setAccountId(account.getAccountId());
                bo.setType("StockMarket");
                DateTime txDate = DateUtil.getDateFromMonthDayYear(strDate);
				bo.setTxDate(txDate.getMillis());
				bo.setTxDateISO(txDate.toDate()); 
				
                bo.setShares(Double.parseDouble(strShares));
                
                bo.setAmount(-amount);

                if (bo.getShares() > 0) {
                    double price = amount / bo.getShares();
                }
                bo.setActionRaw(strTransactionType);
                BrokerageCSVUtil.rewriteFidelityTransactionType(bo); 
                boList.add(bo);
            }
            catch(RuntimeException e) {
            	e.printStackTrace();
            	throw e;
            }
            finally {}
        }
		return boList;
	}

	
	public List<StockMarketTransaction> retrieveTransactionsAfterDate(int userId, int accountId, DateTime date) {
		List<StockMarketTransaction> txs = null;
		txs = this.repository.findByAccountIdAndTxDateGreaterThan(accountId, date.getMillis());
		if (txs == null)
			txs = new ArrayList<>();
		
		return txs;
	}


	
}
