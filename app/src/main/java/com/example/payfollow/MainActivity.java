package com.example.payfollow;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    DBHelper db;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        Button button1 = findViewById(R.id.btn1);
        Button button2 = findViewById(R.id.btn2);
        EditText editText1 = findViewById(R.id.edt1);
        EditText editText2 = findViewById(R.id.edt2);
        DBHelper dbHelper = new DBHelper(this);
        listView = findViewById(R.id.listV);
        ArrayList<String> items = new ArrayList<>();
        int layout = android.R.layout.simple_list_item_1;

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues cv = new ContentValues();
                String nomClt = editText1.getText().toString();
                String villeClt = editText2.getText().toString();

                cv.put("nomClt",nomClt);
                cv.put("villeClt",villeClt);
                long newId = db.insert("client",null,cv);
                if(newId==-1){
                    Log.e("insertError", "Error insert" );
                }else{
                    Toast.makeText(MainActivity.this,"insert succesful",Toast.LENGTH_LONG).show();

                    Log.i("insertSucc", "insert succesful" );
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                ContentValues cv = new ContentValues();
                Cursor c = db.rawQuery("SELECT nomClt , villeClt FROM client",null);
                if(c.moveToFirst()){
                    do {
                        String cltNom = c.getString(0);
                        String cltVille = c.getString(1);
                        items.add("nom : "+cltNom+" ville : "+cltVille);
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, layout, items);
                        loadListView(adapter);
                    }while(c.moveToNext());

                }

            }
        });
    }

    private void loadListView(ArrayAdapter<String> adapter) {
        listView.setAdapter(adapter);
    }
}



