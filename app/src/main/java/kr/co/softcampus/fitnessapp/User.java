package kr.co.softcampus.fitnessapp;

public class User {
    private int userId;
    private String name;
    private int age;
    private float height;
    private float weight;
    private String loginId;
    private String password;

    public User(int userId, String name, int age, float height, float weight, String loginId, String password) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.loginId = loginId;
        this.password = password;
    }

    public int getUserId() { return userId; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public float getHeight() { return height; }
    public float getWeight() { return weight; }
    public String getLoginId() { return loginId; }
    public String getPassword() { return password; }
}
