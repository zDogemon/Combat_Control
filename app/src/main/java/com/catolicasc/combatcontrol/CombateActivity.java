package com.catolicasc.combatcontrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CombateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combate);

        final TextView txtRobo1Dano = findViewById(R.id.txtRobo1Dano);
        final TextView txtRobo2Dano = findViewById(R.id.txtRobo2Dano);
        final TextView txtRobo1Agressividade = findViewById(R.id.txtRobo1Agressividade);
        final TextView txtRobo2Agressividade = findViewById(R.id.txtRobo2Agressividade);
        final TextView txtCronometro = findViewById(R.id.txtCronometro);

        final Button btnRobo1Dano = findViewById(R.id.btnRobo1Dano);
        final Button btnRobo1Agressividade = findViewById(R.id.btnRobo1Agressividade);
        final Button btnRobo2Dano = findViewById(R.id.btnRobo2Dano);
        final Button btnRobo2Agressividade = findViewById(R.id.btnRobo2Agressividade);
        final Button btnRobo1Nocaute = findViewById(R.id.btnRobo1Nocaute);
        final Button btnRobo2Nocaute = findViewById(R.id.btnRobo2Nocaute);
        final Button btnPararCronometro = findViewById(R.id.btnPararCronometro);

        btnRobo1Dano.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                int dano1 = Integer.parseInt(txtRobo1Dano.getText().toString());
                int dano2 = Integer.parseInt(txtRobo2Dano.getText().toString());
                txtRobo1Dano.setText((dano1 + 1) + "");
                txtRobo2Dano.setText((dano2 - 1) + "");
            }
        });
    }
}
