package com.example.garuda20app;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.database.sqlite.SQLiteOpenHelper;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main Activity";

    DatabaseHelper mDatabaseHelper;
    private Button viewJobsButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configureButtons();
    }

    /**
     * Initializes buttons on starting activity
     */
    private void configureButtons() {
        Button viewJobsButton = (Button) findViewById(R.id.viewJobsButton);
        viewJobsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Jobs.class));
            }
        });
    }
}