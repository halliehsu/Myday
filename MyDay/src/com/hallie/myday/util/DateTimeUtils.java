package com.hallie.myday.util;

import java.util.Calendar;

public class DateTimeUtils {

	/**
	 * ��ȡ��ǰ����
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
	 * ��ȡ��ǰʱ��
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

	// ��̬��������int�͵�������ת����YYYY/MM/DD
	public static String toDateString(int y, int m, int d) {
		StringBuffer sb = new StringBuffer();
		sb.append(y + "/");
		sb.append(m < 10 ? "0" + m : "" + m);
		sb.append("/");
		sb.append(d < 10 ? "0" + d : "" + d);

		return sb.toString();
	}

	// �����ճ�ʱ�䣬ת����HH:MM
	public static String toTimeString(int h, int m) {
		StringBuffer sb = new StringBuffer();
		sb.append(h<10?"0"+h:""+h);
		sb.append(":");
		sb.append(m<10?"0"+m:""+m);
		return sb.toString();
	}

	// �����
	public static int getYear(String date1) {
		String[] date = date1.split("/");
		int tmp = Integer.valueOf(date[0]);
		return tmp;
	}

	// �����
	public static int getMonth(String date1) {
		String[] date = date1.split("/");
		int tmp = Integer.valueOf(date[1]);
		return tmp;
	}

	// �����
	public static int getDay(String date1) {
		String[] date = date1.split("/");
		int tmp = Integer.valueOf(date[2]);
		return tmp;
	}

	// ���ʱ
	public static int getHour(String time1) {
		String[] time = time1.split(":");
		int tmp = Integer.valueOf(time[0]);
		return tmp;
	}

	// ��÷�
	public static int getMinute(String time1) {
		String[] time = time1.split(":");
		int tmp = Integer.valueOf(time[1]);
		return tmp;
	}

	// ���������
	public static int getAlarmYear(String date2) {
		String[] date = date2.split("/");
		int tmp = Integer.valueOf(date[0]);
		return tmp;
	}

	// ���������
	public static int getAlarmMonth(String date2) {
		String[] date = date2.split("/");
		int tmp = Integer.valueOf(date[1]);
		return tmp;
	}

	// ���������
	public static int getAlarmDay(String date2) {
		String[] date = date2.split("/");
		int tmp = Integer.valueOf(date[2]);
		return tmp;
	}

	// �������ʱ
	public static int getAlarmHour(String time2) {
		String[] time = time2.split(":");
		int tmp = Integer.valueOf(time[0]);
		return tmp;
	}

	// ������ӷ�
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
