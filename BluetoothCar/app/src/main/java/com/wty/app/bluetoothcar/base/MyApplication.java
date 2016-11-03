package com.wty.app.bluetoothcar.base;

import android.app.Application;
import com.wty.app.bluetoothcar.utils.PreferenceUtil;

public class MyApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		PreferenceUtil.init(this);
	}

}
