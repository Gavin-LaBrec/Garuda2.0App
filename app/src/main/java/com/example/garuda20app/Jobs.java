package com.example.garuda20app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

public class Jobs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs);
    }

    /**
     * Handles swiping left or right
     *
     * @param touchevent screen touched
     *
     * @return if something changed
     */
    public boolean onTouchEvent(MotionEvent touchevent) {
        switch (touchevent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // Press down
                x1 = touchevent.getX();
                break;
            case MotionEvent.ACTION_UP:
                // Release
                x2 = touchevent.getX();
                if (x1 > x2) {
                    // Left swipe
                    nextJob();
                    return true;
                } else if (x1 < x2) {
                    // Right swipe
                    nextJob();
                    // FUNCTION TO RECORD A SWIPE RIGHT FOR EMPLOYER
                    TextView jobTitle = findViewById(R.id.jobTitleView);
                    jobTitle.setText("A great job on the right!");
                    return true;
                }
        }
        return false;
    }

    /**
     * Displays the next job
     * Updates text and image
     */
    public void nextJob() {
        // Temporary variables to be retrieved from database
        String nextTitle = "Pick up maple syrup";
        String nextPay = "$10000";
        String nextLocation = "Canada";
        String nextTime = "2 hours";
        String nextDescription = "An amazing adventure were you acquire "
                + "large amounts of maple syrup.";
        int nextImage = R.drawable.industrial_delivery;

        // Update text and image
        TextView jobTitle = findViewById(R.id.jobTitleView);
        jobTitle.setText(nextTitle);

        TextView jobPay = findViewById(R.id.payView);
        jobPay.setText(nextPay);

        TextView jobLocation = findViewById(R.id.locationView);
        jobLocation.setText(nextLocation);

        TextView jobTime = findViewById(R.id.timeView);
        jobTime.setText(nextTime);

        TextView jobDescription = findViewById(R.id.jobDescriptionView);
        jobDescription.setText(nextDescription);

        ImageView jobImage = findViewById(R.id.jobImageView);
        jobImage.setImageResource(nextImage);

    }

    // Positions of touches
    float x1, x2;
}