package com.example.demo;

public class SimplePayload {
    private String name;
    private int age;
    private String Occupation;

    public SimplePayload(String name, int age, String occupation) {
        this.name = name;
        this.age = age;
        Occupation = occupation;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getOccupation() {
        return Occupation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setOccupation(String occupation) {
        Occupation = occupation;
    }

    @Override
    public String toString() {
        return "SimplePayload{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", Occupation='" + Occupation + '\'' +
                '}';
    }
}
