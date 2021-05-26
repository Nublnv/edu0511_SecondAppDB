package com.example.secondapp;

import java.io.Serializable;
import java.util.UUID;

public class User implements Serializable {
    private UUID uuid;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    public User(String firstName,String lastName, String phoneNumber)
    {
        this.uuid = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }
    public User(UUID uuid, String  firstName,String lastName, String phoneNumber)
    {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public User()
    {
        this.uuid = UUID.randomUUID();
    }

    public UUID getUuid() { return uuid; }
    public String getName() { return lastName + " " + firstName; }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
