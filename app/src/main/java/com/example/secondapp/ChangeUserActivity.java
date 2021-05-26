package com.example.secondapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChangeUserActivity extends AppCompatActivity {
    TextView firstNameTextView, lastNameTextView ,phoneTextView;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user);
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user");
        addButton = findViewById(R.id.changeButton);
        firstNameTextView = findViewById(R.id.changeFirstNameTextView);
        firstNameTextView.setText(user.getFirstName());
        lastNameTextView = findViewById(R.id.changeLastNameTextView);
        lastNameTextView.setText(user.getLastName());
        phoneTextView = findViewById(R.id.changePhoneTextView);
        phoneTextView.setText(user.getPhoneNumber());
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Users users = Users.get(ChangeUserActivity.this);
                users.update(new User(user.getUuid(),firstNameTextView.getText().toString(),lastNameTextView.getText().toString(),phoneTextView.getText().toString()));
                onBackPressed();
            }
        });
    }
}