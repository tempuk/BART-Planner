package org.kilon.android.trainride.test.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.kilon.android.trainride.util.DateUtils;

import android.test.AndroidTestCase;

public class DateUtilsTestCase extends AndroidTestCase {

	private static final String DATE_FORMAT = "MM/dd/yyyy hh:mm a";
	
	public DateUtilsTestCase() {
		super();
	}
	
	public void testParseDate2() throws ParseException {
		Date date = DateUtils.parseDate(DATE_FORMAT, "1/27/1976 10:15 PM", 0);
		Calendar cal = (Calendar) Calendar.getInstance().clone();
		cal.setTime(date);
		
		assertEquals(10, cal.get(Calendar.HOUR));
		assertEquals(15, cal.get(Calendar.MINUTE));
		assertEquals(Calendar.PM, cal.get(Calendar.AM_PM));
		
		assertEquals(Calendar.JANUARY, cal.get(Calendar.MONTH));
		assertEquals(27, cal.get(Calendar.DAY_OF_MONTH));
		assertEquals(1976, cal.get(Calendar.YEAR));
	}
	
	public void testDateDiff() throws ParseException {
		
		Date date1 = DateUtils.parseDate(DATE_FORMAT, "1/27/1976 11:45 PM", 0);
		Date date2 = DateUtils.parseDate(DATE_FORMAT, "1/28/1976 00:16 AM", 0);
		
		long diff = DateUtils.getMinutesDiff(date1, date2);
		assertEquals(31, diff);
	}
	
	
	public void testDateByMinutes1() throws ParseException {
		String format = "MM/dd/yyyy hh:mm a";
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Calendar cal = (Calendar) Calendar.getInstance().clone();
		String baseDateStr = dateFormat.format(cal.getTime());
		
		Date date;
		
		date = DateUtils.parseDate(format, baseDateStr, 0);
		assertNotSame(cal.getTime(), date);
		assertEquals(baseDateStr, dateFormat.format(date));

		date = DateUtils.parseDate(format, dateFormat.format(cal.getTime()), 5);
		assertFalse(baseDateStr.equals(dateFormat.format(date)));
		cal.add(Calendar.MINUTE, 5);
		assertEquals(dateFormat.format(cal.getTime()), dateFormat.format(date));
		
	}
	
	
	
	
}
