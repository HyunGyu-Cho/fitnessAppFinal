package kr.co.softcampus.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.content.Intent;
import java.util.List;
import android.view.View;
import android.widget.AdapterView;

public class MainActivity extends AppCompatActivity {
    TextView textWelcome;
    TextView textTodayRoutineTitle;
    Spinner spinnerRoutines;
    ListView listSelectedRoutineExercises;
    Button btnGoRoutineManage;
    DBHelper db;
    List<Routine> userRoutines;
    List<Exercise> selectedRoutineExercises;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = DBHelper.getInstance(this);
        if (db.getLoggedInUser() == null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }
        setContentView(R.layout.activity_main);

        textWelcome = findViewById(R.id.textWelcome);
        textTodayRoutineTitle = findViewById(R.id.textTodayRoutineTitle);
        spinnerRoutines = findViewById(R.id.spinnerRoutines);
        listSelectedRoutineExercises = findViewById(R.id.listSelectedRoutineExercises);
        btnGoRoutineManage = findViewById(R.id.btnGoRoutineManage);

        user = db.getLoggedInUser();
        textWelcome.setText("안녕하세요 " + user.getName() + "님");

        // 사용자 루틴 목록 불러오기
        userRoutines = db.getUserRoutines(user.getUserId());

        // 루틴 이름 목록 만들기
        String[] routineNames = new String[userRoutines.size()];
        for (int i = 0; i < userRoutines.size(); i++) {
            routineNames[i] = userRoutines.get(i).getRoutineName();
        }

        // Spinner에 루틴 목록 표시
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, routineNames);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRoutines.setAdapter(spinnerAdapter);

        // Spinner 선택 리스너
        spinnerRoutines.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Routine selectedRoutine = userRoutines.get(position);
                selectedRoutineExercises = db.getExercisesForRoutine(selectedRoutine.getRoutineId());

                String[] exerciseNames = new String[selectedRoutineExercises.size()];
                for (int i = 0; i < selectedRoutineExercises.size(); i++) {
                    exerciseNames[i] = selectedRoutineExercises.get(i).getName();
                }

                listSelectedRoutineExercises.setAdapter(new ArrayAdapter<>(MainActivity.this,
                        android.R.layout.simple_list_item_1, exerciseNames));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                listSelectedRoutineExercises.setAdapter(null);
            }
        });

        btnGoRoutineManage.setOnClickListener(v -> {
            startActivity(new Intent(this, RoutineListActivity.class));
        });
    }
}
