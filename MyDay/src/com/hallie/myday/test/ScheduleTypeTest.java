package com.hallie.myday.test;

import java.util.Calendar;

import cn.bmob.v3.listener.SaveListener;

import com.hallie.myday.db.ScheduleDBOpenHelper;
import com.hallie.myday.db.dao.ScheduleDao;
import com.hallie.myday.db.dao.TypeDao;
import com.hallie.myday.domain.Schedule;
import com.hallie.myday.domain.ScheduleUser;

import android.test.AndroidTestCase;

public class ScheduleTypeTest extends AndroidTestCase {
	public void testCreateDB() {
		ScheduleDBOpenHelper helper = new ScheduleDBOpenHelper(getContext());
		helper.getReadableDatabase();
	}

	public void testAddS() {
		ScheduleDao dao = new ScheduleDao(getContext());
		Calendar c = Calendar.getInstance();
		int t1 = c.get(Calendar.YEAR);
		int t2 = c.get(Calendar.MONTH) + 1;
		int t3 = c.get(Calendar.DAY_OF_MONTH);
		Schedule s = new Schedule(t1, t2, t3);
		dao.addSchedule(s);
		// System.out.println(String.valueOf(s.isAlarmSet()));

	}

	public void testDeletS() {

		ScheduleDao dao = new ScheduleDao(getContext());
		dao.deleteSchedule(4);
	}

	// =======================“‘œ¬ «type test==========================

	public void testAddtype() {
		TypeDao dao = new TypeDao(getContext());
		dao.addType("”È¿÷");
	}
	
	public void testSu(){
		ScheduleUser user=new ScheduleUser();
		user.setUsername("hallie");
		user.setPassword("hallie");
		user.signUp(getContext(), new SaveListener() {
			
			@Override
			public void onSuccess() {
				System.out.println("hallie");
				
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				System.out.println("fault");
			}
		});
	}

}
