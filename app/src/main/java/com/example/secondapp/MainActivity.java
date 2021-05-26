package com.example.secondapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerViewMain);
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("Misha","+79161570495"));
        users.add(new User("Masha","+79161570584"));
        users.add(new User("Milena","+79161570216"));
        users.add(new User("Galya","+79161570155"));
        users.add(new User("Serega","+79161570747"));

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(new UserAdapter(users));


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
            textView.setText("Имя:"+user.getName()+" Телефон: "+user.getPhoneNumber());
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