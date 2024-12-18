package kr.co.softcampus.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.content.Intent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class RoutineDetailActivity extends AppCompatActivity {
    TextView textRoutineDate;
    EditText editRoutineName;
    Button btnUpdateRoutineName, btnDeleteRoutine, btnAddExercise;
    ListView listRoutineExercises;
    DBHelper db;
    Routine routine;
    List<Exercise> exercises;
    ArrayAdapter<String> adapter;

    int routineId;

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        routine = db.getRoutineById(routineId);
        if (routine == null) {
            finish();
            return;
        }
        editRoutineName.setText(routine.getRoutineName());
        exercises = db.getExercisesForRoutine(routineId);
        String[] names = new String[exercises.size()];
        for (int i = 0; i < exercises.size(); i++) {
            names[i] = exercises.get(i).getName();
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
        listRoutineExercises.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_detail);
        db = DBHelper.getInstance(this);
        routineId = getIntent().getIntExtra("routineId", -1);

        textRoutineDate = findViewById(R.id.textRoutineDate);
        editRoutineName = findViewById(R.id.editRoutineName);
        btnUpdateRoutineName = findViewById(R.id.btnUpdateRoutineName);
        btnDeleteRoutine = findViewById(R.id.btnDeleteRoutine);
        listRoutineExercises = findViewById(R.id.listRoutineExercises);
        btnAddExercise = findViewById(R.id.btnAddExercise);

        String dateStr = new SimpleDateFormat("yyyy년 MM월 dd일").format(new Date());
        textRoutineDate.setText(dateStr + " 오늘의 운동루틴입니다.");

        btnUpdateRoutineName.setOnClickListener(v -> {
            String newName = editRoutineName.getText().toString();
            db.updateRoutineName(routineId, newName);
            Toast.makeText(this, "수정 완료", Toast.LENGTH_SHORT).show();
            loadData();
        });

        btnDeleteRoutine.setOnClickListener(v -> {
            db.deleteRoutine(routineId);
            Toast.makeText(this, "루틴 삭제", Toast.LENGTH_SHORT).show();
            finish();
        });

        listRoutineExercises.setOnItemClickListener((parent, view, position, id) -> {
            Exercise ex = exercises.get(position);
            Intent intent = new Intent(this, ExerciseDetailActivity.class);
            intent.putExtra("exerciseId", ex.getExerciseId());
            startActivity(intent);
        });

        listRoutineExercises.setOnItemLongClickListener((parent, view, position, id) -> {
            Exercise ex = exercises.get(position);
            db.removeExerciseFromRoutine(routineId, ex.getExerciseId());
            Toast.makeText(this, "운동 삭제", Toast.LENGTH_SHORT).show();
            loadData();
            return true;
        });

        btnAddExercise.setOnClickListener(v -> {
            Intent intent = new Intent(this, SelectExerciseActivity.class);
            intent.putExtra("routineId", routineId);
            startActivity(intent);
        });

        loadData();
    }
}
