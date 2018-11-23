package com.example.lenovo.applock_ascitech;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Service;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

/**
 * Created by Lenovo on 11/22/2018.
 */

public class CheckAppService extends Service {
    Context c;
    public CheckAppService() {
        super();
    }

    @Override
    public IBinder onBind(Intent intent) {
       return null;
    }

    @Override
    public void onCreate() {
       // Toast.makeText(this, "Invoke background service onCreate method.", Toast.LENGTH_LONG).show();
        super.onCreate();

    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
         final String TAG = "com.example.lenovo.applock_ascitech";
        Timer timer  =  new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {

                String currentApp = "NULL";
                if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    UsageStatsManager usm = (UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE);
                    long time = System.currentTimeMillis();
                    List<UsageStats> appList = usm.queryUsageStats(UsageStatsManager.INTERVAL_DAILY,  time - 1000*1000, time);
                    if (appList != null && appList.size() > 0) {
                        SortedMap<Long, UsageStats> mySortedMap = new TreeMap<Long, UsageStats>();
                        for (UsageStats usageStats : appList) {
                            mySortedMap.put(usageStats.getLastTimeUsed(), usageStats);
                        }
                        if (mySortedMap != null && !mySortedMap.isEmpty()) {
                            currentApp = mySortedMap.get(mySortedMap.lastKey()).getPackageName();
                        }
                    }
                } else {
                    ActivityManager am = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
                    List<ActivityManager.RunningAppProcessInfo> tasks = am.getRunningAppProcesses();
                    currentApp = tasks.get(0).processName;

                }

                Log.e(TAG, "Current App in foreground is: " + currentApp);
                if(currentApp.equals("com.android.documentsui")){
                    Intent lockIntent = new Intent(getApplicationContext(), LockScreen.class);
                    lockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(lockIntent);

                }
            }
        },2000,3000);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, " service is destroyed", Toast.LENGTH_LONG).show();
    }


}
