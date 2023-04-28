package com.taorui.tags;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

class TimeInfo2 {
	private long startTime;
	private long endTime;

	public long getStartTime() {
		return this.startTime;
	}

	public void setStartTime(long paramLong) {
		this.startTime = paramLong;
	}

	public long getEndTime() {
		return this.endTime;
	}

	public void setEndTime(long paramLong) {
		this.endTime = paramLong;
	}
}

public class WhichDayFunction2 {
	public static String getTimestampString(Date paramDate) {
		String str = null;
		long l = paramDate.getTime();
		Calendar localCalendar = GregorianCalendar.getInstance();
		localCalendar.setTime(paramDate);
		int year = localCalendar.get(Calendar.YEAR);
		if (!isSameYear(year)) { // 去年，直接返回
			String paramDate2str = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA).format(paramDate);
			return paramDate2str;
		}

		if (isSameDay(l)) {
			int i = localCalendar.get(Calendar.HOUR_OF_DAY);
			Calendar now = Calendar.getInstance();
			long s = (now.getTimeInMillis() - localCalendar.getTimeInMillis()) / 1000;
			long m = s / 60;
			if(s <= 30) {
				str = "刚刚";
			}else if(s > 30 && s < 60){
				str = s + "秒前";
			}else {
				if(m >= 1 && m < 60) {
					str = m + "分前";
				}else {
					if (i > 17) {
						str = "晚上 HH:mm";// HH表示24小时,hh表示12小时进制，
					} else if ((i >= 0) && (i <= 6)) {
						str = "凌晨 HH:mm";
					} else if ((i > 11) && (i <= 17)) {
						str = "下午 HH:mm";
					} else {
						str = "上午 HH:mm";
					}
				}
			}
		} else if (isYesterday(l)) {
			str = "昨天 HH:mm";
		} else if (isBeforeYesterday(l)) {
			str = "前天 HH:mm";
		} else {
			str = "MM月dd日 HH:mm";
		}
		String paramDate2str = new SimpleDateFormat(str, Locale.CHINA).format(paramDate);
		return paramDate2str;
	}

	//   获取  今天开始结束 时间
	public static TimeInfo2 getTodayStartAndEndTime() {

		Calendar localCalendar1 = Calendar.getInstance();
		localCalendar1.set(Calendar.HOUR_OF_DAY, 0);
		localCalendar1.set(Calendar.MINUTE, 0);
		localCalendar1.set(Calendar.SECOND, 0);
		localCalendar1.set(Calendar.MILLISECOND, 0);
		Date localDate1 = localCalendar1.getTime();
		long l1 = localDate1.getTime();

		Calendar localCalendar2 = Calendar.getInstance();
		localCalendar2.set(Calendar.HOUR_OF_DAY, 23);
		localCalendar2.set(Calendar.MINUTE, 59);
		localCalendar2.set(Calendar.SECOND, 59);
		localCalendar2.set(Calendar.MILLISECOND, 999);
		Date localDate2 = localCalendar2.getTime();
		long l2 = localDate2.getTime();

		TimeInfo2 localTimeInfo = new TimeInfo2();
		localTimeInfo.setStartTime(l1);
		localTimeInfo.setEndTime(l2);
		return localTimeInfo;
	}

//   获取  昨天开始结束 时间

	public static TimeInfo2 getYesterdayStartAndEndTime() {
		Calendar localCalendar1 = Calendar.getInstance();
		localCalendar1.add(Calendar.DAY_OF_MONTH, -1);// 5
		localCalendar1.set(Calendar.HOUR_OF_DAY, 0);// 11
		localCalendar1.set(Calendar.MINUTE, 0);// 12
		localCalendar1.set(Calendar.SECOND, 0);// 13
		localCalendar1.set(Calendar.MILLISECOND, 0);// Calendar.MILLISECOND
		Date localDate1 = localCalendar1.getTime();
		long l1 = localDate1.getTime();

		Calendar localCalendar2 = Calendar.getInstance();
		localCalendar2.add(Calendar.DAY_OF_MONTH, -1);// 5
		localCalendar2.set(Calendar.HOUR_OF_DAY, 23);// 11
		localCalendar2.set(Calendar.MINUTE, 59);// 12
		localCalendar2.set(Calendar.SECOND, 59);// 13
		localCalendar2.set(Calendar.MILLISECOND, 999);// Calendar.MILLISECOND
		Date localDate2 = localCalendar2.getTime();
		long l2 = localDate2.getTime();

		TimeInfo2 localTimeInfo = new TimeInfo2();
		localTimeInfo.setStartTime(l1);
		localTimeInfo.setEndTime(l2);
		return localTimeInfo;
	}

//   获取 前天开始结束 时间

	public static TimeInfo2 getBeforeYesterdayStartAndEndTime() {
		Calendar localCalendar1 = Calendar.getInstance();
		localCalendar1.add(Calendar.DAY_OF_MONTH, -2);
		localCalendar1.set(Calendar.HOUR_OF_DAY, 0);
		localCalendar1.set(Calendar.MINUTE, 0);
		localCalendar1.set(Calendar.SECOND, 0);
		localCalendar1.set(Calendar.MILLISECOND, 0);
		Date localDate1 = localCalendar1.getTime();
		long l1 = localDate1.getTime();

		Calendar localCalendar2 = Calendar.getInstance();
		localCalendar2.add(Calendar.DAY_OF_MONTH, -2);
		localCalendar2.set(Calendar.HOUR_OF_DAY, 23);
		localCalendar2.set(Calendar.MINUTE, 59);
		localCalendar2.set(Calendar.SECOND, 59);
		localCalendar2.set(Calendar.MILLISECOND, 999);
		Date localDate2 = localCalendar2.getTime();
		long l2 = localDate2.getTime();
		TimeInfo2 localTimeInfo = new TimeInfo2();
		localTimeInfo.setStartTime(l1);
		localTimeInfo.setEndTime(l2);
		return localTimeInfo;
	}

	private static boolean isSameDay(long paramLong) {
		TimeInfo2 localTimeInfo = getTodayStartAndEndTime();
		return (paramLong > localTimeInfo.getStartTime()) && (paramLong < localTimeInfo.getEndTime());
	}

	private static boolean isYesterday(long paramLong) {
		TimeInfo2 localTimeInfo = getYesterdayStartAndEndTime();
		return (paramLong > localTimeInfo.getStartTime()) && (paramLong < localTimeInfo.getEndTime());
	}

	private static boolean isBeforeYesterday(long paramLong) {
		TimeInfo2 localTimeInfo = getBeforeYesterdayStartAndEndTime();
		return (paramLong > localTimeInfo.getStartTime()) && (paramLong < localTimeInfo.getEndTime());
	}

	public static boolean isSameYear(int year) {
		Calendar cal = Calendar.getInstance();
		int CurYear = cal.get(Calendar.YEAR);
		return CurYear == year;
	}

	public static String whichDay(String d) {
		return getTimestampString(java.sql.Timestamp.valueOf(d));
	}

	public static void main(String[] args) {
		System.out.println(whichDay(("2022-10-28 8:21:00")));
	}
}
