package com.hallie.myday;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.listener.SaveListener;

import com.hallie.myday.db.dao.ScheduleDao;
import com.hallie.myday.db.dao.TypeDao;
import com.hallie.myday.domain.Schedule;
import com.hallie.myday.domain.ScheduleUser;
import com.hallie.myday.util.ToastUtil;

public class NewScheduleActivity extends Activity implements OnClickListener {
	private Spinner sp_type;
	private Button bt_typemanage;

	private EditText et_titile;
	private EditText et_note;

	private TextView tv_date;
	private TextView tv_time;
	private Button bt_time_setting;

	private TextView tv_clock;
	private Button bt_clock_setting;

	private Button bt_submit;
	private Button bt_cancle;

	private Schedule schTemp;
	private int sel = 0;

	private List<String> typeList = new ArrayList<String>();
	private TypeDao typeDao;
	private ScheduleDao scheduleDao;

	private SpinnerAdpater adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_schedule);
		init();// 加载布局

		typeDao = new TypeDao(NewScheduleActivity.this);
		typeList = typeDao.findAllType();

		scheduleDao = new ScheduleDao(NewScheduleActivity.this);

		defaultNote();// 新建事件的默认设置

	}

	/**
	 * 新建事件的默认设置
	 */
	@SuppressLint("ResourceAsColor")
	private void defaultNote() {
		Calendar c = Calendar.getInstance();
		int t1 = c.get(Calendar.YEAR);
		int t2 = c.get(Calendar.MONTH) + 1;
		int t3 = c.get(Calendar.DAY_OF_MONTH);
		schTemp = new Schedule(t1, t2, t3);
		et_titile.setText(schTemp.getTitle());
		et_note.setText(schTemp.getNote());
		tv_date.setText(schTemp.getDate1());
		tv_time.setText(schTemp.isTimeSet() ? schTemp.getTime1() + "" : "无具体时间");
		tv_clock.setText(schTemp.isAlarmSet() ? schTemp.getDate2() + " "
				+ schTemp.getTime2() : "无闹钟");

		adapter = new SpinnerAdpater();
		sp_type.setAdapter(adapter);

		sp_type.setSelection(sel);
	}

	/**
	 * 加载布局文件
	 */
	private void init() {
		sp_type = (Spinner) findViewById(R.id.sp_type);
		bt_typemanage = (Button) findViewById(R.id.bt_typemanage);
		bt_typemanage.setOnClickListener(this);

		et_titile = (EditText) findViewById(R.id.et_titile);
		et_note = (EditText) findViewById(R.id.et_note);

		tv_date = (TextView) findViewById(R.id.tv_date);
		tv_time = (TextView) findViewById(R.id.tv_time);
		bt_time_setting = (Button) findViewById(R.id.bt_time_setting);
		bt_time_setting.setOnClickListener(this);

		tv_clock = (TextView) findViewById(R.id.tv_clock);
		bt_clock_setting = (Button) findViewById(R.id.bt_clock_setting);
		bt_clock_setting.setOnClickListener(this);

		bt_submit = (Button) findViewById(R.id.bt_submit);
		bt_submit.setOnClickListener(this);
		bt_cancle = (Button) findViewById(R.id.bt_cancle);
		bt_cancle.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_typemanage:// 增加类类型
			schTemp.setTitle(et_titile.getText().toString());
			schTemp.setNote(et_note.getText().toString());
			sel = sp_type.getSelectedItemPosition();
			Intent manageType = new Intent(NewScheduleActivity.this,
					ManageTypeActivit.class);
			startActivity(manageType);

			break;
		case R.id.tv_date:

			schTemp.setTitle(et_titile.getText().toString());
			schTemp.setNote(et_note.getText().toString());
			sel = sp_type.getSelectedItemPosition();
			// showDialog(2);
			break;

		case R.id.bt_cancle:

			finish();
			break;

		case R.id.bt_submit:// 完成按钮
			addNewSchedule();
			break;

		default:
			break;
		}

	}

	/**
	 * 完成按钮添加新日程
	 */
	private void addNewSchedule() {
		String title = et_titile.getText().toString().trim();
		String note = et_note.getText().toString().trim();
		int typeId = sp_type.getSelectedItemPosition();
		String type = typeList.get(typeId);
		if (TextUtils.isEmpty(title)) {
			Toast.makeText(NewScheduleActivity.this, "请输入标题！",
					Toast.LENGTH_LONG).show();
			return;
		}

		schTemp.setType(type);
		schTemp.setTitle(title);
		if (TextUtils.isEmpty(note)) {
			note = "";
		}
		schTemp.setNote(note);
		// scheduleDao.addSchedule(schTemp);
		// addSchedule(schTemp);
		schTemp.save(NewScheduleActivity.this, new SaveListener() {

			@Override
			public void onSuccess() {
				ToastUtil.toast(NewScheduleActivity.this, "添加事件成功！");

			}

			@Override
			public void onFailure(int arg0, String arg1) {

				ToastUtil.toast(NewScheduleActivity.this, "添加事件失败！");
			}
		});
		finish();

	}

	@SuppressLint("ResourceAsColor")
	class SpinnerAdpater extends BaseAdapter {
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LinearLayout ll = new LinearLayout(NewScheduleActivity.this);
			ll.setOrientation(LinearLayout.VERTICAL);
			TextView tv = new TextView(NewScheduleActivity.this);
			tv.setText(typeList.get(position));
			tv.setTextSize(17);
			tv.setTextColor(R.color.black);
			return tv;
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
			return typeList.size();
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		adapter.notifyDataSetChanged();
	}
}
