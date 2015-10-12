package com.lsp.test.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

/**
 * Created by xian on 2015/10/12.
 */
public class AlarmUtil {
    public static void addAlarmBroadcast(Context context, long intervalTime, String action) {
        AlarmManager alarmManager = (AlarmManager) context.getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, new Intent(action), PendingIntent.FLAG_UPDATE_CURRENT);
        long triggerTime = SystemClock.elapsedRealtime();// + intervalTime;
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, triggerTime, intervalTime, pendingIntent);
    }

    public static void removeAlarmBroadcast(Context context, String action) {
        AlarmManager alarmManager = (AlarmManager) context.getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, new Intent(action), PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);
    }
}
