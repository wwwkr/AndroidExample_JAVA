package com.passcombine.imagecaptureactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    FrameLayout rootLayout;

    ImageView imageView;
    String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootLayout = (FrameLayout)findViewById(R.id.rootLayout);
        imageView = findViewById(R.id.imageView);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            int permissionResult = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if(permissionResult== PackageManager.PERMISSION_DENIED){
                String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

                requestPermissions(permissions,100);
            }
        }



        MyView myView = new MyView(this);


        rootLayout.addView(myView);


        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(0xFFFF0000);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(12);



        mEmboss = new EmbossMaskFilter(new float[] { 1, 1, 1 },
                0.4f, 6, 3.5f);
        mBlur = new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL);

    }



    public void mOnCaptureClick(View v){
        //전체화면
        View rootView = getWindow().getDecorView();

        File screenShot = ScreenShot(rootView);
//        if(screenShot!=null){
//            //갤러리에 추가
//            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(screenShot)));
//        }


        try{
            Uri uri = Uri.parse(screenShot.getPath());
            imageView.setImageURI(uri);
        }catch (Exception e){

        }

    }




    //화면 캡쳐하기
    public File ScreenShot(View view){
        view.setDrawingCacheEnabled(true);  //화면에 뿌릴때 캐시를 사용하게 한다

        Bitmap screenBitmap = view.getDrawingCache();   //캐시를 비트맵으로 변환

//        String filename = "screenshot.png";


       Log.e(TAG ,  "QQ : "+ screenBitmap);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String fileName = "IMG_"+sdf.format(new Date())+".jpg";

        File file = new File(Environment.getExternalStorageDirectory()+"/Pictures", fileName);  //Pictures폴더 screenshot.png 파일
        FileOutputStream os = null;
        try{
            os = new FileOutputStream(file);
            screenBitmap.compress(Bitmap.CompressFormat.PNG, 90, os);   //비트맵을 PNG파일로 변환
            os.close();
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }

        view.setDrawingCacheEnabled(false);

        return file;
    }



    private Paint mPaint;
    private MaskFilter mEmboss;
    private MaskFilter mBlur;

    public void colorChanged(int color) {
        mPaint.setColor(color);
    }


    public class MyView extends View {

        private static final float MINP = 0.25f;
        private static final float MAXP = 0.75f;
        private Bitmap mBitmap;
        private Canvas mCanvas;
        private Path mPath;
        private Paint mBitmapPaint;
        public MyView(Context c) {
            super(c);
            mPath = new Path();
            mBitmapPaint = new Paint(Paint.DITHER_FLAG);

        }

        public MyView(Context c , AttributeSet att){
            super(c , att);
            mPath = new Path();
            mBitmapPaint = new Paint(Paint.DITHER_FLAG);
        }

        public MyView(Context c , AttributeSet att , int ref){
            super(c , att , ref);
            mPath = new Path();
            mBitmapPaint = new Paint(Paint.DITHER_FLAG);
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            mCanvas = new Canvas(mBitmap);
        }
        @Override
        protected void onDraw(Canvas canvas) {
//            canvas.drawColor(0xFFAAAAAA);
            canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
            canvas.drawPath(mPath, mPaint);
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.SCREEN);
        }
        private float mX, mY;
        private static final float TOUCH_TOLERANCE = 4;
        private void touch_start(float x, float y) {
            mPath.reset();
            mPath.moveTo(x, y);
            mX = x;
            mY = y;
        }
        private void touch_move(float x, float y) {
            float dx = Math.abs(x - mX);
            float dy = Math.abs(y - mY);
            if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
                mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
                mX = x;
                mY = y;
            }
        }
        private void touch_up() {
            mPath.lineTo(mX, mY);
            // commit the path to our offscreen
            mCanvas.drawPath(mPath, mPaint);
            // kill this so we don't double draw
            mPath.reset();
        }
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX();
            float y = event.getY();
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    touch_start(x, y);
                    invalidate();
                    break;
                case MotionEvent.ACTION_MOVE:
                    touch_move(x, y);
                    invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    touch_up();
                    invalidate();
                    break;
            }
            return true;
        }


    }
}