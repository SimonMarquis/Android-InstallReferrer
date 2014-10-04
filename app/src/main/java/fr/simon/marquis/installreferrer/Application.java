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

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Application extends android.app.Application {

    private static final String FIRST_LAUNCH = "FIRST_LAUNCH";
    private static final String REFERRER_DATE = "REFERRER_DATE";
    private static final String REFERRER_DATA = "REFERRER_DATA";

    @Override
    public void onCreate() {
        super.onCreate();
        setFirstLaunch(this);
    }

    private static void setFirstLaunch(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        if (!sp.contains(FIRST_LAUNCH)) {
            sp.edit().putLong(FIRST_LAUNCH, new Date().getTime()).apply();
        }
    }

    public static String getFirstLaunch(Context context) {
        Date date = new Date(PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()).getLong(FIRST_LAUNCH, new Date().getTime()));
        return DateFormat.getDateInstance().format(date) + " - " + new SimpleDateFormat("HH:mm:ss.SSS").format(date);
    }

    public static boolean isReferrerDetected(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()).contains(REFERRER_DATE);
    }

    public static void setReferrerDate(Context context, long date) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        if (!sp.contains(REFERRER_DATE)) {
            sp.edit().putLong(REFERRER_DATE, date).apply();
        }
    }

    public static String getReferrerDate(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        if (!sp.contains(REFERRER_DATE)) {
            return "Undefined";
        }

        Date date = new Date(sp.getLong(REFERRER_DATE, new Date().getTime()));
        return DateFormat.getDateInstance().format(date) + " - " + new SimpleDateFormat("HH:mm:ss.SSS").format(date);
    }

    public static void setReferrerData(Context context, String data) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        if (!sp.contains(REFERRER_DATA)) {
            sp.edit().putString(REFERRER_DATA, data).apply();
        }
    }

    public static String getReferrerDataRaw(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        if (!sp.contains(REFERRER_DATA)) {
            return "Undefined";
        }
        return sp.getString(REFERRER_DATA, null);
    }

    public static String getReferrerDataDecoded(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        String raw = sp.getString(REFERRER_DATA, null);

        if (raw == null) {
            return null;
        }

        try {
            String url = URLDecoder.decode(raw, "utf-8");
            try {
                String url2x = URLDecoder.decode(url, "utf-8");
                if (raw.equals(url2x)) {
                    return null;
                }
                return url2x;
            } catch (UnsupportedEncodingException uee) {
                // not URL 2x encoded but URL encoded
                if (raw.equals(url)) {
                    return null;
                }
                return url;
            }
        } catch (UnsupportedEncodingException uee) {
            // not URL encoded
        }
        return null;
    }

}
