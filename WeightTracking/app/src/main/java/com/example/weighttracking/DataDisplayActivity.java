package com.example.weighttracking;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.weighttracking.model.DataItem; // Correct import statement

import java.util.ArrayList;
import java.util.List;

public class DataDisplayActivity extends AppCompatActivity {

    private ListView dataListView;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_display);

        dbHelper = new DatabaseHelper(this);
        dataListView = findViewById(R.id.dataListView);

        List<DataItem> dataItems = dbHelper.getAllItems();
        List<String> displayItems = new ArrayList<>();

        for (DataItem item : dataItems) {
            displayItems.add(item.getWeight() + " - " + item.getDate());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, displayItems);
        dataListView.setAdapter(adapter);
    }
}
