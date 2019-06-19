package com.catolicasc.combatcontrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "EmailPassword";

    private Button btnCadastrar;
    private Button btnLogar;
    private EditText editLogin;
    private EditText editSenha;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //to-do

        btnCadastrar = findViewById(R.id.btnCadastrar);

       btnLogar = findViewById(R.id.btnLogar);
       btnLogar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               openActivity_Login();
           }
       });

       editSenha = findViewById(R.id.editSenha);
       editLogin = findViewById(R.id.editLogin);

       eventoClicks();


    }

    private void eventoClicks() {
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });

    }

    public void openActivity_Login(){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }




}
