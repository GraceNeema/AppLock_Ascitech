package com.example.lenovo.applock_ascitech;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView userInstalledApps;
    PackageManager manager;
    List<AppList> installedApps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userInstalledApps = (ListView)findViewById(R.id.app_list);
       // startService(new Intent(this, CheckAppService.class));
        Intent intent = new Intent(MainActivity.this, CheckAppService.class);
        startService(intent);
        manager =getPackageManager();
        installedApps = getInstalledApps();

        List_Adapter installedAppAdapter = new List_Adapter(MainActivity.this, installedApps);
        userInstalledApps.setAdapter(installedAppAdapter);

        //checkApps(this);


    }


    private List<AppList> getInstalledApps() {
        List<AppList> res = new ArrayList<AppList>();

        List<PackageInfo> packs = manager.getInstalledPackages(0);
        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);
            if ((isSystemPackage(p) == false)) {

                Log.i("tag",p.applicationInfo.packageName);
                String appName = p.applicationInfo.loadLabel(getPackageManager()).toString();
                Drawable icon = p.applicationInfo.loadIcon(getPackageManager());
                res.add(new AppList(appName, icon,  p.applicationInfo.packageName));


            }
        }
        return res;
    }

    private boolean isSystemPackage(PackageInfo pkgInfo) {
        return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP ) != 0) ? true : false; // Applications systèmes & Applications installées par l'utilisateur
    }

}



