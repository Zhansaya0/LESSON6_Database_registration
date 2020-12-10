package com.e.database_registrationform.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Database_Registration.db";
    private static final int DATABASE_VERSION = 3;

    public static final String TABLE_USERS = "users";

    public static final String COLUMN_USER_NAME = "user_name";
    public static final String COLUMN_USER_EMAIL = "user_email";
    public static final String COLUMN_USER_PASSWORD = "user_password";

    Context context;

    public Database (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_USERS + "(" +
                COLUMN_USER_NAME + "TEXT," +
                COLUMN_USER_EMAIL + "TEXT," +
                COLUMN_USER_PASSWORD + "TEXT)" );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);

        onCreate(db);
    }
}