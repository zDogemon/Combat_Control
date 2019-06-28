package com.catolicasc.combatcontrol;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class CombateActivity extends AppCompatActivity {

    private static final long START_TIME_IN_MILLIS = 180000;

    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;

    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    private TextView txtRobo1;
    private TextView txtRobo2;
    private TextView txtRobo1Dano;
    private TextView txtRobo2Dano;
    private TextView txtRobo1Agressividade;
    private TextView txtRobo2Agressividade;
    private TextView txtCronometro;

    private Button btnRobo1Dano;
    private Button btnRobo1Agressividade;
    private Button btnRobo2Dano;
    private Button btnRobo2Agressividade;
    private Button btnRobo1Nocaute;
    private Button btnRobo2Nocaute;
    private Button btnCronometro;
    private Button btnEncerrarPartida;

    private boolean nocaute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combate);

        txtRobo1 = findViewById(R.id.txtRobo1);
        txtRobo2 = findViewById(R.id.txtRobo2);
        txtRobo1Dano = findViewById(R.id.txtRobo1Dano);
        txtRobo2Dano = findViewById(R.id.txtRobo2Dano);
        txtRobo1Agressividade = findViewById(R.id.txtRobo1Agressividade);
        txtRobo2Agressividade = findViewById(R.id.txtRobo2Agressividade);
        txtCronometro = findViewById(R.id.txtCronometro);

        btnRobo1Dano = findViewById(R.id.btnRobo1Dano);
        btnRobo1Agressividade = findViewById(R.id.btnRobo1Agressividade);
        btnRobo2Dano = findViewById(R.id.btnRobo2Dano);
        btnRobo2Agressividade = findViewById(R.id.btnRobo2Agressividade);
        btnRobo1Nocaute = findViewById(R.id.btnRobo1Nocaute);
        btnRobo2Nocaute = findViewById(R.id.btnRobo2Nocaute);
        btnCronometro = findViewById(R.id.btnPararCronometro);
        btnEncerrarPartida = findViewById(R.id.btnEncerrarPartida);

        nocaute = false;

        btnCronometro.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if (mTimerRunning){
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        Intent i = getIntent();
        String robo1 = i.getStringExtra("robo1");
        String robo2 = i.getStringExtra("robo2");
        txtRobo1.setText(robo1);
        txtRobo2.setText(robo2);

        updateCountdownText();
        startTimer();

        // Botão de Dano do Robô 1
        btnRobo1Dano.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                int dano1 = Integer.parseInt(txtRobo1Dano.getText().toString());
                int dano2 = Integer.parseInt(txtRobo2Dano.getText().toString());
                if (dano1 < 6){
                    txtRobo1Dano.setText((dano1 + 1) + "");
                    txtRobo2Dano.setText((dano2 - 1) + "");
                }
            }
        });

        // Botão de Dano do Robô 2
        btnRobo2Dano.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                int dano1 = Integer.parseInt(txtRobo1Dano.getText().toString());
                int dano2 = Integer.parseInt(txtRobo2Dano.getText().toString());
                if (dano2 < 6){
                    txtRobo1Dano.setText((dano1 - 1) + "");
                    txtRobo2Dano.setText((dano2 + 1) + "");
                }
            }
        });

        // Botão de Agressividade do Robô 1
        btnRobo1Agressividade.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                int agressividade1 = Integer.parseInt(txtRobo1Agressividade.getText().toString());
                int agressividade2 = Integer.parseInt(txtRobo2Agressividade.getText().toString());
                if (agressividade1 < 5){
                    txtRobo1Agressividade.setText((agressividade1 + 1) + "");
                    txtRobo2Agressividade.setText((agressividade2 - 1) + "");
                }
            }
        });

        // Botão de Agressividade do Robô 2
        btnRobo2Agressividade.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                int agressividade1 = Integer.parseInt(txtRobo1Agressividade.getText().toString());
                int agressividade2 = Integer.parseInt(txtRobo2Agressividade.getText().toString());
                if (agressividade2 < 5){
                    txtRobo1Agressividade.setText((agressividade1 - 1) + "");
                    txtRobo2Agressividade.setText((agressividade2 + 1) + "");
                }
            }
        });

        // Botão de Nocaute do Robô 1
        btnRobo1Nocaute.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                nocaute = true;
            }
        });

        // Botão de Nocaute do Robô 2
        btnRobo2Nocaute.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                nocaute = true;
            }
        });

        // Botão de encerrar a partida
        btnEncerrarPartida.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent it = new Intent(CombateActivity.this, EncerramentoActivity.class);
                int pontuacao1 = (Integer.parseInt(txtRobo1Agressividade.getText().toString())) + (Integer.parseInt(txtRobo1Dano.getText().toString()));
                int pontuacao2 = (Integer.parseInt(txtRobo2Agressividade.getText().toString())) + (Integer.parseInt(txtRobo2Dano.getText().toString()));
                if (pontuacao1 > pontuacao2) {
                    it.putExtra("vencedor", txtRobo1.getText().toString());
                } else {
                    it.putExtra("vencedor", txtRobo2.getText().toString());
                }
                it.putExtra("pontuacao1", pontuacao1 + "");
                it.putExtra("pontuacao2", pontuacao2 + "");
                if (nocaute == true) it.putExtra("nocaute", "Nocaute!");
                else it.putExtra("nocaute", " ");

                startActivity(it);
                CombateActivity.this.finish();
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
                mTimerRunning = false;
                btnCronometro.setVisibility(View.INVISIBLE);
            }
        }.start();

        mTimerRunning = true;
        btnCronometro.setText("Pausar Cronômetro");
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        btnCronometro.setText("Iniciar Cronômetro");

    }

    private void updateCountdownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);

        txtCronometro.setText(timeLeftFormatted);
    }

}
