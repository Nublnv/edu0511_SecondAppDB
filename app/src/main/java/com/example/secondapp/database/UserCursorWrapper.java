package com.example.secondapp.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.secondapp.User;
import com.example.secondapp.database.UserDbSchema.UserTable.*;

import java.util.UUID;

public class UserCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public UserCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public User getUser(){
        String uuidString = getString(getColumnIndex(Cols.UUID));
        String firstName = getString(getColumnIndex(Cols.FIRSTNAME));
        String lastName = getString(getColumnIndex(Cols.LASTNAME));
        String phoneNumber = getString(getColumnIndex(Cols.PHONE));
        return new User(UUID.fromString(uuidString),firstName,lastName,phoneNumber);
    }
}
