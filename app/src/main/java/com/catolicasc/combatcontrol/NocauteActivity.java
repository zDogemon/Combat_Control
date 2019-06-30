package com.catolicasc.combatcontrol;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.widget.TextView;


public class NocauteActivity extends AppCompatActivity {

    private static final long START_TIME_IN_MILLIS = 10000;

    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;

    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    TextView txtNocaute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nocaute);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*.8), (int) (height*.1));

        txtNocaute = findViewById(R.id.txtNocaute);
        startTimer();
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountdownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
            }
        }.start();

        mTimerRunning = true;
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
    }

    private void updateCountdownText() {
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        if (seconds == 0) {
            Nocaute.getInstance().nocaute = true;
            pauseTimer();
            finish();
        }

        txtNocaute.setText("Nocaute em " + seconds + "...");
    }

    @Override
    public void onBackPressed() {
        Nocaute.getInstance().nocaute = false;
        pauseTimer();
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Nocaute.getInstance().nocaute = false;
        pauseTimer();
        finish();
        return super.onTouchEvent(event);
    }

}
