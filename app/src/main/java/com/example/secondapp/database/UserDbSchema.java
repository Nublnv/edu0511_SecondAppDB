package com.example.secondapp.database;

public class UserDbSchema {
    public static class UserTable{
        public static final String NAME = "users";
        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String FIRSTNAME = "firstname";
            public static final String LASTNAME = "lastname";
            public static final String PHONE = "phone";
        }
    }
}
