package com.hallie.myday.domain;

import cn.bmob.v3.BmobObject;

import com.hallie.myday.ConstantValue;
import com.hallie.myday.util.DateTimeUtils;

public class Schedule extends BmobObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int sn;// 每一个日程对应一个独一无二的sn吗，在数据库中为主键
	private String date1;// 日程日期
	private String time1;// 日程时间
	private String date2;// 闹钟日期
	private String time2;// 闹钟时间
	private String type;// 日程类型
	private String title;// 日程标题
	private String note;// 日程备注
	private boolean timeSet;// 日程是否设置具体时间
	private boolean alarmSet;// 日程是否设置闹钟

	// 创建新日程时的临时数据，只需要年月日三个数据，
	// 用来在刚刚进入新建日程界面,把年月日默认设置成当前日期
	public Schedule(int y, int m, int d) {
		// sn = 0;
		date1 = DateTimeUtils.toDateString(y, m, d);
		// 时间默认8点
		setTime1("8", "0");

		date2 = null;
		time2 = null;

		title = "";
		note = "";
		type = ConstantValue.defaultType[0];

		timeSet = true;
		alarmSet = false;
	}

	// 此构造器为从数据库读取日程对象时用
	public Schedule(String date1, String time1, String date2, String time2,
			String type, String title, String note, String timeSet,
			String alarmSet) {
		super();
		// this.sn = sn;
		this.date1 = date1;
		this.time1 = time1;
		this.date2 = date2;
		this.time2 = time2;
		this.type = type;
		this.title = title;
		this.note = note;
		this.timeSet = Boolean.parseBoolean(timeSet);
		this.alarmSet = Boolean.parseBoolean(alarmSet);
	}



	public int getSn() {
		return sn;
	}

	public void setSn(int sn) {
		this.sn = sn;
	}

	public String getDate1() {
		return date1;
	}

	// 设置日程日期，转换成YYYY/MM/DD
	public void setDate1(String y, String m, String d) {
		StringBuffer sb = new StringBuffer();
		sb.append(y + "/");
		sb.append(m + "/");
		sb.append(d);
		date1 = sb.toString();
	}

	public String getTime1() {
		return time1;
	}

	// 设置日程时间，转换成HH:MM
	public void setTime1(String h, String m) {
		StringBuffer sb = new StringBuffer();
		sb.append(h + ":");
		sb.append(m);
		time1 = sb.toString();
	}

	public String getDate2() {
		return date2;
	}

	public void setDate2(String y, String m, String d) {
		StringBuffer sb = new StringBuffer();
		sb.append(y + "/");
		sb.append(m + "/");
		sb.append(d);
		date2 = sb.toString();
	}

	public String getTime2() {
		return time2;
	}

	public void setTime2(String h, String m) {
		StringBuffer sb = new StringBuffer();
		sb.append(h + ":");
		sb.append(m);
		time2 = sb.toString();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public boolean isTimeSet() {
		return timeSet;
	}

	public void setTimeSet(boolean timeSet) {
		this.timeSet = timeSet;
		if (!timeSet) {// 如果为false说明没有设置具体时间，则具体时间默认为当天最后一分钟
			time1 = "23:59";
		}
	}

	public boolean isAlarmSet() {
		return alarmSet;
	}

	public void setAlarmSet(boolean alarmSet) {
		this.alarmSet = alarmSet;
		if (!alarmSet) {// 如果为false说明没有设置闹钟，则闹钟置null
			date2 = null;
			time2 = null;
		}
	}

	public boolean isPassed() {
		String nowDate = DateTimeUtils.getNowDateString();
		String nowTime = DateTimeUtils.getNowTimeString();
		String schDate = date1;
		String schTime = timeSet ? time1 : "23:59";
		if (nowDate.compareTo(schDate) > 0
				|| (nowDate.compareTo(schDate) == 0 && nowTime
						.compareTo(schTime) > 0)) {
			return true;
		}

		return false;
	}

	public String typeForListView() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append(type);
		sb.append("]");
		return sb.toString();
	}

	@Override
	public String toString() {
		return "Schedule [sn=" + sn + ", date1=" + date1 + ", time1=" + time1
				+ ", date2=" + date2 + ", time2=" + time2 + ", type=" + type
				+ ", title=" + title + ", note=" + note + ", timeSet="
				+ timeSet + ", alarmSet=" + alarmSet + "]";
	}

}
