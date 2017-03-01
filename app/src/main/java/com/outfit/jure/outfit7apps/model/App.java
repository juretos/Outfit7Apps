package com.outfit.jure.outfit7apps.model;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;

import java.io.Serializable;

/**
 * Created by Sorn stacionarc on 1.3.2017.
 */

public class App implements Serializable {

    private String name;
    private Drawable icon;
    private String package_name;
    private int version_code;
    private String version_name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVersion_code() {
        return version_code;
    }

    public void setVersion_code(int version_code) {
        this.version_code = version_code;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public String getVersion_name() {
        return version_name;
    }

    public void setVersion_name(String version_name) {
        this.version_name = version_name;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
