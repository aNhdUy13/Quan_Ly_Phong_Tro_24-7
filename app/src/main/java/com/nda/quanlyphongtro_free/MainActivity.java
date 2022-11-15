package com.nda.quanlyphongtro_free;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nda.quanlyphongtro_free.Houses.HousesSystem;
import com.nda.quanlyphongtro_free.Services.ServicesSystem;
import com.nda.quanlyphongtro_free.Tenants.TenantsSystem;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    CardView cvNhaTro, cvDichVu,cv_quanLyTenants;

    TextView getTotalHouse, getTotalRoom,getTotalTenants,getTotalAvailableServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapting();
        initiate();

    }
    private void initiate() {
        cvNhaTro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HousesSystem.class));
            }
        });

        cvDichVu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ServicesSystem.class));
            }
        });


        cv_quanLyTenants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TenantsSystem.class));

            }
        });




    }


    private void mapting() {
        cvNhaTro    = (CardView) findViewById(R.id.cvNhaTro);
        cvDichVu    = (CardView) findViewById(R.id.cvDichVu);
        cv_quanLyTenants    = (CardView) findViewById(R.id.cv_quanLyTenants);

        getTotalHouse   = (TextView) findViewById(R.id.getTotalHouse);
        getTotalRoom    = (TextView) findViewById(R.id.getTotalRoom);
        getTotalTenants = (TextView) findViewById(R.id.getTotalTenants);
        getTotalAvailableServices   = (TextView) findViewById(R.id.getTotalAvailableServices);

    }
}