package com.hallie.myday.bmob;

import android.content.Context;
import android.content.Intent;
import cn.bmob.v3.listener.SaveListener;

import com.hallie.myday.HomeLocalActivity;
import com.hallie.myday.HomeNetActivity;
import com.hallie.myday.domain.ScheduleUser;
import com.hallie.myday.util.ToastUtil;

public class BmobUserDao {
	/**
	 * ��¼
	 * 
	 * @param username
	 *            �û���
	 * @param password
	 *            ����
	 */
	public static void login(final Context context,String username, String password) {
		ScheduleUser user = new ScheduleUser();
		user.setUsername(username);
		user.setPassword(password);
		user.login(context, new SaveListener() {

			@Override
			public void onSuccess() {
				Intent intent = new Intent(context,
						HomeNetActivity.class);
				context.startActivity(intent);
				ToastUtil.toast(context, "��¼�ɹ�!");

			}

			@Override
			public void onFailure(int arg0, String arg1) {

				ToastUtil.toast(context, arg1+"��¼ʧ��");

			}
		});

	}

}
