package com.example.syanaz.syanaztasia_1202152327_studycase5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //inisialisasi
    Database db;
    RecyclerView rv;
    adapter adapter;
    ArrayList<itemTodo> listitem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.rv_list);
        listitem = new ArrayList<>();

        db = new Database(this);
        db.getAllItem(listitem);

        SharedPreferences shp = this.getApplicationContext().getSharedPreferences("shp", 0);
        int warna = shp.getInt("background", R.color.putih);

        adapter = new adapter(this, listitem, warna);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        swipe();
    }
    //method untuk meswipe menu untuk menghapus data
    public void swipe() {
        ItemTouchHelper.SimpleCallback sc = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int posisi = viewHolder.getAdapterPosition();
                itemTodo now = adapter.getItem(posisi);

                if (direction==ItemTouchHelper.LEFT||direction==ItemTouchHelper.RIGHT){
                    if (db.hapusdata(now.getTodo())){
                        adapter.removeitem(posisi);
                    }
                }
            }
        };
        ItemTouchHelper helper =  new ItemTouchHelper(sc);
        helper.attachToRecyclerView(rv);
    }
    //menu setting
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    // menu setting agar bisa di pilih warna apa yang diinginkan
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.setting){
            startActivity(new Intent(MainActivity.this, Setting.class));
            finish();
        }
        return true;
    }


    public void onClick(View view) {
        startActivity(new Intent(MainActivity.this, Todo.class));
        finish();
    }
}