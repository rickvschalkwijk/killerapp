/*
 * Copyright 2012 Hogeschool van Amsterdam.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.app.amsterguide;

import com.app.killerapp.R;

import search.SearchMainActivity;
import services.ServicesContactActivity;
import core.map.MapActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	public static final String PREFS_NAME = "LocalPrefs";
	public static boolean startUp = true;
	private static MainActivity selfReferance = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		if (startUp) {
			selfReferance = this;
			SharedPreferences.Editor editor = settings.edit();
			editor.putBoolean("loggedIn", false);
			editor.putBoolean("loggedInGuest", false);
			editor.commit();
			startUp = false;
		}
		if (!settings.getBoolean("loggedIn", false) && !settings.getBoolean("loggedInGuest", false)) {
			Intent intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
		}
	}

	public void openMap(View view) {
		Intent intent = new Intent(this, MapActivity.class);
		startActivity(intent);
	}

	public void openFriend(View view) {
		Intent intent = new Intent(this, FriendActivity.class);
		startActivity(intent);
	}

	public void gotoServices(View view) {
		Intent intent = new Intent(this, ServicesContactActivity.class);
		startActivity(intent);
	}
	
	public void openSearch(View view) {
		Intent intent = new Intent(this, SearchMainActivity.class);
		startActivity(intent);
	}
	
	public static Context getContext()
	{
		if (selfReferance != null)
		{
			return selfReferance.getApplicationContext();
		}
		return null;
	}

	@Override
	protected void onStart() {
		super.onStart();

	}

	@Override
	protected void onPause() {
		super.onPause();

	}

	@Override
	protected void onResume() {
		super.onResume();

	}

	@Override
	protected void onStop() {
		super.onStop();

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}