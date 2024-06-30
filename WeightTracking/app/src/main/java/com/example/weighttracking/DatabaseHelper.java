package com.example.weighttracking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.weighttracking.model.DataItem; // Correct import statement

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "weightTracking.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_USERS = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_FULL_NAME = "full_name";

    private static final String TABLE_WEIGHTS = "weights";
    private static final String COLUMN_WEIGHT = "weight";
    private static final String COLUMN_DATE = "date";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_USERNAME + " TEXT,"
                + COLUMN_EMAIL + " TEXT,"
                + COLUMN_PASSWORD + " TEXT,"
                + COLUMN_FULL_NAME + " TEXT" + ")";
        db.execSQL(CREATE_USERS_TABLE);

        String CREATE_WEIGHTS_TABLE = "CREATE TABLE " + TABLE_WEIGHTS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_WEIGHT + " REAL,"
                + COLUMN_DATE + " TEXT" + ")";
        db.execSQL(CREATE_WEIGHTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WEIGHTS);
        onCreate(db);
    }

    public void addUser(String username, String email, String password, String fullName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_FULL_NAME, fullName);
        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,
                new String[]{COLUMN_ID},
                COLUMN_EMAIL + "=? AND " + COLUMN_PASSWORD + "=?",
                new String[]{email, password},
                null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count > 0;
    }

    public void addWeight(double weight, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_WEIGHT, weight);
        values.put(COLUMN_DATE, date);
        db.insert(TABLE_WEIGHTS, null, values);
        db.close();
    }

    public List<DataItem> getAllItems() {
        List<DataItem> weightList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_WEIGHTS, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                double weight = cursor.getDouble(cursor.getColumnIndex(COLUMN_WEIGHT));
                String date = cursor.getString(cursor.getColumnIndex(COLUMN_DATE));
                weightList.add(new DataItem(id, weight, date));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return weightList;
    }
}
