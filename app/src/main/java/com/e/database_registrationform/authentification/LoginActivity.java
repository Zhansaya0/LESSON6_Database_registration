package com.e.database_registrationform.authentification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.e.database_registrationform.R;
import com.e.database_registrationform.database.Database;

import static com.e.database_registrationform.database.Database.COLUMN_USER_EMAIL;
import static com.e.database_registrationform.database.Database.COLUMN_USER_NAME;
import static com.e.database_registrationform.database.Database.COLUMN_USER_PASSWORD;
import static com.e.database_registrationform.database.Database.TABLE_USERS;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Database storeDb;
    private SQLiteDatabase sqdb;

    EditText editText_email;
    EditText editText_password;
    Button button_submit2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        storeDb = new Database(this);
        sqdb = storeDb.getWritableDatabase();

        editText_email= findViewById(R.id.editText_email);
        editText_password= findViewById(R.id.editText_password);
        button_submit2= findViewById(R.id.button_submit2);
        button_submit2.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if(TextUtils.isEmpty(editText_email.getText())){
            editText_email.setError("Fill again");
            return;
        }
        if(TextUtils.isEmpty(editText_password.getText())){
            editText_password.setError("Fill again");
            return;
        }
        Cursor userCursor = sqdb.rawQuery(" SELECT * FROM " + TABLE_USERS +
                " WHERE " + COLUMN_USER_EMAIL + "=? AND " + COLUMN_USER_PASSWORD + "=?",
                new String[] {editText_email.getText().toString(),editText_password.getText().toString()});

        if (((userCursor != null) && (userCursor.getCount()>0))) {
            userCursor.moveToFirst();
            String userName = userCursor.getString(userCursor.getColumnIndex(COLUMN_USER_NAME));
            Toast.makeText(this, "Siz satti kirdiniz! Welcome," + userName, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Invalid user", Toast.LENGTH_SHORT).show();
        }

    }
}
