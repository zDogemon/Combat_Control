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
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private EditText EditLogin;
    private EditText EditNome;
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

    private void eventoClicks() {

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = EditLogin.getText().toString().trim();
                String nome = EditNome.getText().toString().trim();
                String senha = EditSenha.getText().toString().trim();
                CriarUser (email, senha);
            }
        });


    }

    private void CriarUser(String email, String senha) {
        auth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            alert("Usuário cadastrado com sucesso");
                            Intent i = new Intent(RegisterActivity.this,MainActivity.class);
                            startActivity(i);
                            finish();
                        }else
                            alert("Erro ao cadastrar usuário");
                        }

                });
    }

    private void alert (String msg){
        Toast.makeText(RegisterActivity.this,msg,Toast.LENGTH_SHORT).show();
    }

    private void ininicializaComponentes() {

        EditLogin = (EditText) findViewById(R.id.editLogin);
        EditNome = (EditText) findViewById(R.id.editNome);
        EditSenha = (EditText) findViewById(R.id.editSenha);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexão.getFirebaseAuth();
    }
}


