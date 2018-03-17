package com.mchale.investmentapp.spring.rest.services;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.codec.binary.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mchale.investmentapp.rest.model.AccountSummary;
import com.mchale.investmentapp.rest.model.HoldingSummary;
import com.mchale.investmentapp.spring.data.datadynamodb.model.Account;
import com.mchale.investmentapp.spring.data.datadynamodb.model.StockMarketAsset;
import com.mchale.investmentapp.spring.data.datadynamodb.model.StockMarketTransaction;
import com.mchale.investmentapp.util.DateUtil;

@Component
public class AccountAggregationService {

	@Autowired
	private StockMarketTransactionService transactionService;
	
	@Autowired
	private StockMarketAssetService assetService;
	
	@Autowired
	private AccountService accountService; 

	
	public AccountSummary calculateSummary(int userId, int accountId) {
		Account account = this.accountService.retrieveAccountById(accountId);
		
		AccountSummary summary = new AccountSummary();
		summary.setAccount(account);
		
		List<StockMarketTransaction> txs  = this.transactionService.retrieveTransactionsAfterDate(userId, accountId, DateUtil.epoch());
		
		
		Map<String, List<StockMarketTransaction>> groupedTransactions = 
				txs
					.stream()
					.filter(p -> p.getSymbol() != null)
					.collect(
						Collectors.groupingBy(
								StockMarketTransaction::getSymbol));

		
		Set<String> symbolSet = groupedTransactions.keySet();
		List<HoldingSummary> summaryList = new ArrayList<>();
		
		for (String symbol : symbolSet) {
			List<StockMarketTransaction> transactions = groupedTransactions.get(symbol);

/*			if (StringUtils.equals(symbol,"FSHBX")) {
				for (StockMarketTransaction tx : transactions) {
					System.out.print(DateUtil.formatYearMonthDay(new DateTime(tx.getTxDate())));
					System.out.print("\t");
					System.out.print(symbol);
					System.out.print("\t");
					System.out.print(tx.getActionRaw());
					System.out.print("\t");
					System.out.print(tx.getAmount());
					System.out.print("\t");
					System.out.print(tx.getShares());
					System.out.println();
				}
			}*/
			
			double shares = transactions.stream()
					.filter(p -> p.getShares() != null) 
					.mapToDouble(StockMarketTransaction::getShares).sum();
			double fees = transactions.stream()
					.filter(p -> p.getFees() != null)
					.mapToDouble(StockMarketTransaction::getFees).sum();
			double amountInvested = transactions.stream()
					.filter(p-> p.getAmount() != null) 
					.mapToDouble(StockMarketTransaction::getAmount).sum();
			
			summary.addFees(fees); 
			summary.addAmountInvested(amountInvested); 
			
			StockMarketAsset asset = assetService.retrieveAssetBySymbol(symbol);
			HoldingSummary holdingSummary = new HoldingSummary();
			holdingSummary.setAsset(asset); 
			holdingSummary.setAmountInvested(amountInvested); 
			holdingSummary.setSymbol(symbol);
			holdingSummary.setFees(fees);
			
			NumberFormat format = new DecimalFormat("##.###"); 
			String strString = format.format(shares);
			shares = Double.parseDouble(strString);
			
			holdingSummary.setShares(shares); 
			summaryList.add(holdingSummary);
		}
		
		summary.setHoldingSummaries(summaryList); 
		return summary;

	}
	
}
