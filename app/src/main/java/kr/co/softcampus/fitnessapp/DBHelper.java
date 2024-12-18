package kr.co.softcampus.fitnessapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static DBHelper instance;
    private static final String DB_NAME = "fitness.db";
    private static final int DB_VERSION = 1;

    private User loggedInUser = null;

    public static synchronized DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context.getApplicationContext());
        }
        return instance;
    }

    private DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users (" +
                "user_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "age INTEGER," +
                "height REAL," +
                "weight REAL," +
                "login_id TEXT UNIQUE," +
                "password TEXT)");

        db.execSQL("CREATE TABLE exercises (" +
                "exercise_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "energy_consumption REAL," +
                "type TEXT)");

        db.execSQL("CREATE TABLE routines (" +
                "routine_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user_id INTEGER," +
                "routine_name TEXT," +
                "FOREIGN KEY(user_id) REFERENCES users(user_id))");

        db.execSQL("CREATE TABLE routine_exercises (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "routine_id INTEGER," +
                "exercise_id INTEGER," +
                "FOREIGN KEY(routine_id) REFERENCES routines(routine_id)," +
                "FOREIGN KEY(exercise_id) REFERENCES exercises(exercise_id))");

        // 전체 운동 데이터 INSERT
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('바벨 스쿼트',6,'하체')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('스트레칭',2.5,'전신')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('푸쉬업(가볍게)',3,'가슴')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('푸쉬업(격렬하게)',8,'가슴')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('레그레이즈',5,'하체')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('윗몸일으키기(가볍게)',2.8,'복근')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('윗몸일으키기(격렬하게)',8,'복근')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('크런치(가볍게)',3,'복근')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('크런치(격렬하게)',8,'복근')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('스쿼트',5.5,'하체')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('싸이클',7,'유산소')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('체스트 프레스',5,'가슴')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('제자리뛰기',5.5,'유산소')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('트랙걷기',4.5,'유산소')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('운동장 걷기',4.5,'유산소')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('걷기',4.5,'유산소')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('런닝머신(달리기)',9,'유산소')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('레그프레스',5,'하체')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('계단내려가기',3,'유산소')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('계단오르기',8,'유산소')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('턱걸이(보통으로)',5,'등')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('빠르게 걷기',6,'유산소')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('점프 스쿼트',6,'하체')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('코어운동',4.5,'복근')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('렛풀다운',5,'등')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('리버스컬',5,'하체')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('타바타 운동',8,'유산소')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('런닝머신(걷기)',4.5,'유산소')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('시티드 로우',5,'등')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('원 암 덤벨로우',5,'등')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('풀 스쿼트',6,'하체')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('암풀다운',4,'등')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('이두컬',5,'이두')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('해머컬',5,'이두')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('와이드 스쿼트',5.5,'하체')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('인클라인 벤치프레스',5,'가슴')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('스탠딩 카프 레이즈',5,'하체')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('백 익스텐션',3.5,'하체')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('사이드 크런치',4,'복근')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('벤트오버 레터럴 레이즈',5.5,'어깨')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('덤벨 숄더프레스',5,'어깨')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('벤트 오버 바벨 로우',5.5,'등')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('레그컬',5,'하체')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('루마니안 데드리프트',5,'등')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('백 스쿼트',6,'하체')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('15초인터벌트레이닝!',8.8,'유산소')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('스쿼트 덤벨 프레스',7,'하체')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('벤치 프레스',5,'가슴')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('덤벨 풀오버',5.5,'가슴')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('크로스 크런치',4.5,'복근')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('컨벤셔널 데드리프트',8,'등')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('사이드 플랭크',3.8,'복근')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('고블릿 스쿼트',5.5,'하체')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('시티드 니업',5,'복근')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('데드 리프트',5.5,'등')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('딥스' ,3,'삼두')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('사이드 레터럴 레이즈',5,'어깨')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('풀업(턱걸이)',5,'등')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('케이블 플라이',5,'가슴')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('덤벨컬',5,'이두')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('바벨 숄더프레스',5,'어깨')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('케이블 푸시다운',5,'삼두')");
        db.execSQL("INSERT INTO exercises(name,energy_consumption,type) VALUES('덤벨 킥백',5,'삼두')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS routine_exercises");
        db.execSQL("DROP TABLE IF EXISTS routines");
        db.execSQL("DROP TABLE IF EXISTS exercises");
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    public boolean registerUser(String name, int age, float height, float weight, String loginId, String password) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("SELECT user_id FROM users WHERE login_id=?", new String[]{loginId});
        if (c.moveToFirst()) {
            c.close();
            return false;
        }
        c.close();

        db.execSQL("INSERT INTO users(name, age, height, weight, login_id, password) VALUES(?,?,?,?,?,?)",
                new Object[]{name, age, height, weight, loginId, password});
        return true;
    }

    public boolean login(String loginId, String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT user_id,name,age,height,weight,login_id,password FROM users WHERE login_id=? AND password=?",
                new String[]{loginId, password});
        if (c.moveToFirst()) {
            int userId = c.getInt(c.getColumnIndexOrThrow("user_id"));
            String name = c.getString(c.getColumnIndexOrThrow("name"));
            int age = c.getInt(c.getColumnIndexOrThrow("age"));
            float height = c.getFloat(c.getColumnIndexOrThrow("height"));
            float weight = c.getFloat(c.getColumnIndexOrThrow("weight"));
            String lid = c.getString(c.getColumnIndexOrThrow("login_id"));
            String pw = c.getString(c.getColumnIndexOrThrow("password"));
            loggedInUser = new User(userId, name, age, height, weight, lid, pw);
            c.close();
            return true;
        }
        c.close();
        return false;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void logout() {
        loggedInUser = null;
    }

    public List<Routine> getUserRoutines(int userId) {
        List<Routine> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT routine_id, routine_name FROM routines WHERE user_id=?", new String[]{String.valueOf(userId)});
        while (c.moveToNext()) {
            int rid = c.getInt(c.getColumnIndexOrThrow("routine_id"));
            String rname = c.getString(c.getColumnIndexOrThrow("routine_name"));
            list.add(new Routine(rid, userId, rname));
        }
        c.close();
        return list;
    }

    public int createRoutine(int userId, String name) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO routines(user_id, routine_name) VALUES(?,?)", new Object[]{userId, name});
        Cursor c = db.rawQuery("SELECT last_insert_rowid()", null);
        int newId = -1;
        if (c.moveToFirst()) {
            newId = c.getInt(0);
        }
        c.close();
        return newId;
    }

    public Routine getRoutineById(int routineId) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT user_id, routine_name FROM routines WHERE routine_id=?", new String[]{String.valueOf(routineId)});
        Routine r = null;
        if (c.moveToFirst()) {
            int uid = c.getInt(c.getColumnIndexOrThrow("user_id"));
            String rname = c.getString(c.getColumnIndexOrThrow("routine_name"));
            r = new Routine(routineId, uid, rname);
        }
        c.close();
        return r;
    }

    public void updateRoutineName(int routineId, String newName) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE routines SET routine_name=? WHERE routine_id=?", new Object[]{newName, routineId});
    }

    public void deleteRoutine(int routineId) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM routine_exercises WHERE routine_id=?", new Object[]{routineId});
        db.execSQL("DELETE FROM routines WHERE routine_id=?", new Object[]{routineId});
    }

    public void addExerciseToRoutine(int routineId, int exerciseId) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO routine_exercises(routine_id,exercise_id) VALUES(?,?)", new Object[]{routineId, exerciseId});
    }

    public List<Exercise> getExercisesForRoutine(int routineId) {
        List<Exercise> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT e.exercise_id, e.name, e.energy_consumption, e.type " +
                "FROM routine_exercises re JOIN exercises e ON re.exercise_id=e.exercise_id " +
                "WHERE re.routine_id=?", new String[]{String.valueOf(routineId)});
        while (c.moveToNext()) {
            int eid = c.getInt(c.getColumnIndexOrThrow("exercise_id"));
            String ename = c.getString(c.getColumnIndexOrThrow("name"));
            float eco = c.getFloat(c.getColumnIndexOrThrow("energy_consumption"));
            String etype = c.getString(c.getColumnIndexOrThrow("type"));
            list.add(new Exercise(eid, ename, eco, etype));
        }
        c.close();
        return list;
    }

    public void removeExerciseFromRoutine(int routineId, int exerciseId) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM routine_exercises WHERE routine_id=? AND exercise_id=?", new Object[]{routineId, exerciseId});
    }

    public Exercise getExerciseById(int exerciseId) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT name,energy_consumption,type FROM exercises WHERE exercise_id=?", new String[]{String.valueOf(exerciseId)});
        Exercise e = null;
        if (c.moveToFirst()) {
            String n = c.getString(c.getColumnIndexOrThrow("name"));
            float ec = c.getFloat(c.getColumnIndexOrThrow("energy_consumption"));
            String t = c.getString(c.getColumnIndexOrThrow("type"));
            e = new Exercise(exerciseId, n, ec, t);
        }
        c.close();
        return e;
    }

    public List<Exercise> getAllExercises() {
        List<Exercise> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT exercise_id,name,energy_consumption,type FROM exercises", null);
        while (c.moveToNext()) {
            int eid = c.getInt(c.getColumnIndexOrThrow("exercise_id"));
            String n = c.getString(c.getColumnIndexOrThrow("name"));
            float ec = c.getFloat(c.getColumnIndexOrThrow("energy_consumption"));
            String t = c.getString(c.getColumnIndexOrThrow("type"));
            list.add(new Exercise(eid, n, ec, t));
        }
        c.close();
        return list;
    }
}
