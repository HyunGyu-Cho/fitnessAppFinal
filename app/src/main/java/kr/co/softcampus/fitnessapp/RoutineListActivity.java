package kr.co.softcampus.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.content.Intent;
import java.util.List;


public class RoutineListActivity extends AppCompatActivity {
    ListView listUserRoutines;
    Button btnCreateRoutine, btnBackToMain;
    DBHelper db;
    User user;
    List<Routine> userRoutines;

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        userRoutines = db.getUserRoutines(user.getUserId());
        String[] routineNames = new String[userRoutines.size()];
        for (int i = 0; i < userRoutines.size(); i++) {
            routineNames[i] = userRoutines.get(i).getRoutineName();
        }
        listUserRoutines.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, routineNames));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_list);
        db = DBHelper.getInstance(this);
        user = db.getLoggedInUser();
        if (user == null) {
            finish();
            return;
        }

        listUserRoutines = findViewById(R.id.listUserRoutines);
        btnCreateRoutine = findViewById(R.id.btnCreateRoutine);
        btnBackToMain = findViewById(R.id.btnBackToMain);

        btnCreateRoutine.setOnClickListener(v -> {
            startActivity(new Intent(this, CreateRoutineActivity.class));
        });

        listUserRoutines.setOnItemClickListener((parent, view, position, id) -> {
            Routine selected = userRoutines.get(position);
            Intent intent = new Intent(this, RoutineDetailActivity.class);
            intent.putExtra("routineId", selected.getRoutineId());
            startActivity(intent);
        });

        btnBackToMain.setOnClickListener(v -> {
            finish();
        });

        loadData();
    }
}
