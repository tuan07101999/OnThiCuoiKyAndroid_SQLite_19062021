package com.example.booksqlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText edtEmailRegis,edtPasswordRegis;
    Button btnSignUp;
    FirebaseAuth auth;
    TextView txtbacktoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtEmailRegis = findViewById(R.id.edtEmailRegister);
        edtPasswordRegis = findViewById(R.id.edtEmailRegister);
        btnSignUp = findViewById(R.id.btnRegister);
        txtbacktoLogin = findViewById(R.id.txtBacktoLogin);
        auth = FirebaseAuth.getInstance();

        txtbacktoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmailRegis.getText().toString().trim();
                String password = edtPasswordRegis.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    edtPasswordRegis.setError("Hãy nhập Email");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    edtPasswordRegis.setError("Hãy Nhập Password");
                    return;
                }
                if(password.length() < 6)
                {
                    edtPasswordRegis.setError("Password trên 6 số");
                    return;
                }
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener( RegisterActivity.this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful())
                            Toast.makeText(RegisterActivity.this, "Registry failed....", Toast.LENGTH_SHORT).show();
                        else {
                            Toast.makeText(RegisterActivity.this, "Đăng kí thành công tài khoản: "+ email, Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                            finish();
                        }
                    }
                });
            }
        });

    }
}