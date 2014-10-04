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

    public static void setFirstLaunch(Context context) {
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
