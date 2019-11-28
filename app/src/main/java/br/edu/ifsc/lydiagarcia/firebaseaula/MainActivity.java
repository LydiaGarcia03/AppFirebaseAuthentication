package br.edu.ifsc.lydiagarcia.firebaseaula;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        mAuth.signOut();

        // Create new account in Firebase
        mAuth.createUserWithEmailAndPassword("icm2801@gmail.com", "12345");

        // Login with login and password in app
        mAuth.signInWithEmailAndPassword("icm2801@gmail.com","12345").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
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
            Log.d("FirebaseUserExemplo", "Usuário logado: " + firebaseUser.getEmail());
        } else {
            Log.d("FIrebaseUserExemplo", "Falha na autenticação");
        }


    }

    public void login(View view) {



    }
}
