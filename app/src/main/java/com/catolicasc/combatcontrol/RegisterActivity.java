package com.catolicasc.combatcontrol;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    public EditText EditLogin;
    private EditText EditSenha;
    private Button btnCadastrar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ininicializaComponentes();
        eventoClicks();

    }

    public void eventoClicks() {

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = EditLogin.getText().toString().trim();
                String senha = EditSenha.getText().toString().trim();

            }
        });


    }

    private void CriarUser(String email, String senha) {
        try {
            auth.createUserWithEmailAndPassword(email, senha)
                    .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                alert("Usuário cadastrado com sucesso");
                                Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(i);
                                finish();
                            } else
                                alert("Erro ao cadastrar usuário");
                        }

                    });
        }catch (Exception e) {
            alert("Preencha todos os campos");
        }
    }

    private void alert (String msg){
        Toast.makeText(RegisterActivity.this,msg,Toast.LENGTH_SHORT).show();
    }

    private void ininicializaComponentes() {

        EditLogin = (EditText) findViewById(R.id.editLogin);
        EditSenha = (EditText) findViewById(R.id.editSenha);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Connection.getFirebaseAuth();
    }
}


