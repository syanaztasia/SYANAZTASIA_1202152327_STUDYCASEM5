package com.example.syanaz.syanaztasia_1202152327_studycase5;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Setting extends AppCompatActivity {
    int wrn;
    TextView color;
    AlertDialog.Builder alert;
    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        alert = new AlertDialog.Builder(this);

        SharedPreferences shp = getApplicationContext().getSharedPreferences("shp", 0);
        edit = shp.edit();
        color = findViewById(R.id.warna);
        wrn = shp.getInt("background", R.color.putih);

        color.setText(getWarna(wrn));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() ==android.R.id.home){
            this.onBackPressed();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Setting.this, MainActivity.class));
        finish();
    }

    public String getWarna(int i){
        if (i==R.color.biru){
            return "Biru";
        }else if (i==R.color.merah){
            return "Merah";
        }else if (i==R.color.hijau){
            return "Hijau";
        }else{
            return "Putih";
        }
    }
    public int getIntColor(int i){
        if (i==R.color.biru){
            return R.id.btn_blue;
        }else if (i==R.color.merah){
            return R.id.btn_red;
        }else if (i==R.color.hijau){
            return R.id.btn_green;
        }else {
            return R.id.btn_white;
        }
    }

    public void Klik(View view) {
        alert.setTitle("Choose Color");
        View v = getLayoutInflater().inflate(R.layout.pilihwarna,null);
        alert.setView(v);

        final RadioGroup rg = v.findViewById(R.id.rg);
        rg.check(getIntColor(wrn));

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int cek = rg.getCheckedRadioButtonId();
                switch (cek){
                    case R.id.btn_blue:
                        wrn = R.color.biru;
                        break;
                    case R.id.btn_green:
                        wrn = R.color.hijau;
                        break;
                    case R.id.btn_red:
                        wrn = R.color.merah;
                        break;
                    case R.id.btn_white:
                        wrn = R.color.putih;
                        break;
                }
                color.setText(getWarna(wrn));
                edit.putInt("background", wrn);
                edit.commit();
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });alert.create().show();
    }
}
