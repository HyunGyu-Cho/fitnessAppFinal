package kr.co.softcampus.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.content.Intent;

public class LoginActivity extends AppCompatActivity {
    EditText editLoginId, editLoginPassword;
    Button btnLogin, btnGoRegister;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = DBHelper.getInstance(this);

        editLoginId = findViewById(R.id.editLoginId);
        editLoginPassword = findViewById(R.id.editLoginPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnGoRegister = findViewById(R.id.btnGoRegister);

        btnLogin.setOnClickListener(v -> {
            String id = editLoginId.getText().toString();
            String pw = editLoginPassword.getText().toString();
            if (db.login(id, pw)) {
                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show();
            }
        });

        btnGoRegister.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });
    }
}