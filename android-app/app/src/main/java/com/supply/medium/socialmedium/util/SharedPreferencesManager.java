package com.supply.medium.socialmedium.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created : Nisha Developed by : GraycellTechnologies Change history:07/19/2016
 * Used : To save/get data of local variable.
 */
public class SharedPreferencesManager {
	Context ctx;

	SharedPreferences prefs;

	public SharedPreferencesManager(Context ctx) {
		this.ctx = ctx;
		prefs = ctx.getSharedPreferences(ctx.getPackageName(),
				Context.MODE_PRIVATE);
	}


	public void saveStringValues(String key, String value) {
		Editor editor = prefs.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public String getStringValues(String key) {

		return prefs.getString(key, "");

	}

}
