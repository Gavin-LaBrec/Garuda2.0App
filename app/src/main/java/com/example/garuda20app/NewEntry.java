package com.example.garuda20app;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.navigation.ui.AppBarConfiguration;

import com.example.dailyjournal.databinding.ActivityNewEntryBinding;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NewEntry extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityNewEntryBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNewEntryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        configureText();
        configureButtons();
    }

    /**
     * Initializes buttons on starting activity
     */
    private void configureButtons() {
        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button finishButton = (Button) findViewById(R.id.editButton);
        finishButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                // Collect data for entry

                TextView titleTextView = (TextView) findViewById(R.id.titleTextMultiLine);
                String titleText = titleTextView.getText().toString();
                TextView payTextView = (TextView) findViewById(R.id.payTextMultiLine);
                String payText = payTextView.getText().toString();
                TextView locationTextView = (TextView) findViewById(R.id.locationTextMultiLine);
                String locationText = locationTextView.getText().toString();
                TextView timeTextView = (TextView) findViewById(R.id.timeTextMultiLine);
                String timeText = timeTextView.getText().toString();
                TextView descriptionTextView = (TextView) findViewById(R.id.descriptionTextMultiLine);
                String descriptionText = descriptionTextView.getText().toString();

                // Create and add entry to database
                Entry newEntry = new Entry(title, pay, location, time, description);
                DatabaseHelper databaseHelper = new DatabaseHelper(NewEntry.this);
                databaseHelper.addDatabaseEntry(newEntry);
                finish();
            }
        });
    }



}