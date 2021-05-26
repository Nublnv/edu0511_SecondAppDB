package com.example.secondapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.secondapp.User;
import com.example.secondapp.database.UserCursorWrapper;
import com.example.secondapp.database.UserDbHelper;
import com.example.secondapp.database.UserDbSchema.*;
import com.example.secondapp.database.UserDbSchema.UserTable.*;

import java.util.ArrayList;

public class Users {
    private static Users instance;
    private ArrayList<User> _users;
    private SQLiteDatabase _database;
    private Context _context;

    public Users()
    {
        Log.d("SYSTEM INFO","Method Users() called");
        _users = new ArrayList<>();
        _users.add(new User("Misha","","+79161570495"));
        _users.add(new User("Masha","","+79161570584"));
        _users.add(new User("Milena","","+79161570216"));
        _users.add(new User("Galya","","+79161570155"));
        _users.add(new User("Serega","","+79161570747"));
        Log.d("SYSTEM INFO", "Method Users() ended");
    }

    public static Users get(Context context)
    {
        if(instance == null)
        {
            instance = new Users(context);
        }
        return instance;
    }

    public Users(Context context) {
        Log.d("SYSTEM INFO", "Method Users(Context) called");
        this._context = context.getApplicationContext();
        this._database = new UserDbHelper(_context).getWritableDatabase();
        this._users = new ArrayList<>();
        Log.d("SYSTEM INFO", "Method Users(Context) ended");
    }

    public void addUser(User user){
        Log.d("SYSTEM INFO", "Method addUser(User user) called");
        _database.insert(UserTable.NAME,null,getContentValues(user));
        Log.d("SYSTEM INFO", "Method addUser(User user) ended");
    }

    public void delete(User user)
    {
        _database.delete(UserTable.NAME,Cols.UUID + "= ?", new String[]{ user.getUuid().toString() });
    }

    public void update(User user)
    {
        _database.update(UserTable.NAME,getContentValues(user),Cols.UUID + "= ?", new String[]{ user.getUuid().toString() });
    }

    private static ContentValues getContentValues(User user)
    {
        Log.d("SYSTEM INFO", "Method getContentValues(User user) called");
        ContentValues values = new ContentValues();
        values.put(Cols.UUID,user.getUuid().toString());
        values.put(Cols.FIRSTNAME,user.getFirstName());
        values.put(Cols.LASTNAME,user.getLastName());
        values.put(Cols.PHONE,user.getPhoneNumber());
        Log.d("SYSTEM INFO", "Method getContentValues(User user) ended");
        return values;
    }

    public ArrayList<User> get_users() {
        Log.d("SYSTEM INFO", "Method get_users() called");
        add();
        if (_users.size() == 0)
        {
            Log.d("SYSTEM INFO", "Method get_users() if (_users.size() == 0) called");
            addUser(new User("Misha","G","+79161570495"));
            addUser(new User("Masha","G","+79161570584"));
            addUser(new User("Milena","V","+79161570216"));
            addUser(new User("Galya","T","+79161570155"));
            addUser(new User("Serega","P","+79161570747"));
            add();
            Log.d("SYSTEM INFO", "Method get_users() if (_users.size() == 0) ended");
        }
        Log.d("SYSTEM INFO", "Method get_users() ended");
        return _users;
    }

    public void add(User user)
    {
        _users.add(user);
    }

    private UserCursorWrapper queryUsers()
    {
        Log.d("SYSTEM INFO", "Method queryUsers() called");
        Cursor cursor = _database.query(UserTable.NAME,null,null, null,null,null,null);
        Log.d("SYSTEM INFO", "Method queryUsers() ended");
        return new UserCursorWrapper(cursor);
    }

    private void add()
    {
        Log.d("SYSTEM INFO", "Method add() called");
        try (UserCursorWrapper cursorWrapper = queryUsers()) {
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast())
            {
                _users.add(cursorWrapper.getUser());
                cursorWrapper.moveToNext();
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        Log.d("SYSTEM INFO", "Method add() ended");
    }
}
