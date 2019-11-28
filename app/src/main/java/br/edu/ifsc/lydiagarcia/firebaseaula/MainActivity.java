package br.edu.ifsc.lydiagarcia.firebaseaula;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    EditText etLogin, etSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etLogin = findViewById(R.id.etSenha);
        etSenha = findViewById(R.id.etLogin);

        mAuth = FirebaseAuth.getInstance();

        mAuth.signOut();

        // Create new account in Firebase
//        mAuth.createUserWithEmailAndPassword("icm2811@gmail.com", "12345");

        // Login with login and password in app
//        mAuth.signInWithEmailAndPassword("icm2801@gmail.com","12345").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(task.isSuccessful()){
//                    Toast.makeText(getApplicationContext(), mAuth.getCurrentUser().getEmail(), Toast.LENGTH_LONG).show();
//                } else{
//                    Toast.makeText(getApplicationContext(), "Falha no login", Toast.LENGTH_LONG).show();
//                }
//            }
//        });

//        FirebaseUser firebaseUser = mAuth.getCurrentUser();
//
//        if(firebaseUser != null){
//            Log.d("FirebaseUserExemplo", "Usuário logado: " + firebaseUser.getEmail());
//        } else {
//            Log.d("FirebaseUserExemplo", "Falha na autenticação");
//        }
    }

    public void login(View view) {

        String login = etLogin.getText().toString();
        String senha = etSenha.getText().toString();

        if(!login.trim().equals("")){
            mAuth.signInWithEmailAndPassword(login, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(), mAuth.getCurrentUser().getEmail(), Toast.LENGTH_LONG).show();
                    } else{
                        Toast.makeText(getApplicationContext(), "Falha no login", Toast.LENGTH_LONG).show();
                    }
                }
            });

            FirebaseUser firebaseUser = mAuth.getCurrentUser();

            if(firebaseUser != null){
                Intent i = new Intent(this, PrincipalActivity.class);
                startActivity(i);
            }
        }
    }

    public void activityCadastro(View view){
        Intent i = new Intent(this, Cadastro.class);
        startActivity(i);
    }

    public void recuperarSenha(View view){

        if(!etLogin.getText().toString().trim().equals("")){
            mAuth.sendPasswordResetEmail(etLogin.getText().toString());
        }

    }
}
