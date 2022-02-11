package com.passcombine.androidid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static MainActivity mActivity;

    TextView textVIew;

    String TAG = MainActivity.class.getSimpleName();

    ArrayList<String> arr = new ArrayList<>();

    ListAdapter adapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mActivity = this;

        textVIew = findViewById(R.id.textVIew);

        listView = findViewById(R.id.listview);

        textVIew.setText(Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));


        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> ris = getPackageManager().queryIntentActivities(intent, 0);

        if (ris != null) {
            for (ResolveInfo ri : ris) {
                String packageName = ri.activityInfo.packageName;
                String label = ri.loadLabel(getPackageManager()).toString();
                String className = ri.activityInfo.name;
                Drawable icon = ri.loadIcon(getPackageManager());

                arr.add(label+"@"+packageName+"@"+className);

                Log.e(TAG, "Package name: " + packageName +
                        "\nLabel:" + label +
                        "\nActivityName:" + className);
            }
        }




        adapter = new ListAdapter(arr);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {


                if(! openApp(MainActivity.this, arr.get(position).split("@")[1])){

                    Toast.makeText(MainActivity.this, "앱을 실행할 수 없습니다", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }



    public boolean openApp(Context context, String packageName) {
        PackageManager manager = context.getPackageManager();
        try {
            Intent i = manager.getLaunchIntentForPackage(packageName);
            if (i == null) {
                throw new PackageManager.NameNotFoundException();
            }
            i.addCategory(Intent.CATEGORY_LAUNCHER);
            context.startActivity(i);
            return true;
        }catch(PackageManager.NameNotFoundException e){
            e.printStackTrace();
            return false;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }




}