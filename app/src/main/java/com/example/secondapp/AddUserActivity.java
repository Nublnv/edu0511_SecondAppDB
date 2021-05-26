package com.example.secondapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddUserActivity extends AppCompatActivity {
    TextView firstNameTextView, lastNameTextView ,phoneTextView;
    Button addButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        addButton = findViewById(R.id.addButton);
        firstNameTextView = findViewById(R.id.addFirstNameTextView);
        lastNameTextView = findViewById(R.id.addLastNameTextView);
        phoneTextView = findViewById(R.id.addPhoneTextView);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Users users = Users.get(AddUserActivity.this);
                users.addUser(new User(firstNameTextView.getText().toString(),lastNameTextView.getText().toString(),phoneTextView.getText().toString()));
                onBackPressed();
            }
        });
    }
}