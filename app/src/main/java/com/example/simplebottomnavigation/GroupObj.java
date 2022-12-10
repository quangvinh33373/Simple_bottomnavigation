package com.example.simplebottomnavigation;

public class GroupObj {
    int id;
    String name;

    public GroupObj(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public GroupObj() {
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

    @Override
    public String toString() {
        return name;
    }
}
