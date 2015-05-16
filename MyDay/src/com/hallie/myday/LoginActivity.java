package com.hallie.myday;


import com.hallie.myday.bmob.BmobUserDao;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity implements OnClickListener {
	private Button login_submit;
	private EditText login_password;
	private EditText login_username;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_login);
		init();// 加载布局
		BmobUserDao.login(LoginActivity.this, "hallie", "hallie");
		login_submit.setOnClickListener(this);

	}

	


	/**
	 * 加载布局
	 */
	private void init() {
		login_username = (EditText) findViewById(R.id.login_username);
		login_password = (EditText) findViewById(R.id.login_password);
		login_submit = (Button) findViewById(R.id.login_submit);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_submit:
			String username = login_username.getText().toString().trim();
			String password = login_password.getText().toString().trim();
			BmobUserDao.login(LoginActivity.this, username, password);

			break;

		default:
			break;
		}

	}

}
