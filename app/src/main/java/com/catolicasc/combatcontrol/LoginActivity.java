package com.catolicasc.combatcontrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    private Button btnCadastrar;
    private Button btnLogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //to-do

        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity_Register();
            }
        });

       btnLogar = findViewById(R.id.btnLogar);
       btnLogar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               openActivity_Login();
           }
       });
    }


    public void openActivity_Register(){

        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);

    }

    public void openActivity_Login(){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
