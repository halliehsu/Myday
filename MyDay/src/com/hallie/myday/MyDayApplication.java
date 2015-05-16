package com.hallie.myday;

import com.bmob.BmobConfiguration;
import com.bmob.BmobPro;

import android.app.Application;
import android.content.Context;
import cn.bmob.v3.Bmob;

public class MyDayApplication extends Application {

	private static MyDayApplication mApp = null;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mApp = this;
		// 初始化BmobSDK
		Bmob.initialize(getApplicationContext(), ConstantValue.BMOB_ID);

		initConfig(getApplicationContext());
	}

	/**
	 * 初始化配置文件
	 * 
	 * @param Context
	 */
	private void initConfig(Context context) {
		BmobConfiguration config = new BmobConfiguration.Builder(context)
				.customExternalCacheDir("Smile").build();
		BmobPro.getInstance(context).initConfig(config);

	}

	public static MyDayApplication getmApp() {
		return mApp;
	}

	public static Context context() {
		return mApp.getApplicationContext();
	}

}
