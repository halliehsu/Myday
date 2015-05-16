package com.hallie.myday;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.hallie.myday.db.dao.ScheduleDao;
import com.hallie.myday.db.dao.TypeDao;
import com.hallie.myday.domain.Schedule;
import com.hallie.myday.util.DateTimeUtils;

public class HomeLocalActivity extends Activity {
	private ImageButton ib_delete;
	private ImageButton ib_newone;
	private ImageButton ib_check;
	private ImageButton ib_edit;
	private ImageButton ib_deleteall;
	private ImageButton ib_search;
	private ListView lv_mainSchedule;

	private List<Schedule> schedules = new ArrayList<Schedule>();

	Schedule schTemp;

	private ScheduleDao scheduleDao;
	private TypeDao typeDao;
	private List<Boolean> allIsSelected;

	private ScheduleAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();// 初始化界面
		gotoMain();
	}

	private void init() {
		ib_newone = (ImageButton) findViewById(R.id.ib_newone);
		ib_delete = (ImageButton) findViewById(R.id.ib_delete);
		ib_check = (ImageButton) findViewById(R.id.ib_check);
		ib_edit = (ImageButton) findViewById(R.id.ib_edit);
		ib_deleteall = (ImageButton) findViewById(R.id.ib_deleteall);
		ib_search = (ImageButton) findViewById(R.id.ib_search);
		lv_mainSchedule = (ListView) findViewById(R.id.lv_mainSchedule);

	}

	protected void gotoMain() {

		allIsSelected = new ArrayList<Boolean>();	

		ib_check.setEnabled(false);
		ib_edit.setEnabled(false);
		ib_delete.setEnabled(false);


		// 获取事务列表
		scheduleDao = new ScheduleDao(HomeLocalActivity.this);
		schedules = scheduleDao.findAllSchedule();
		// typeDao=new TypeDao(HomeActivity.this);
		// typeDao.initType();

		if (schedules.size() == 0) {
			ib_deleteall.setEnabled(false);
		} else {
			ib_deleteall.setEnabled(true);
		}

		allIsSelected.clear();
		for (int i = 0; i < schedules.size(); i++) {
			allIsSelected.add(false);
		}
		adapter = new ScheduleAdapter();
		lv_mainSchedule.setAdapter(adapter);
		lv_mainSchedule.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ib_check.setEnabled(true);
				ib_edit.setEnabled(true);
				ib_delete.setEnabled(true);

				schTemp = schedules.get(position);

				for (int i = 0; i < allIsSelected.size(); i++) {
					allIsSelected.set(i, false);
				}
				allIsSelected.set(position, true);
			}
		});

		ib_newone.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Calendar c=Calendar.getInstance();
				// int t1=c.get(Calendar.YEAR);
				// int t2=c.get(Calendar.MONTH)+1;
				// int t3=c.get(Calendar.DAY_OF_MONTH);
				// schTemp=new Schedule(t1, t2, t3);

				Intent intent = new Intent(HomeLocalActivity.this,
						NewScheduleActivity.class);
				startActivity(intent);

			}
		});
	}

	@Override
	protected void onResume() {
		schedules = scheduleDao.findAllSchedule();
		adapter.notifyDataSetChanged();
		super.onResume();
	}

	class ScheduleAdapter extends BaseAdapter {
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			LinearLayout ll = new LinearLayout(HomeLocalActivity.this);
			ll.setOrientation(LinearLayout.VERTICAL);
			ll.setPadding(5, 5, 5, 5);

			LinearLayout llUp = new LinearLayout(HomeLocalActivity.this);
			llUp.setOrientation(LinearLayout.HORIZONTAL);
			LinearLayout llDown = new LinearLayout(HomeLocalActivity.this);
			llDown.setOrientation(LinearLayout.HORIZONTAL);

			TextView tvDate = new TextView(HomeLocalActivity.this);
			tvDate.setText(schedules.get(position).getDate1() + "  ");
			tvDate.setTextSize(17);
			tvDate.setTextColor(Color.parseColor("#129666"));
			llUp.addView(tvDate);

			TextView tvTime = new TextView(HomeLocalActivity.this);
			Schedule temp = schedules.get(position);
			tvTime.setText(DateTimeUtils.timeForListView(temp.isTimeSet(),
					temp.getTime1()));
			tvTime.setTextSize(17);
			tvTime.setTextColor(Color.parseColor("#925301"));
			llUp.addView(tvTime);

			if (schedules.get(position).isPassed()) {
				tvDate.setTextColor(getResources().getColor(
						R.color.passedschtext));
				tvTime.setTextColor(getResources().getColor(
						R.color.passedschtext));
				ll.setBackgroundColor(getResources().getColor(
						R.color.passedschbg));

			}

			// if (allIsSelected.get(position)) {
			// ll.setBackgroundColor(getResources().getColor(
			// R.color.selectedsch));
			// }
			if (schedules.get(position).isAlarmSet()) {
				ImageView iv = new ImageView(HomeLocalActivity.this);
				iv.setImageDrawable(getResources()
						.getDrawable(R.drawable.alarm));
				iv.setLayoutParams(new LayoutParams(20, 20));
				llUp.addView(iv);
			}

			// 日程类型textview
			TextView tvType = new TextView(HomeLocalActivity.this);
			tvType.setText(schedules.get(position).typeForListView());
			tvType.setTextSize(17);
			tvType.setTextColor(Color.parseColor("#b20000"));
			llDown.addView(tvType);

			// 日程标题textview
			TextView tvTitle = new TextView(HomeLocalActivity.this);
			tvTitle.setText(schedules.get(position).getTitle());
			tvTitle.setTextSize(17);
			tvTitle.setTextColor(Color.parseColor("#000000"));
			llDown.addView(tvTitle);

			ll.addView(llUp);
			ll.addView(llDown);
			return ll;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return schedules.size();
		}
	}

}
