package kr.co.softcampus.fitnessapp;

public class RoutineExercise {
    private int id;
    private int routineId;
    private int exerciseId;

    public RoutineExercise(int id, int routineId, int exerciseId) {
        this.id = id;
        this.routineId = routineId;
        this.exerciseId = exerciseId;
    }

    public int getId() { return id; }
    public int getRoutineId() { return routineId; }
    public int getExerciseId() { return exerciseId; }
}
