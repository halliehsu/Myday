package com.hallie.myday.util;

import java.util.Calendar;

public class DateTimeUtils {

	/**
	 * 获取当前日期
	 * 
	 * @return
	 */
	public static String getNowDateString() {
		Calendar c = Calendar.getInstance();
		String nowDate = DateTimeUtils.toDateString(c.get(Calendar.YEAR),
				c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH));

		return nowDate;
	}

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static String getNowTimeString() {

		Calendar c = Calendar.getInstance();
		int nowh = c.get(Calendar.HOUR_OF_DAY);
		int nowm = c.get(Calendar.MINUTE);
		String nowTime = (nowh < 10 ? "0" + nowh : "" + nowh) + ":"
				+ (nowm < 10 ? "0" + nowm : "" + nowm);
		return nowTime;
	}

	// 静态方法，把int型的年月日转换成YYYY/MM/DD
	public static String toDateString(int y, int m, int d) {
		StringBuffer sb = new StringBuffer();
		sb.append(y + "/");
		sb.append(m < 10 ? "0" + m : "" + m);
		sb.append("/");
		sb.append(d < 10 ? "0" + d : "" + d);

		return sb.toString();
	}

	// 设置日程时间，转换成HH:MM
	public static String toTimeString(int h, int m) {
		StringBuffer sb = new StringBuffer();
		sb.append(h<10?"0"+h:""+h);
		sb.append(":");
		sb.append(m<10?"0"+m:""+m);
		return sb.toString();
	}

	// 获得年
	public static int getYear(String date1) {
		String[] date = date1.split("/");
		int tmp = Integer.valueOf(date[0]);
		return tmp;
	}

	// 获得月
	public static int getMonth(String date1) {
		String[] date = date1.split("/");
		int tmp = Integer.valueOf(date[1]);
		return tmp;
	}

	// 获得日
	public static int getDay(String date1) {
		String[] date = date1.split("/");
		int tmp = Integer.valueOf(date[2]);
		return tmp;
	}

	// 获得时
	public static int getHour(String time1) {
		String[] time = time1.split(":");
		int tmp = Integer.valueOf(time[0]);
		return tmp;
	}

	// 获得分
	public static int getMinute(String time1) {
		String[] time = time1.split(":");
		int tmp = Integer.valueOf(time[1]);
		return tmp;
	}

	// 获得闹钟年
	public static int getAlarmYear(String date2) {
		String[] date = date2.split("/");
		int tmp = Integer.valueOf(date[0]);
		return tmp;
	}

	// 获得闹钟月
	public static int getAlarmMonth(String date2) {
		String[] date = date2.split("/");
		int tmp = Integer.valueOf(date[1]);
		return tmp;
	}

	// 获得闹钟日
	public static int getAlarmDay(String date2) {
		String[] date = date2.split("/");
		int tmp = Integer.valueOf(date[2]);
		return tmp;
	}

	// 获得闹钟时
	public static int getAlarmHour(String time2) {
		String[] time = time2.split(":");
		int tmp = Integer.valueOf(time[0]);
		return tmp;
	}

	// 获得闹钟分
	public static int getAlarmMinute(String time2) {
		String[] time = time2.split(":");
		int tmp = Integer.valueOf(time[1]);
		return tmp;
	}

	public static String timeForListView(boolean timeSet, String time1) {
		if (!timeSet) {
			return "- -:- - ";
		}
		StringBuffer sb = new StringBuffer();
		sb.append(time1);
		sb.append("  ");
		return sb.toString();
	}

}
