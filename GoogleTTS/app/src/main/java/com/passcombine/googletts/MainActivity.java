package com.passcombine.googletts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {


    private TextToSpeech tts;
    private Button btn_speak, btn_tone_up, btn_tone_down, btn_speed_up, btn_speed_down;
    private EditText txtText;


    float tone = 0.6f;
    float speech_speed = 1.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        init();
    }


    public void init(){

        tts = new TextToSpeech(this, this);
        btn_speak = findViewById(R.id.btn_speak);
        txtText = findViewById(R.id.txtText);

        btn_tone_up = findViewById(R.id.btn_tone_up);
        btn_tone_down = findViewById(R.id.btn_tone_down);
        btn_speed_up = findViewById(R.id.btn_speed_up);
        btn_speed_down = findViewById(R.id.btn_speed_down);




        btn_tone_up.setOnClickListener(listener);
        btn_tone_down.setOnClickListener(listener);
        btn_speed_up.setOnClickListener(listener);
        btn_speed_down.setOnClickListener(listener);
        btn_speak.setOnClickListener(listener);

    }


    private void speckOut(){
        CharSequence text = txtText.getText();
        tts.setPitch(tone); //목소리 톤
        tts.setSpeechRate(speech_speed); //스피치 속도
//        tts.setLanguage(Locale.KOREA); //언어설정
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "id1");


        Toast.makeText(MainActivity.this, "현재 tone: "+ Math.round(tone*100)/100.0 +" speech speed : "+Math.round(speech_speed*100)/100.0 , Toast.LENGTH_SHORT).show();

    }



    @Override
    protected void onDestroy() {


        if(tts != null){
            tts.stop();
            tts.shutdown();
        }

        super.onDestroy();

    }

    @Override
    public void onInit(int status) {

        if(status == TextToSpeech.SUCCESS){

            int result = tts.setLanguage(Locale.KOREA);

            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS", "This Language is not supported");

            }else {
                btn_speak.setEnabled(true);
                speckOut();
            }
        }else {
            Log.e("TTS", "Initilization Failed!");
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()){


                case R.id.btn_tone_up:

                    tone += 0.1f;



                    break;
                case R.id.btn_tone_down:

                    tone -= 0.1f;

                    break;
                case R.id.btn_speed_up:
                    speech_speed += 0.1f;

                    break;
                case R.id.btn_speed_down:
                    speech_speed -= 0.1f;

                    break;

                case R.id.btn_speak:
                    speckOut();
                    break;
            }
        }
    };
}