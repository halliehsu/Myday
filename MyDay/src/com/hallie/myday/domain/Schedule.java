package com.hallie.myday.domain;

import cn.bmob.v3.BmobObject;

import com.hallie.myday.ConstantValue;
import com.hallie.myday.util.DateTimeUtils;

public class Schedule extends BmobObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int sn;// ÿһ���ճ̶�Ӧһ����һ�޶���sn�������ݿ���Ϊ����
	private String date1;// �ճ�����
	private String time1;// �ճ�ʱ��
	private String date2;// ��������
	private String time2;// ����ʱ��
	private String type;// �ճ�����
	private String title;// �ճ̱���
	private String note;// �ճ̱�ע
	private boolean timeSet;// �ճ��Ƿ����þ���ʱ��
	private boolean alarmSet;// �ճ��Ƿ���������

	// �������ճ�ʱ����ʱ���ݣ�ֻ��Ҫ�������������ݣ�
	// �����ڸոս����½��ճ̽���,��������Ĭ�����óɵ�ǰ����
	public Schedule(int y, int m, int d) {
		// sn = 0;
		date1 = DateTimeUtils.toDateString(y, m, d);
		// ʱ��Ĭ��8��
		setTime1("8", "0");

		date2 = null;
		time2 = null;

		title = "";
		note = "";
		type = ConstantValue.defaultType[0];

		timeSet = true;
		alarmSet = false;
	}

	// �˹�����Ϊ�����ݿ��ȡ�ճ̶���ʱ��
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

	// �����ճ����ڣ�ת����YYYY/MM/DD
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

	// �����ճ�ʱ�䣬ת����HH:MM
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
		if (!timeSet) {// ���Ϊfalse˵��û�����þ���ʱ�䣬�����ʱ��Ĭ��Ϊ�������һ����
			time1 = "23:59";
		}
	}

	public boolean isAlarmSet() {
		return alarmSet;
	}

	public void setAlarmSet(boolean alarmSet) {
		this.alarmSet = alarmSet;
		if (!alarmSet) {// ���Ϊfalse˵��û���������ӣ���������null
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
