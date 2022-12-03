package com.example.ballanimatedgame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private ImageView imageView;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    public static int x = 0;
    public static int y = 0;
    public static int z = 0;
    private TextView xTxt, yTxt, zTxt;

    int screenwidth;
    int screenheight;
    int maxValueW;
    int maxValueH;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        xTxt = findViewById(R.id.x_axis);
        yTxt = findViewById(R.id.y_axis);
        zTxt = findViewById(R.id.z_axis);
        //   requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Bitmap myBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
        Bitmap tempBitmap = Bitmap.createBitmap(myBitmap.getWidth(), myBitmap.getHeight(), Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(tempBitmap);
        Drawable drawable = imageView.getDrawable();
        drawable.setBounds(20, 30, drawable.getIntrinsicWidth() + 20, drawable.getIntrinsicHeight() + 30);
        drawable.draw(canvas);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        screenwidth = metrics.widthPixels;
        screenheight = metrics.heightPixels;

        maxValueW = screenwidth - 281;
        maxValueH = screenheight - 281;

        Log.d("TAG", "onCreate: " + screenwidth + "," + screenheight);
        Log.d("TAG", "onCreateBitmap: " + myBitmap.getWidth() + "," + myBitmap.getHeight());

        //1080,2177
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            Log.d("TAG", "onCreateValues: " +event.values[0]);
            Log.d("TAG", "onCreateValues: " + event.values[1]);

            x -= (int) event.values[0];
            y += (int) event.values[1];
            z += (int) event.values[2];

            xTxt.setText("x = " + x);
            yTxt.setText("y = " + y);
            zTxt.setText("z = " + z);

            if (y < maxValueH && y > 0)
                imageView.setY(y);
            if (x < maxValueW && x > 0)
                imageView.setX(x);

            imageView.setZ(z);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }


}