package com.mchale.investmentapp.spring.rest.services;

import org.apache.commons.lang3.StringUtils;

import com.mchale.investmentapp.spring.data.datadynamodb.model.StockMarketTransaction;

public class BrokerageCSVUtil {

    public static String cleanCapitalOneInvestment(String strInvestment)
    {
        String cleanedInvestment = strInvestment;
        cleanedInvestment = strInvestment.replace("REV DIVIDEND: ", "");
        cleanedInvestment = strInvestment.replace("DIVIDEND: ", "");
        return cleanedInvestment;
    }

	public static void rewriteCapitalOneTransactionType(StockMarketTransaction bo) {
        if (StringUtils.equals(bo.getActionRaw(), "Buy"))
        {
            bo.setAction("buy");
        }
        else if (StringUtils.equals(bo.getActionRaw(), "ReinvDiv"))
        {
            bo.setAction("reinvest_dividend");
        }
        else if (StringUtils.equals(bo.getActionRaw(), "Div"))
        {
            bo.setAction( "dividend");
        }

        else if (StringUtils.equals(bo.getActionRaw(), "MiscExp"))
        {
            bo.setAction( "sell_fee");
        }

        else if (StringUtils.equals(bo.getActionRaw(), "CGLong"))
        {
            if (bo.getAmount() < 0)
                bo.setAction( "capital_loss_long_term");
            else
            {
                bo.setAction( "capital_gain_long_term");
            }
        }
        else if (StringUtils.equals(bo.getActionRaw(), "CGShort"))
        {
            if (bo.getAmount() < 0)
                bo.setAction( "capital_loss_short_term");
            else
            {
                bo.setAction( "capital_gain_short_term");
            }
        }

        else if (StringUtils.equals(bo.getActionRaw(), "Sell"))
        {
            bo.setAction( "sell");
        }
        /*           CREDIT
        DEBIT
        INT
*/

        else if (StringUtils.equals(bo.getActionRaw(), "CREDIT"))
        {
            bo.setAction( "deposit");
        }
        else if (StringUtils.equals(bo.getActionRaw(), "DEBIT"))
        {
            bo.setAction( "withdrawl");
        }

        //actionRaw = INT often means dividend, but not always... 
        else if (bo.getAssetRaw().contains("DIVIDEND"))
        {
            bo.setAction( "dividend");
        }
        else
        {
            bo.setAction( bo.getActionRaw());
        }
    }
		
	

	
	
	public static void rewriteFidelityTransactionType(StockMarketTransaction bo)
	{
	    if (StringUtils.equals(bo.getActionRaw(), "CONTRIBUTION"))
	    {
	        bo.setAction("buy");
	    }
	    else if (StringUtils.equals(bo.getActionRaw(), "DIVIDEND"))
	    {
	        bo.setAction("reinvest_dividend");
	    }
	
	    else if (StringUtils.equals(bo.getActionRaw(), "ADMINISTRATIVE FEES")
	        || StringUtils.equals(bo.getActionRaw(), "RECORDKEEPING FEE")
	        )
	    {
	        bo.setAction("sell_fee");
	    }
	
	    else if (StringUtils.equals(bo.getActionRaw(), "REALIZED G/L"))
	    {
	        if (bo.getAmount() < 0)
	            bo.setAction("capital_loss");
	        else
	        {
	            bo.setAction("capital_gain");
	        }
	    }
	    else if (StringUtils.equals(bo.getActionRaw(), "Exchanges"))
	    {
	        if (bo.getShares() < 0)
	            bo.setAction("sell");
	        else
	        {
	            bo.setAction("buy");
	        }
	    }
	    else if (StringUtils.equals(bo.getActionRaw(), "VENDOR EXCHANGE IN"))
	    {
	        bo.setAction("balance_transfer");
	    }
	    else
	    {
	        bo.setAction(bo.getActionRaw());
	    }
	}

}
