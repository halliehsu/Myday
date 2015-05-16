package com.hallie.myday;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.hallie.myday.ui.TopBarView;
import com.hallie.myday.util.DateTimeUtils;

public class NewScheduleNetActivity extends Activity implements OnClickListener {
	private TopBarView top_menu;
	private EditText task_title;
	private EditText task_notes;
	private View task_type;
	private TextView task_type_info;
	private View task_date;
	private TextView task_date_info;
	private View task_time;
	private TextView task_time_info;
	private View task_reminder;
	private TextView task_reminder_info;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_schedule_net);
		init();

		task_type.setOnClickListener(this);
		task_date.setOnClickListener(this);
		task_time.setOnClickListener(this);
		task_reminder.setOnClickListener(this);
	}

	private void init() {
		top_menu = (TopBarView) findViewById(R.id.top_menu);
		task_title = (EditText) findViewById(R.id.task_title);
		task_notes = (EditText) findViewById(R.id.task_notes);
		task_type = findViewById(R.id.taks_type);
		setItemIcon(task_type, R.drawable.icon_viewpage_project);
		setItemTitle(task_type, "任务类型");
		task_type_info = (TextView) task_type.findViewById(R.id.item_info);
		task_type_info.setText("备忘");

		task_date = findViewById(R.id.taks_date);
		setItemIcon(task_date, R.drawable.edit_duetime_icon);
		setItemTitle(task_date, "日期");
		task_date_info = (TextView) task_date.findViewById(R.id.item_info);
		task_date_info.setText(DateTimeUtils.getNowDateString());

		task_time = findViewById(R.id.taks_time);
		setItemIcon(task_time, R.drawable.icon_viewpage_time);
		setItemTitle(task_time, "时间");
		task_time_info = (TextView) task_time.findViewById(R.id.item_info);
		task_time_info.setText(DateTimeUtils.getNowTimeString());

		task_reminder = findViewById(R.id.taks_reminder);
		setItemIcon(task_reminder, R.drawable.icon_viewpage_reminder);
		setItemTitle(task_reminder, "提醒");
		task_reminder_info = (TextView) task_reminder
				.findViewById(R.id.item_info);
		task_reminder_info.setText("未设置提醒");

	}

	private void setItemTitle(View view, String titleInfo) {
		TextView title = (TextView) view.findViewById(R.id.item_title);
		title.setText(titleInfo);
	}

	private void setItemIcon(View view, int iconSourceID) {
		ImageView icon = (ImageView) view.findViewById(R.id.item_icon);
		icon.setBackgroundResource(iconSourceID);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.taks_date:
			setDateInfo();

			break;

		case R.id.taks_time:
			setTimeInfo();

			break;

		default:
			break;
		}

	}

	/**
	 * 设置时间
	 */
	private void setTimeInfo() {
		Calendar c = Calendar.getInstance();
		new TimePickerDialog(NewScheduleNetActivity.this, new OnTimeSetListener() {
			
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				String time=DateTimeUtils.toTimeString(hourOfDay, minute);
				task_time_info.setText(time);
			}
		}, c.get(Calendar.HOUR_OF_DAY),
				c.get(Calendar.MINUTE), true).show();

	}

	/**
	 * 设置日期
	 */
	private void setDateInfo() {
		Calendar c = Calendar.getInstance();
		new DatePickerDialog(NewScheduleNetActivity.this,
				new OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						String date = DateTimeUtils.toDateString(year,
								monthOfYear + 1, dayOfMonth);
						task_date_info.setText(date);

					}
				}, c.get(Calendar.YEAR), c.get(Calendar.MONTH),
				c.get(Calendar.DAY_OF_MONTH)).show();
		;

	}

}
