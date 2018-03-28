package com.example.syanaz.syanaztasia_1202152327_studycase5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Todo extends AppCompatActivity {
    EditText td, des, prior;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        td = findViewById(R.id.todo);
        des = findViewById(R.id.desc);
        prior = findViewById(R.id.prio);
        db = new Database(this);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Todo.this, MainActivity.class));
        this.finish();
    }

    public void tambah(View view) {
        if (db.inputdata(new itemTodo(td.getText().toString(), des.getText().toString(), prior.getText().toString()))){
            Toast.makeText(this, "Todo ditambahkan", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }else {
            Toast.makeText(this, "Todo gagal ditambahkan", Toast.LENGTH_SHORT).show();
            td.setText(null);
            des.setText(null);
            prior.setText(null);
        }
    }
}