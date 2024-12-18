package kr.co.softcampus.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;

public class CreateRoutineActivity extends AppCompatActivity {
    EditText editNewRoutineName;
    Button btnCreateRoutineOk;
    DBHelper db;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_routine);
        db = DBHelper.getInstance(this);
        user = db.getLoggedInUser();

        editNewRoutineName = findViewById(R.id.editNewRoutineName);
        btnCreateRoutineOk = findViewById(R.id.btnCreateRoutineOk);

        btnCreateRoutineOk.setOnClickListener(v -> {
            String name = editNewRoutineName.getText().toString();
            db.createRoutine(user.getUserId(), name);
            Toast.makeText(this, "루틴 생성 완료", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}

