package com.webservice.app.webservice.dao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

public class User {

    @ApiModelProperty(notes = "Firstname of the User")
    private String firstName;

    @ApiModelProperty(notes = "Lastname of the User")
    private String lastName;

    @ApiModelProperty(notes = "Age of the User")
    private int age;

    public User(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
