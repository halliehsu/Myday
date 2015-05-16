package com.hallie.myday.bmob;

import com.hallie.myday.MyDayApplication;
import com.hallie.myday.domain.Schedule;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class BmobScheduleDao {

	public static void findScheduleObject(FindListener<Schedule> findListener) {
		BmobQuery<Schedule> bmobQuery = new BmobQuery<Schedule>();
		bmobQuery.findObjects(MyDayApplication.context(), findListener);
	}

}
