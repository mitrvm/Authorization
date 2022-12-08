package com.example.authorization;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button login;
    TextView awarning;
    TextView attemptsleft;

    int numberOfRemainingLoginAttempts = 3;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.button_login);
        awarning = (TextView) findViewById(R.id.awarning);
        attemptsleft = (TextView) findViewById(R.id.attemptsleft);

        attemptsleft.setText("Осталось " + numberOfRemainingLoginAttempts + " попыток.");


        View.OnClickListener log = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                    Toast.makeText(getApplicationContext(), "Вход выполнен!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, Second.class);
                    startActivity(intent);
                } else {
                    numberOfRemainingLoginAttempts = numberOfRemainingLoginAttempts - 1;
                    if (numberOfRemainingLoginAttempts == 0) {
                        login.setEnabled(false);
                        attemptsleft.setText("Осталось 0 попыток.");
                        attemptsleft.setTextColor(Color.RED);
                        awarning.setText("Вход заблокирован!");

                    } else {
                        awarning.setText("Неправильные данные!");
                        attemptsleft.setText("Осталось " + numberOfRemainingLoginAttempts + " попыток.");
                    }
                }
            }
        };
        login.setOnClickListener(log);
    }
}