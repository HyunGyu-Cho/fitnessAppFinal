package kr.co.softcampus.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;


public class RegisterActivity extends AppCompatActivity {
    EditText editName, editAge, editHeight, editWeight, editRegisterId, editRegisterPw;
    Button btnRegisterComplete;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = DBHelper.getInstance(this);

        editName = findViewById(R.id.editName);
        editAge = findViewById(R.id.editAge);
        editHeight = findViewById(R.id.editHeight);
        editWeight = findViewById(R.id.editWeight);
        editRegisterId = findViewById(R.id.editRegisterId);
        editRegisterPw = findViewById(R.id.editRegisterPw);
        btnRegisterComplete = findViewById(R.id.btnRegisterComplete);

        btnRegisterComplete.setOnClickListener(v -> {
            String name = editName.getText().toString();
            int age = Integer.parseInt(editAge.getText().toString());
            float height = Float.parseFloat(editHeight.getText().toString());
            float weight = Float.parseFloat(editWeight.getText().toString());
            String loginId = editRegisterId.getText().toString();
            String pw = editRegisterPw.getText().toString();

            boolean success = db.registerUser(name, age, height, weight, loginId, pw);
            if (success) {
                Toast.makeText(this, "회원가입 완료", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "이미 존재하는 ID입니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
