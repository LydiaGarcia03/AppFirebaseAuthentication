package br.edu.ifsc.lydiagarcia.firebaseaula;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Cadastro extends AppCompatActivity {

    FirebaseAuth mAuth;
    EditText etLogin, etSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        etLogin = findViewById(R.id.etNovoLogin);
        etSenha = findViewById(R.id.etNovaSenha);

        mAuth = FirebaseAuth.getInstance();
    }

    public void cadastrar(View view) {

        if(!etLogin.getText().toString().trim().equals("")){
            // Create new account in Firebase
            mAuth.createUserWithEmailAndPassword(etLogin.getText().toString(), etSenha.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(), mAuth.getCurrentUser().getEmail(), Toast.LENGTH_LONG).show();
                    } else{
                        Toast.makeText(getApplicationContext(), "Falha no cadastro", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

    }
}
