package org.module.springmvc.model;

public class User {
    private int id;
    private String name;
    private int total_points;

    public User() {
        super();
    }
    public User(int id, String name, int total_points) {
        this.id = id;
        this.name = name;
        this.total_points = total_points;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotal_points() {
        return total_points;
    }

    public void setTotal_points(int total_points) {
        this.total_points = total_points;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", total_points=" + total_points +
                '}';
    }
}
