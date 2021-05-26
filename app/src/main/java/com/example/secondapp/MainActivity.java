package com.example.secondapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button addUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerViewMain);
        ArrayList<User> users = new Users(MainActivity.this).get_users();
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(new UserAdapter(users));
        addUserButton = findViewById(R.id.addUserButton);
        addUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddUserActivity.class));
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        ArrayList<User> users = new Users(MainActivity.this).get_users();
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(new UserAdapter(users));
    }

    private void changeUser(User user){
        Intent intent = new Intent(MainActivity.this, ChangeUserActivity.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }

    private void deleteUser(User user)
    {
        Users users = Users.get(MainActivity.this);
        users.delete(user);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(new UserAdapter(users.get_users()));
    }

    private class UserHolder extends RecyclerView.ViewHolder
    {
        TextView textView;
        public UserHolder(LayoutInflater inflater, ViewGroup viewGroup) {
            super(inflater.inflate(R.layout.activity_item, viewGroup,false));
            Log.d("SYSTEM INFO","Method UserHolder called");
            textView = itemView.findViewById(R.id.itemTextView);
            Log.d("SYSTEM INFO","Method UserHolder ended");
        }

        public void Bind(User user)
        {
            Log.d("SYSTEM INFO","Method Bind called");
            textView.setText(user.getName()+"\n Телефон: "+user.getPhoneNumber());
            textView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final CharSequence[] items = {"Изменить контакт", "Удалить контакт"};

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                    builder.setTitle("Выберите действие");
                    builder.setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int item) {
                            switch (item){
                                case 0:
                                    changeUser(user);
                                    break;
                                case 1:
                                    deleteUser(user);
                                    break;
                            }
                        }
                    });
                    builder.show();
                    return false;
                }
            });
            Log.d("SYSTEM INFO","Method Bind ended");
        }
    }

    private class UserAdapter extends RecyclerView.Adapter<UserHolder>
    {
        List<User> users;
        public UserAdapter(List<User> users)
        {
            Log.d("SYSTEM INFO","Method UserAdapter called");
            this.users = users;
            Log.d("SYSTEM INFO","Method UserAdapter ended");
        }

        @Override
        public UserHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            Log.d("SYSTEM INFO","Method onCreateViewHolder called");
            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
            Log.d("SYSTEM INFO","Method onCreateViewHolder ended");
            return new UserHolder(inflater, viewGroup);
        }

        @Override
        public void onBindViewHolder(MainActivity.UserHolder holder, int position) {
            Log.d("SYSTEM INFO","Method onBindViewHolder called");
            holder.Bind(users.get(position));
            Log.d("SYSTEM INFO","Method onBindViewHolder ended");
        }

        @Override
        public int getItemCount() {
            Log.d("SYSTEM INFO","Method getItemCount called");
            Log.d("SYSTEM INFO","Method getItemCount ended");
            return users.size();
        }
    }
}