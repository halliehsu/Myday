package com.hallie.myday.db;

import com.hallie.myday.ConstantValue;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ScheduleDBOpenHelper extends SQLiteOpenHelper {

	public ScheduleDBOpenHelper(Context context) {
		super(context, "schedule.db", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//创建schedule表
		String sql = "create table if not exists schedule("
				+ "sn integer primary key," + "date1 char(10),"
				+ "time1 char(5)," + "date2 char(10)," + "time2 char(5),"
				+ "title varchar2(40)," + "note varchar2(120),"
				+ "type varchar2(20)," + "timeset boolean,"
				+ "alarmset boolean" + ")";
		db.execSQL(sql);
		
		//创建type表
		String sqlType = "create table if not exists type(tno integer primary key,tname varchar2(20));";
		db.execSQL(sqlType);
		for (int i = 0; i < ConstantValue.defaultType.length; i++) {
			String sqlitem = "insert into type values(" + i + ",'"
					+ ConstantValue.defaultType[i] + "')";
			db.execSQL(sqlitem);
		}
		

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
