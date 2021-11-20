package com.example.garuda20app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

public class Jobs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs);
    }

    /**
     * Handles swiping left or right.
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
                    // Example change
                    TextView jobTitle = findViewById(R.id.jobTitleView);
                    jobTitle.setText("A great job on the left!");
                    //this.finishAffinity(); // To get data from database and
                    //swipeLeft();
                    return true;
                } else if (x1 < x2) {
                    // Example change
                    TextView jobTitle = findViewById(R.id.jobTitleView);
                    jobTitle.setText("A great job on the right!");
                    //swipeRight(); // To get data from database and
                    return true;
                }
        }
        return false;
    }

    // Position of touch
    float x1, x2;
}