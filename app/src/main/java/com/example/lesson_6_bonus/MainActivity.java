package com.example.lesson_6_bonus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnAdd,btnRead,btnClear;
    EditText etName,etEmail;
    DBHelper DBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        btnAdd = findViewById ( R.id.btnAdd );
        btnAdd.setOnClickListener (this);
        btnRead = findViewById ( R.id.btnRead );
        btnRead.setOnClickListener (this);
        btnClear = findViewById ( R.id.btnClear );
        btnClear.setOnClickListener (this);

        etName = findViewById ( R.id.etName );
        etEmail = findViewById ( R.id.etEmail );

        DBHelper = new DBHelper ( this );
    }

    @Override
    public void onClick(View v) {
        String name = etName.getText ().toString ();
        String email = etEmail.getText ().toString ();

        SQLiteDatabase database = DBHelper.getWritableDatabase ();
        ContentValues contentValues = new ContentValues ();
        switch (v.getId()){

            case R.id.btnAdd:
                contentValues.put ( DBHelper.KEY_NAME,name );
                contentValues.put(DBHelper.KEY_EMAIL,email);

                database.insert ( DBHelper.TABLE_CONTACTS,null,contentValues );
                break;
            case R.id.btnRead:
                Cursor cursor = database.query ( DBHelper.TABLE_CONTACTS,null,null,null,null,null,null );
                if (cursor.moveToFirst ()){
                    int idIndex = cursor.getColumnIndex ( DBHelper.KEY_ID );
                    int nameIndex = cursor.getColumnIndex ( DBHelper.KEY_NAME );
                    int emailIndex = cursor.getColumnIndex ( DBHelper.KEY_EMAIL );
                        do{
                            Log.d ("mLog","ID =" + cursor.getInt ( idIndex )+
                                           ",name=" + cursor.getString ( nameIndex )+
                                    ",email="+ cursor.getString ( emailIndex ));
                        }while (cursor.moveToNext ());
                }else
                    Log.d ( "mLog","0 rows" );
                cursor.close ();
                break;
            case R.id.btnClear:
                database.delete ( DBHelper.TABLE_CONTACTS,null,null );
                break;
        }
        DBHelper.close ();

    }
}