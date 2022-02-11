package com.passcombine.qrpos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    IntentIntegrator integrator = new IntentIntegrator(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        integrator.setCameraId(1);

        integrator.initiateScan();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode, data);

        if(result != null){

            if(result.getContents() == null){
                Toast.makeText(this, "취소되었습니다.", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "스캔 : "+ result.getContents(), Toast.LENGTH_SHORT).show();

                Log.e("TAG ", "Q : "+ result.getContents());

            }

        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }
        integrator.initiateScan();

    }
}