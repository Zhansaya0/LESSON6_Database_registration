package com.e.database_registrationform.authentification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Database storeDb;
    private SQLiteDatabase sqdb;

    EditText editText_name;
    EditText editText_email;
    EditText editText_password;
    Button button_submit;
    Button button_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storeDb = new Database(this);
        sqdb = storeDb.getWritableDatabase();

        editText_name=findViewById(R.id.editText_name);
        editText_email= findViewById(R.id.editText_email);
        editText_password= findViewById(R.id.editText_password);
        button_submit= findViewById(R.id.button_submit);
        button_login= findViewById(R.id.button_login);
        button_submit.setOnClickListener(this);
        button_login.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button_submit:

        if(TextUtils.isEmpty(editText_name.getText())){
            editText_name.setError("Fill again");
            return;
        }
        if(TextUtils.isEmpty(editText_email.getText())){
            editText_email.setError("Fill again");
            return;
        }
        if(TextUtils.isEmpty(editText_password.getText())){
            editText_password.setError("Fill again");
            return;
        }
        ContentValues versionValues = new ContentValues();
        versionValues.put(COLUMN_USER_NAME, editText_name.getText().toString());
        versionValues.put(COLUMN_USER_EMAIL, editText_email.getText().toString());
        versionValues.put(COLUMN_USER_PASSWORD, editText_password.getText().toString());
        sqdb.insert(TABLE_USERS, null, versionValues);
        Toast.makeText(this, "Databasege user engizildi",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
                break;
            case R.id.button_login:
                Intent intent2 = new Intent(this, LoginActivity.class);
                startActivity(intent2);
                break;
    }
    }
}
