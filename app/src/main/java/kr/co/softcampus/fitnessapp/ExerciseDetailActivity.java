package kr.co.softcampus.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;



public class ExerciseDetailActivity extends AppCompatActivity {
    TextView textExerciseName, textExerciseInfo;
    Button btnCloseExerciseDetail;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_detail);
        db = DBHelper.getInstance(this);

        textExerciseName = findViewById(R.id.textExerciseName);
        textExerciseInfo = findViewById(R.id.textExerciseInfo);
        btnCloseExerciseDetail = findViewById(R.id.btnCloseExerciseDetail);

        int exerciseId = getIntent().getIntExtra("exerciseId", -1);
        Exercise e = db.getExerciseById(exerciseId);
        if (e != null) {
            textExerciseName.setText(e.getName());
            textExerciseInfo.setText("에너지소비량: " + e.getEnergyConsumption() + "\n유형: " + e.getType());
        } else {
            textExerciseName.setText("운동 정보를 찾을 수 없습니다.");
        }

        btnCloseExerciseDetail.setOnClickListener(v -> {
            finish();
        });
    }
}

