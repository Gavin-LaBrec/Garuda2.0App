package com.example.garuda20app;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.dailyjournal.databinding.ActivityReviewEntryBinding;

import java.time.LocalDateTime;

public class ReviewEntry extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityReviewEntryBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityReviewEntryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        try {
            configureText();
        } catch (Exception emptyDB) {
            // Popup error if there are no entries
            startActivity(new Intent(ReviewEntry.this, EmptyEntryPop.class));
            finish();
        }

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

        ImageButton olderButton = (ImageButton) findViewById(R.id.olderButton);
        olderButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                TextView dateTextView = findViewById(R.id.dateTextView);

                // Get older date
                DatabaseHelper databaseHelper = new DatabaseHelper(ReviewEntry.this);
                String selectedDate = (String) dateTextView.getText();
                String olderDateString = databaseHelper.getNextDate(selectedDate, "older");
                try {
                    updateText(olderDateString);
                } catch (Exception e) {
                    // May want to handle moving from oldest entry better
                    e.printStackTrace();
                }
            }
        });

        ImageButton newerButton = (ImageButton) findViewById(R.id.newerButton);
        newerButton.setOnClickListener(new View.OnClickListener() {
            }
        });
    }

    /**
     * Updates the text displayed based on the given date
     *
     * @param dateString string to look up to find next date
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void updateText(String dateString) throws Exception {
        // Set up TextViews
        TextView titleTextView = findViewById(R.id.titleTextView);
        TextView payTextView = findViewById(R.id.payTextMultiLine);
        TextView locationTextView = findViewById(R.id.locationTextMultiLine);
        TextView timeTextView = findViewById(R.id.timeTextView);
        TextView descriptionTextView = findViewById(R.id.descriptionTextMultiLine);

        DatabaseHelper databaseHelper = new DatabaseHelper(ReviewEntry.this);

        // Update display text
        String title = olderDate.getTitleText();
        String pay = olderDate.getPayText();
        String location = olderDate.getLocationText();
        String time = olderDate.getTimeText();
        String description = olderDate.getDescriptionText();

        titleTextView.setText(title);
        payTextView.setText(pay);
        locationTextView.setText(location);
        timeTextView.setText(time);
        descriptionTextView.setText(description);
    }
}