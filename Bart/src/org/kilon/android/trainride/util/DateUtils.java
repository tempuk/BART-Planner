package org.kilon.android.trainride.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.util.Log;

@SuppressWarnings("unused")
public class DateUtils {

	private static final String TAG = "DateUtils";

	private DateUtils() {
		
	}
	
	public static Date parseDate(String format, String dateString) throws ParseException {
		return parseDate(format, dateString, 0);
	}
	
	public static Date parseDate(String format, String dateString, int minutesToAdd) throws ParseException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Date date = dateFormat.parse(dateString);
		
		Calendar cal = (Calendar) Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, minutesToAdd);
		
		return cal.getTime();
	}

	
	
	
	public static long getMinutesDiff(Date departureDate, Date arrivalDate) {
		long miliseconds = arrivalDate.getTime() - departureDate.getTime();
		double seconds = miliseconds / 1000;
		double minutes = seconds / 60;
		double hours = minutes / 60;
		double days  = hours / 24;
		return Math.round(minutes);
		
		
	}
	
}
