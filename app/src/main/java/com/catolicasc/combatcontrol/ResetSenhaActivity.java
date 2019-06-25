package com.catolicasc.combatcontrol;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class ResetSenhaActivity extends AppCompatActivity {

    private Button btnReset;
    private Button btnVoltar;
    private EditText edtEmailReset;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_senha);

        mAuth = FirebaseAuth.getInstance();

        btnVoltar = findViewById(R.id.btnVoltar);

        btnReset = (Button) findViewById(R.id.btnReset);
        edtEmailReset = (EditText) findViewById(R.id.edtEmailReset);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String emailreset = edtEmailReset.getText().toString();

                if (TextUtils.isEmpty(emailreset))
                {
                    Toast.makeText(ResetSenhaActivity.this, "Por favor escreva seu endereço de e-mail", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    mAuth.sendPasswordResetEmail(emailreset).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(ResetSenhaActivity.this, "Por favor verificar seu endereço de e-mail", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ResetSenhaActivity.this, LoginActivity.class));
                            }
                            else
                            {
                                String message = task.getException().getMessage();
                                Toast.makeText(ResetSenhaActivity.this, "E-mail inválido" + message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }


        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResetSenhaActivity.this.finish();
            }
        });
    }


}
