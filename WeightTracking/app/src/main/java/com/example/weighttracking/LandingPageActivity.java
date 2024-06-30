package com.example.weighttracking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class LandingPageActivity extends AppCompatActivity {

    private Button viewDataButton;
    private Button sendSMSButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        viewDataButton = findViewById(R.id.viewDataButton);
        sendSMSButton = findViewById(R.id.sendSMSButton);

        viewDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LandingPageActivity.this, DataDisplayActivity.class);
                startActivity(intent);
            }
        });

        sendSMSButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LandingPageActivity.this, SMSActivity.class);
                startActivity(intent);
            }
        });
    }
}
