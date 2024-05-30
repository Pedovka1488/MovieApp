package com.example.movie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.movie.databinding.ActivityLoginBinding;
import com.example.movie.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import javax.net.ssl.SSLSessionBindingEvent;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());


        binding.btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.edEmail.getText().toString().isEmpty() || binding.edPassword.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Field cannot be empty", Toast.LENGTH_SHORT).show();
                }else{
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(binding.edEmail.getText().toString(),binding.edPassword.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){

                                        HashMap<String, String> userInfo = new HashMap<>();
                                        userInfo.put("email",binding.edEmail.getText().toString());
                                        FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(userInfo);

                                        startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                                    }
                                }
                            });
                }

            }
        });


        binding.clickToLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });



    }
}