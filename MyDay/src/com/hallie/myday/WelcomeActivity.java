package com.hallie.myday;

import com.hallie.myday.domain.ScheduleType;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

public class WelcomeActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		addDefaultType();// ����Ĭ���¼�����
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		enterLogin();
	}

	private void enterLogin() {
		Intent intent=new Intent(WelcomeActivity.this, LoginActivity.class);
		startActivity(intent);
		finish();
		
	}

	/**
	 * ����Ĭ���¼�����
	 */
	private void addDefaultType() {
		ScheduleType type = new ScheduleType();
		if (TextUtils.isEmpty(type.getTableName())) {// ��������Ѿ����ڣ�˵���Ѿ���ӹ�������Ҫ�����
			for (int i = 0; i < ConstantValue.defaultType.length; i++) {
				type.setType(ConstantValue.defaultType[i]);
				type.save(WelcomeActivity.this);
			}
		}
	}
}
