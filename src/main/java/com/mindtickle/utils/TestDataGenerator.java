package com.mindtickle.utils;

import com.github.javafaker.Faker;

public class TestDataGenerator {
    Faker faker;
    public TestDataGenerator(){
         faker = new Faker();
    }

    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int id;
    private int userStatus;


    public String getUserName() {
        return faker.name().username();
    }

    public String getFirstName() {
        return faker.name().firstName();
    }

    public String getLastName() {
        return faker.name().lastName();
    }

    public String getEmail() {
        return faker.internet().emailAddress();
    }

    public String getPassword() {
        return faker.internet().password();
    }

    public String getPhone() {
        return faker.phoneNumber().phoneNumber();
    }

    public int getId() {
        return faker.number().numberBetween(0,100);
    }

    public int getUserStatus() {
        return faker.number().numberBetween(0,100);
    }
}
