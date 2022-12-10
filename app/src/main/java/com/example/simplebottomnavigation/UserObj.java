package com.example.simplebottomnavigation;

public class UserObj {
    int id;
    String name,birth;

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

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public UserObj(int id, String name, String birth) {
        this.id = id;
        this.name = name;
        this.birth = birth;
    }

    public UserObj() {
    }
}
