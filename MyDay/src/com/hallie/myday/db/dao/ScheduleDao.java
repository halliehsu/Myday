package com.hallie.myday.db.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hallie.myday.db.ScheduleDBOpenHelper;
import com.hallie.myday.domain.Schedule;

public class ScheduleDao {

	private ScheduleDBOpenHelper helper;
//	private Context context;

	public ScheduleDao(Context context) {
		helper = new ScheduleDBOpenHelper(context);
	//	this.context = context;
	}
	/**
	 * 添加一个事务
	 * @param schedule
	 */
	public void addSchedule(Schedule schedule){
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values=new ContentValues();
		//int sn, String date1, String time1, String date2,
		//String time2, String type, String title, String note,
		//String timeSet, String alarmSet
		values.put("date1", schedule.getDate1());
		values.put("time1", schedule.getTime1());
		values.put("date2", schedule.getDate2());
		values.put("time2", schedule.getTime2());
		values.put("type", schedule.getType());
		values.put("title", schedule.getTitle());
		values.put("note", schedule.getNote());
		values.put("timeSet", String.valueOf(schedule.isTimeSet()));
		values.put("alarmSet", String.valueOf(schedule.isAlarmSet()));
		db.insert("schedule", null, values);
		db.close();
		
	}
	public void deleteSchedule(int sn){
		SQLiteDatabase db = helper.getWritableDatabase();
		db.delete("schedule", "sn=?", new String[]{String.valueOf(sn)});
		db.close();
		
	}

	/**
	 * 查询数据库中事务列表
	 * 
	 * @return
	 */
	public List<Schedule> findAllSchedule() {
		List<Schedule> schedules = new ArrayList<Schedule>();

		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.query("schedule", null, null, null, null, null,
				"date1 desc,time1 desc");
		while (cursor.moveToNext()) {
			//int sn = cursor.getInt(0);
			String date1 = cursor.getString(1);
			String time1 = cursor.getString(2);
			String date2 = cursor.getString(3);
			String time2 = cursor.getString(4);
			String title = cursor.getString(5);
			String note = cursor.getString(6);
			String type = cursor.getString(7);
			String timeset = cursor.getString(8);
			String alarmset = cursor.getString(9);
			Schedule schTemp = new Schedule(date1, time1, date2, time2,
					type, title, note, timeset, alarmset);
			schedules.add(schTemp);
		}
		cursor.close();
		db.close();

		return schedules;

	}
	

}
