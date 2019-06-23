package com.catolicasc.combatcontrol;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class LobbyActivity extends AppCompatActivity {

    private static final long START_TIME_IN_MILLIS = 180000;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    private Button btnEntar;
    private TextView txtCronometro;
    private Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby_);

        btnEntar = findViewById(R.id.btnEntrar);
        txtCronometro = findViewById(R.id.txtCronometro);
        btnVoltar = findViewById(R.id.btnVoltar);
        startTimer();
        startBotao();
        voltar();
    }

    private void voltar() {
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LobbyActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    private void startBotao() {
        btnEntar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LobbyActivity.this, CombateActivity.class);
                startActivity(i);
                finish();
            }
        });
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
                startActivity(new Intent(LobbyActivity.this, CombateActivity.class));
                btnEntar.setVisibility(View.INVISIBLE);
                finish();
            }

        }.start();
    }

    private void updateCountdownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);

        txtCronometro.setText(timeLeftFormatted);
    }


}

