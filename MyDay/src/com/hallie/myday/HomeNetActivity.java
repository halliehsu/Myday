package com.hallie.myday;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import cn.bmob.v3.listener.FindListener;

import com.hallie.myday.bmob.BmobScheduleDao;
import com.hallie.myday.domain.Schedule;

public class HomeNetActivity extends Activity {
	private ListView lv_schedule_list;
	private View clude_botton;
	private ImageButton search_btn;
	private ImageView add_sechedule;

	private NetScheduleAdapter adapter;

	private List<Schedule> allSchedule = new ArrayList<Schedule>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		init();// 初始化加载布局文件

		// 加载schedule数据
		addScheduleData();

		enterNewSchedule();// 进入添加事项的界面
	}

	/**
	 * 进入添加事项的界面
	 */
	private void enterNewSchedule() {
		add_sechedule.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(HomeNetActivity.this,
						NewScheduleNetActivity.class);
				startActivity(intent);

			}
		});

	}

	/**
	 * 初始化加载布局文件
	 */
	private void init() {
		setContentView(R.layout.activity_home_net);
		lv_schedule_list = (ListView) findViewById(R.id.lv_schedule_list);
		clude_botton = findViewById(R.id.clude_botton);

		search_btn = (ImageButton) clude_botton.findViewById(R.id.search_btn);
		add_sechedule = (ImageView) clude_botton
				.findViewById(R.id.add_sechedule);
	}

	/**
	 * 加载schedule数据
	 */
	private void addScheduleData() {
		BmobScheduleDao.findScheduleObject(new FindListener<Schedule>() {

			@Override
			public void onSuccess(List<Schedule> arg0) {
				// TODO Auto-generated method stub
				allSchedule.addAll(arg0);

				System.out.println(allSchedule.toString());
				System.out.println(allSchedule.size() + "");

				adapter = new NetScheduleAdapter();
				lv_schedule_list.setAdapter(adapter);

			}

			@Override
			public void onError(int arg0, String arg1) {
				// TODO Auto-generated method stub

			}
		});

	}

	class NetScheduleAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// // TODO Auto-generated method stub
			System.out.println(allSchedule.size() + "条");
			return allSchedule.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder;
			if (convertView != null) {
				holder = (ViewHolder) convertView.getTag();
			} else {
				convertView = View.inflate(HomeNetActivity.this,
						R.layout.schedule_item, null);
				holder = new ViewHolder();
				holder.complete_btn = (ImageView) convertView
						.findViewById(R.id.complete_btn);
				holder.schedule_title = (TextView) convertView
						.findViewById(R.id.schedule_title);
				holder.schedule_startat = (TextView) convertView
						.findViewById(R.id.schedule_startat);
				holder.schedule_context = (TextView) convertView
						.findViewById(R.id.schedule_context);
				convertView.setTag(holder);
			}

			Schedule sch = allSchedule.get(position);
			holder.schedule_title.setText(sch.getTitle());
			holder.schedule_startat.setText(sch.getTime1());
			holder.schedule_context.setText(sch.getNote());

			return convertView;
		}
	}

	class ViewHolder {
		ImageView complete_btn;
		TextView schedule_title;
		TextView schedule_startat;
		TextView schedule_context;

	}

}
