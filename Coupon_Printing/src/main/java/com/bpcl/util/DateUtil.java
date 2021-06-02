package com.bpcl.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {
	public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DEFAULT_FORMAT_DAY = "yyyy-MM-dd";
	public static final String INT_STRING_FORMAT = "yyyyMMddHHmmss";
	public static final String INT_STRING_FORMAT_MONTH = "yyyyMM";
	public static final int DB_LONG_DATE = 5;

	public DateUtil() {
	}

	public static String format(Date date) {
		try {
			SimpleDateFormat e = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (date != null) {
				return e.format(date);
			}
		} catch (Exception var2) {
			var2.getMessage();
		}

		return "";
	}

	public static String format(Date date, String formatType) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(formatType);
		return dateFormat.format(date);
	}

	public static String getDateForIntString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static String getDatesForIntString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmsss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static String getDateForString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static String getDateForIntString(String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static String getCurrentDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static String getCurrentMonth() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
		Date date = new Date();
		return dateFormat.format(date);
	}

	/*
	 * public static String formatDate(Date date, String pattern) { if
	 * (StringUtil.isEmpty(pattern)) { pattern = "yyyy-MM-dd HH:mm:ss"; }
	 * 
	 * return date instanceof Date ? format(date, pattern) : ""; }
	 */

	public static long getNowTime() {
		return (new Date()).getTime();
	}

	public static Date getDateFromStr(String dateStr, String pattern) {
		Date d = null;

		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.US);
			d = sdf.parse(dateStr);
		} catch (Exception var4) {
			;
		}

		return d;
	}

	public static Date getNextDate(int minutes) {
		Date date = new Date();
		long currTime = date.getTime();
		currTime += (long) (minutes * 60 * 1000);
		date.setTime(currTime);
		return date;
	}

	public static int getYear(Date date) {
		if (date == null) {
			date = new Date();
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}

	public static int getWeek(Date date) {
		if (date == null) {
			date = new Date();
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK) - 1;
	}

	public static int getHour(Date date) {
		if (date == null) {
			date = new Date();
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.HOUR_OF_DAY);
	}

	public static int getMinute(Date date) {
		if (date == null) {
			date = new Date();
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MINUTE);
	}

	public static Timestamp getSystemTime() {
		return new Timestamp(System.currentTimeMillis());
	}

	public static String translateDate(Long time) {
		long oneDay = 24 * 60 * 60 * 1000;
//        Calendar current = Calendar.getInstance();
//        Calendar today = Calendar.getInstance(); // Nowadays
//
//        today.set(Calendar.YEAR, current.get(Calendar.YEAR));
//        today.set(Calendar.MONTH, current.get(Calendar.MONTH));
//        today.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH));
//        // Calendar.HOUR - 12-hour hourly Calendar.HOUR_OF_DAY - 24-hour hour
//        today.set(Calendar.HOUR_OF_DAY, 0);
//        today.set(Calendar.MINUTE, 0);
//        today.set(Calendar.SECOND, 0);

		Date dt = new Date(time);
		SimpleDateFormat fmt = new SimpleDateFormat("HH:mm");
		String dtStr = fmt.format(dt);

//        long todayStartTime = today.getTimeInMillis();
		long current = System.currentTimeMillis();
		long todayStartTime = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();

		if (time >= todayStartTime && time < todayStartTime + oneDay) { // today
			return "Nowadays" + dtStr;
		} else if (time >= todayStartTime + oneDay && time < todayStartTime + oneDay * 2) { // yesterday
			return "tomorrow" + dtStr;
		} else if (time >= todayStartTime + oneDay * 2 && time < todayStartTime + oneDay * 3) { // the day before
			// yesterday
			return "acquired" + dtStr;
		}
//        else if (time > todayStartTime + oneDay) { // future
//            return "some day in the future";
//        }
		else {
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM month dd day HH: mm");
			Date date = new Date(time);
			return dateFormat.format(date);
		}
	}

	/**
	 * Whether it is at night time
	 *
	 * @param TimeOn
	 * @param TimeOff
	 * @return
	 */
	public static boolean isInNightTime(String TimeOn, String TimeOff, Date date) {

		Calendar calDate = Calendar.getInstance();
		calDate.setTime(date);

		int dateHour = calDate.get(Calendar.HOUR_OF_DAY);

		int nightTimeBeginHour = Integer.parseInt(TimeOn.substring(0, 2));
		int nightTimeBeginMinute = Integer.parseInt(TimeOn.substring(2, 4));

		int nightTimeEndHour = Integer.parseInt(TimeOff.substring(0, 2));
		int nightTimeEndMinute = Integer.parseInt(TimeOff.substring(2, 4));

		// Set night start time
		Calendar calBegin = Calendar.getInstance();
		calBegin.set(Calendar.YEAR, calDate.get(Calendar.YEAR));
		calBegin.set(Calendar.MONTH, calDate.get(Calendar.MONTH));

		if (dateHour >= 0 && dateHour <= nightTimeEndHour && nightTimeBeginHour > nightTimeEndHour) {
			calBegin.set(Calendar.DAY_OF_MONTH, calDate.get(Calendar.DAY_OF_MONTH) - 1);
		} else {
			calBegin.set(Calendar.DAY_OF_MONTH, calDate.get(Calendar.DAY_OF_MONTH));
		}
		calBegin.set(Calendar.HOUR_OF_DAY, nightTimeBeginHour);
		calBegin.set(Calendar.MINUTE, nightTimeBeginMinute);
		calBegin.set(Calendar.SECOND, 0);

		// Set night end time
		Calendar calEnd = Calendar.getInstance();
		calEnd.set(Calendar.YEAR, calDate.get(Calendar.YEAR));
		calEnd.set(Calendar.MONTH, calDate.get(Calendar.MONTH));

		if (dateHour >= 0 && dateHour <= nightTimeEndHour) {
			calEnd.set(Calendar.DAY_OF_MONTH, calDate.get(Calendar.DAY_OF_MONTH));
		} else {
			calEnd.set(Calendar.DAY_OF_MONTH, calDate.get(Calendar.DAY_OF_MONTH) + 1);
		}
		calEnd.set(Calendar.HOUR_OF_DAY, nightTimeEndHour);
		calEnd.set(Calendar.MINUTE, nightTimeEndMinute);
		calEnd.set(Calendar.SECOND, 0);

		if ((calBegin.getTime().getTime() <= date.getTime()) && (date.getTime() <= calEnd.getTime().getTime())) {
			return true;
		}

		return false;
	}

	/**
	 * Whether during peak hours
	 *
	 * @param TimeOn
	 * @param TimeOff
	 * @return
	 */
	public static boolean isInHighPreTime(String TimeOn, String TimeOff, Date date) {

		Calendar calDate = Calendar.getInstance();
		calDate.setTime(date);

		int dateHour = calDate.get(Calendar.HOUR_OF_DAY);

		int nightTimeBeginHour = Integer.parseInt(TimeOn.substring(0, 2));
		int nightTimeBeginMinute = Integer.parseInt(TimeOn.substring(2, 4));

		int nightTimeEndHour = Integer.parseInt(TimeOff.substring(0, 2));
		int nightTimeEndMinute = Integer.parseInt(TimeOff.substring(2, 4));

		// Set start time
		Calendar calBegin = Calendar.getInstance();
		calBegin.set(Calendar.YEAR, calDate.get(Calendar.YEAR));
		calBegin.set(Calendar.MONTH, calDate.get(Calendar.MONTH));
		calBegin.set(Calendar.DAY_OF_MONTH, calDate.get(Calendar.DAY_OF_MONTH));
		calBegin.set(Calendar.HOUR_OF_DAY, nightTimeBeginHour);
		calBegin.set(Calendar.MINUTE, nightTimeBeginMinute);
		calBegin.set(Calendar.SECOND, 0);

		// Set end time
		Calendar calEnd = Calendar.getInstance();
		calEnd.set(Calendar.YEAR, calDate.get(Calendar.YEAR));
		calEnd.set(Calendar.MONTH, calDate.get(Calendar.MONTH));
		calEnd.set(Calendar.DAY_OF_MONTH, calDate.get(Calendar.DAY_OF_MONTH));
		calEnd.set(Calendar.HOUR_OF_DAY, nightTimeEndHour);
		calEnd.set(Calendar.MINUTE, nightTimeEndMinute);
		calEnd.set(Calendar.SECOND, 0);

		if ((calBegin.getTime().getTime() <= date.getTime()) && (date.getTime() <= calEnd.getTime().getTime())) {
			return true;
		}

		return false;
	}

	/**
	 * <p>
	 * Description: Get days before and after
	 * </p>
	 * <p>
	 * Author:Gred
	 * </p>
	 * <p>
	 * Date:2017/4/3 10:31
	 * </p>
	 * <p>
	 * param:date Current date
	 * </p>
	 * <p>
	 * param:day Calculated days
	 * </p>
	 **/
	public static Date getNextDay(Date date, int day) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, day);
		date = calendar.getTime();

		return date;
	}

	public static String yymmdd() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
		Date date = new Date();

		return dateFormat.format(date);
	}

}
