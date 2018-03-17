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
	
	public static String formatYearMonthDay(DateTime yearMonthDay) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
		String print = formatter.print(yearMonthDay);
		return print;
	}

	
	public static DateTime getDateFromMonthDayYear(String monthDayYear) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("MM/dd/yyyy");
		DateTime parseDateTime = formatter.parseDateTime(monthDayYear);
		return parseDateTime.withTimeAtStartOfDay();
	}

	
	
	public static void testme() {
		StockMarketTransaction t = new StockMarketTransaction();
		

	}

	public static DateTime epoch() {
		DateTime date = getDateFromYearMonthDay("1970-01-01"); 
		return date;
	}
	
}
