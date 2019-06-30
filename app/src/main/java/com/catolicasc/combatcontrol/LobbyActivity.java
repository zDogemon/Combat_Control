package com.catolicasc.combatcontrol;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class LobbyActivity extends AppCompatActivity {

    private static final long START_TIME_IN_MILLIS = 300000;
    private CountDownTimer mCountDownTimer;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    private Button btnEntrar;
    private TextView txtCronometro;
    private Button btnVoltar;

    String robo1a;
    String robo2a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby_);

        btnEntrar = findViewById(R.id.btnEntrar);
        txtCronometro = findViewById(R.id.txtCronometro);
        btnVoltar = findViewById(R.id.btnVoltar);

        Intent intent = getIntent();
        robo1a = intent.getStringExtra("robo1");
        robo2a = intent.getStringExtra("robo2");

        startTimer();
        startBotao();
        voltar();
    }

    private void voltar() {
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void startBotao() {
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LobbyActivity.this, CombateActivity.class);
                i.putExtra("robo1", robo1a);
                i.putExtra("robo2", robo2a);
                pauseTimer();
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
                btnEntrar.setVisibility(View.INVISIBLE);
                finish();
            }

        }.start();
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
    }

    @Override
    public void onBackPressed() {
        pauseTimer();
        finish();
    }

    private void updateCountdownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);

        txtCronometro.setText(timeLeftFormatted);
    }


}

