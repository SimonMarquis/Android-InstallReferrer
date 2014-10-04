/*
 * Copyright (C) 2014 Simon Marquis (http://www.simon-marquis.fr)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package fr.simon.marquis.installreferrer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.util.Date;


public class ReferrerReceiver extends BroadcastReceiver {
    public static final String ACTION_UPDATE_DATA = "ACTION_UPDATE_DATA";
    private static final String ACTION_INSTALL_REFERRER = "com.android.vending.INSTALL_REFERRER";
    private static final String KEY_REFERRER = "referrer";

    public ReferrerReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            Log.e("ReferrerReceiver", "Intent is null");
            return;
        }
        if (!ACTION_INSTALL_REFERRER.equals(intent.getAction())) {
            Log.e("ReferrerReceiver", "Wrong action! Expected: " + ACTION_INSTALL_REFERRER + " but was: " + intent.getAction());
            return;
        }
        Bundle extras = intent.getExtras();
        if (intent.getExtras() == null) {
            Log.e("ReferrerReceiver", "No data in intent");
            return;
        }

        Application.setReferrerDate(context.getApplicationContext(), new Date().getTime());
        Application.setReferrerData(context.getApplicationContext(), (String) extras.get(KEY_REFERRER));

        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(ACTION_UPDATE_DATA));
    }
}
