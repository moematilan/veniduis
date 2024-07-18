package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView timerTextView;
    private Button startButton, stopButton;

    private long startTime = 0L;
    private Handler handler = new Handler();
    private boolean isRunning = false;

    private Runnable updateTimerThread = new Runnable() {
        public void run() {
            if (isRunning) {
                long timeInMillis = System.currentTimeMillis() - startTime;
                int seconds = (int) (timeInMillis / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                int hours = minutes / 60;
                minutes = minutes % 60;

                timerTextView.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));

                handler.postDelayed(this, 1000);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerTextView = findViewById(R.id.timerTextView);
        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStopwatch();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopStopwatch();
            }
        });
    }

    private void startStopwatch() {
        startTime = System.currentTimeMillis();
        isRunning = true;
        handler.postDelayed(updateTimerThread, 0);
    }

    private void stopStopwatch() {
        isRunning = false;
        handler.removeCallbacks(updateTimerThread);
    }
}
