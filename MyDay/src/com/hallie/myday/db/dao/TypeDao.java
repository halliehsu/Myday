package com.hallie.myday.db.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hallie.myday.ConstantValue;
import com.hallie.myday.db.ScheduleDBOpenHelper;

public class TypeDao {
	private ScheduleDBOpenHelper helper;
	//private Context context;

	public TypeDao(Context context) {
		helper = new ScheduleDBOpenHelper(context);
		//this.context = context;
	}

	/**
	 * 添加默认type
	 */
	public void initType() {
		SQLiteDatabase db = helper.getWritableDatabase();
		for (int i = 0; i < ConstantValue.defaultType.length; i++) {
			String sqlitem = "insert into type values(" + i + ",'"
					+ ConstantValue.defaultType[i] + "')";
			db.execSQL(sqlitem);
		}
	}

	/**
	 * 查询type列表
	 * @return
	 */
	public List<String> findAllType() {
		List<String> types = new ArrayList<String>();
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.query("type", null, null, null, null, null, "tno");
		while (cursor.moveToNext()) {
			types.add(cursor.getString(1));
		}
		//System.out.println(String.valueOf(cursor.getCount()));
		cursor.close();
		db.close();
		return types;
	}
	/**
	 * 添加type
	 * @param type
	 */
	public void addType(String type){
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values=new ContentValues();
		values.put("tname", type);
		db.insert("type", null, values);
		db.close();		
	}
	
	public void deleteType(String type){
		SQLiteDatabase db = helper.getWritableDatabase();
		db.delete("type", "tname=?", new String[]{type});
		db.close();
	}
	

}
