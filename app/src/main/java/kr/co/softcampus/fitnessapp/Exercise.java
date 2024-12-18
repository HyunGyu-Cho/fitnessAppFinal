package kr.co.softcampus.fitnessapp;

public class Exercise {
    private int exerciseId;
    private String name;
    private float energyConsumption;
    private String type;

    public Exercise(int exerciseId, String name, float energyConsumption, String type) {
        this.exerciseId = exerciseId;
        this.name = name;
        this.energyConsumption = energyConsumption;
        this.type = type;
    }

    public int getExerciseId() { return exerciseId; }
    public String getName() { return name; }
    public float getEnergyConsumption() { return energyConsumption; }
    public String getType() { return type; }
}
