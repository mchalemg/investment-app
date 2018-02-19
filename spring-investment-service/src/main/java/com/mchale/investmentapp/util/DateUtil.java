package com.mchale.investmentapp.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.mchale.investmentapp.spring.data.datadynamodb.model.Account;
import com.mchale.investmentapp.spring.data.datadynamodb.model.StockMarketTransaction;

public class DateUtil {

	/**
	 * 
	 * @param yearMonthDay - String format 2017-01-02 (jan second)
	 * @return
	 */
	public static DateTime getDateFromYearMonthDay(String yearMonthDay) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
		DateTime parseDateTime = formatter.parseDateTime(yearMonthDay);
		return parseDateTime.withTimeAtStartOfDay();
	}
	
	public static void testme() {
		StockMarketTransaction t = new StockMarketTransaction();
		

	}
	
}
