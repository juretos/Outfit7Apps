package com.outfit.jure.outfit7apps;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.outfit.jure.outfit7apps.adapter.ApplicationArrayAdapter;
import com.outfit.jure.outfit7apps.model.App;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {
    private String TAG = MainActivity.class.getSimpleName();
    public final static String EXTRA_PACKAGE = "com.outfit.PACKAGE";
    public static final String EXTRA_VERSION_CODE = "com.outfit.version.CODE";
    public static final String EXTRA_VERSION_NAME = "com.outfit.version.NAME";
    public static final String EXTRA_TITLE = "com.outfit.TITLE";
    private ArrayList<App> appList;
    private ApplicationArrayAdapter listadaptor;
    private String OUTFIT7_PACKAGE = "com.outfit7";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new LoadApplications().execute();
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        //get selected items
        App app = (App) getListAdapter().getItem(position);
        Intent intent = new Intent(MainActivity.this,InfoActivity.class);
        intent.putExtra(EXTRA_PACKAGE, app.getPackage_name());
        intent.putExtra(EXTRA_TITLE, app.getName());
        intent.putExtra(EXTRA_VERSION_CODE, app.getVersion_code());
        intent.putExtra(EXTRA_VERSION_NAME, app.getVersion_name());
        startActivity(intent);
    }

    private ArrayList<App> getApps(){
        ArrayList<App> list = new ArrayList<App>();
        PackageManager pm = getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);

        for (int i = 0; i < packs.size(); i++) {
            PackageInfo packageInfo = packs.get(i);
            ApplicationInfo appInfo = packageInfo.applicationInfo;

            if(appInfo.packageName.startsWith(OUTFIT7_PACKAGE)){
                App app = new App();
                app.setName(appInfo.loadLabel(pm).toString());
                app.setPackage_name(appInfo.packageName);
                app.setIcon(appInfo.loadIcon(pm));
                app.setVersion_code(packageInfo.versionCode);
                app.setVersion_name(packageInfo.versionName);
                list.add(app);
            }
        }
        return list;
    }


    private class LoadApplications extends AsyncTask<Void, Void, Void> {
        private ProgressDialog progress = null;

        @Override
        protected Void doInBackground(Void... params) {
            appList = getApps();
            listadaptor = new ApplicationArrayAdapter(MainActivity.this,appList);
            return null;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onPostExecute(Void result) {
            setListAdapter(listadaptor);
            progress.dismiss();
            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(MainActivity.this, null,
                    "Loading application info...");
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
