package com.example.lenovo.applock_ascitech;

import android.graphics.drawable.Drawable;

/**
 * Created by Lenovo on 11/21/2018.
 */

public class AppList {
    private String name;
    Drawable icon;
    private String package_name;


    private boolean isSelected;

    public AppList(String name, Drawable icon, String package_name) {
        this.name = name;
        this.icon = icon;
        this.package_name = package_name;
    }

    public String getName() {
        return name;
    }

    public Drawable getIcon() {
        return icon;
    }
    public boolean getSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }
}
