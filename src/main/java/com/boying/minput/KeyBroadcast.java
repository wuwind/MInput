package com.boying.minput;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class KeyBroadcast extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		int key = intent.getIntExtra("key", -1);
		System.out.println("onReceive"+key);
	}

}
