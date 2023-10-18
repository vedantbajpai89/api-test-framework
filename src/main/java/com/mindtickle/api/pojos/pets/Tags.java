package com.mindtickle.api.pojos.pets;

import lombok.experimental.Accessors;

@Accessors(chain = true)
public class Tags {
    private int id;
    private String name;

    public Tags() {
    }

    public Tags(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and setters
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
}
