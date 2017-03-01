package com.outfit.jure.outfit7apps;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.support.v4.app.NavUtils;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class InfoActivity extends Activity {
    ImageView imageView;
    TextView titleTV, packageTV, versionNameTV,versionCodeTV;
    String packageName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        imageView = (ImageView) findViewById(R.id.imageView);
        titleTV = (TextView) findViewById(R.id.titleTV);
        packageTV = (TextView) findViewById(R.id.packageNameTV);
        versionNameTV = (TextView) findViewById(R.id.versionNameTV);
        versionCodeTV = (TextView) findViewById(R.id.versionCodeTV);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        PackageManager pm = getPackageManager();
        try {
            packageName  = getIntent().getStringExtra(MainActivity.EXTRA_PACKAGE);
            String version_name  = getIntent().getStringExtra(MainActivity.EXTRA_VERSION_NAME);
            String title  = getIntent().getStringExtra(MainActivity.EXTRA_TITLE);
            int version_code  = getIntent().getIntExtra(MainActivity.EXTRA_VERSION_CODE,0);

            titleTV.setText(title);
            packageTV.setText(packageName);
            versionNameTV.setText(version_name);
            versionCodeTV.setText(Integer.toString(version_code));
            imageView.setImageDrawable(pm.getApplicationIcon(pm.getApplicationInfo(packageName, 0)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openMarket(View v){
        if(packageName!=null){
            Intent launchIntent = getPackageManager().getLaunchIntentForPackage(packageName);
            startActivity(launchIntent);
        }
    }
}
