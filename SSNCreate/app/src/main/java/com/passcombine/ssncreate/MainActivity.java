package com.passcombine.ssncreate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    TextView tv_ssn;

    Button btn_create;

    Random rnd = new Random();

    int[] ssn = new int[12];



    String TAG = MainActivity.class.getSimpleName();

    StringBuffer buffer = new StringBuffer();


    int ssn_total = 0;

    int last_ssn_num = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_ssn = findViewById(R.id.tv_ssn);
        btn_create = findViewById(R.id.btn_create);








        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buffer = new StringBuffer();

                ssn_total = 0;
                for(int i = 0 ; i<ssn.length; i ++){


                    switch (i){


                        case 0:
                            ssn[i] = rnd.nextInt(10);
                            break;

                        case 1:
                            ssn[i] = rnd.nextInt(10);
                            break;

                        case 2:

                            ssn[i] = rnd.nextInt(2);
                            break;

                        case 3:
                            if(ssn[2] == 1 ){
                                ssn[i] = rnd.nextInt(3);
                            }else if(ssn[2] == 0) {
                                ssn[i] = rnd.nextInt(9 - 1 + 1);
                            }
                            else{
                                ssn[i] = rnd.nextInt(10);
                            }

                            break;

                        case 4:
                            ssn[i] = rnd.nextInt(4);
                            break;
                        case 5:

                            if(ssn[4] == 3 ){
                                ssn[i] = rnd.nextInt(2);
                            }
                            else if(ssn[4] == 0) {
                                ssn[i] = rnd.nextInt(  9 - 1 + 1);
                            }
                            else {
                                ssn[i] = rnd.nextInt(10);
                            }
                            break;

                        case 6:
                            ;
                            ssn[i] = rnd.nextInt((2 - 1 + 1)) + 1;
                            break;

                        case 7:
                            ssn[i] = rnd.nextInt(10);
                            break;

                        case 8:
                            ssn[i] = rnd.nextInt(10);
                            break;

                        case 9:
                            ssn[i] = rnd.nextInt(10);
                            break;

                        case 10:
                            ssn[i] = rnd.nextInt(10);
                            break;

                        case 11:
                            ssn[i] = rnd.nextInt(10);
                            break;

                        case 12:
                            ssn[i] = rnd.nextInt(10);
                            break;

                    }

                    buffer.append(ssn[i]+"");


                    int add = i+2;

                    if(add >= 10) {
                        add = add - 8;
                    }
                    int sum = ssn[i] * add;




                    ssn_total += sum;
                }




                last_ssn_num = 11 - ssn_total%11;


                buffer.append(last_ssn_num+"");

                String txt  =buffer.substring(0,6)+"-"+buffer.substring(6,13) ;


                Log.e(TAG , "CHECK : "+ txt);

                tv_ssn.setText(txt);




//                ssn_total= 0;
//
//                for( int i = 0 ; i < ssnTest.length ; i++){
//
//
//                    int add = i+2;
//
//                    if(add >= 10) {
//                        add = add - 8;
//                    }
//                    int sum = ssnTest[i] * add;
//
//
//
//                    ssn_total += sum;
//
//
//                }

//                Log.e(TAG , "CEHCK : "+ ssn_total);

            }
        });




    }
}