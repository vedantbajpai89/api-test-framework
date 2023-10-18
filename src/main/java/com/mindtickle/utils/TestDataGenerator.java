package com.mindtickle.utils;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class TestDataGenerator {
    Faker faker;
    public TestDataGenerator(){
         faker = new Faker();
    }

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

    public String getPetName(){return faker.animal().name();}

    public List<String> getUrls(){
        List<String> urlList = new ArrayList<>();
        int range = faker.number().numberBetween(0,5);
        for(int index = 0; index < range; index++){
            urlList.add(faker.lorem().fixedString(4));
        }
        return urlList;
    }

    public int getRandomRange(){
        return faker.number().numberBetween(0,6);
    }

    public String getPetStatus(){
        int number = faker.number().numberBetween(0,2);
        String[] status = {"available","pending","sold"};
        return status[number];
    }
}
