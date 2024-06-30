package com.example.weighttracking;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

public class SMSActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 1;
    private Switch smsPermissionSwitch;
    private Button sendSMSButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        smsPermissionSwitch = findViewById(R.id.smsPermissionSwitch);
        sendSMSButton = findViewById(R.id.sendSMSButton);

        smsPermissionSwitch.setChecked(ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED);

        smsPermissionSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, PERMISSION_REQUEST_CODE);
                } else {
                    Toast.makeText(this, "Permission already granted", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        });

        sendSMSButton.setOnClickListener(v -> {
            String phoneNumber = "1234567890";
            String message = "This is a test SMS message.";

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNumber, null, message, null, null);
                Toast.makeText(this, "SMS sent successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission not granted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
                smsPermissionSwitch.setChecked(true);
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                smsPermissionSwitch.setChecked(false);
            }
        }
    }
}
