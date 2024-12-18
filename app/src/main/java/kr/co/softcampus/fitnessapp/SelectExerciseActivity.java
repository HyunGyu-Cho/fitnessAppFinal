package kr.co.softcampus.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import java.util.List;

public class SelectExerciseActivity extends AppCompatActivity {
    ListView listAllExercises;
    DBHelper db;
    int routineId;
    List<Exercise> allExercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_exercise);
        db = DBHelper.getInstance(this);
        routineId = getIntent().getIntExtra("routineId", -1);

        listAllExercises = findViewById(R.id.listAllExercises);

        allExercises = db.getAllExercises();
        String[] names = new String[allExercises.size()];
        for (int i = 0; i < allExercises.size(); i++) {
            Exercise ex = allExercises.get(i);
            // 운동명 (유형) 형태로 표시
            names[i] = ex.getName() + " (" + ex.getType() + ")";
        }
        listAllExercises.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names));

        listAllExercises.setOnItemClickListener((parent, view, position, id) -> {
            Exercise ex = allExercises.get(position);
            db.addExerciseToRoutine(routineId, ex.getExerciseId());
            Toast.makeText(this, "운동 추가됨: " + ex.getName(), Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
