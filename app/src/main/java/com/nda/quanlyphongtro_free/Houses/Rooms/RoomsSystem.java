package com.nda.quanlyphongtro_free.Houses.Rooms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nda.quanlyphongtro_free.Houses.HousesSystem;
import com.nda.quanlyphongtro_free.Model.Houses;
import com.nda.quanlyphongtro_free.Model.Rooms;
import com.nda.quanlyphongtro_free.R;

import java.util.ArrayList;
import java.util.List;

public class RoomsSystem extends AppCompatActivity {
    Houses houses;

    ImageView imgBack,img_addRoom;

    RecyclerView rcv_rooms;
    TextView txt_houseName;


    List<Rooms> roomsList = new ArrayList<>();
    AdapterRoom roomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms_system);

        initUI();

        initiate();
        setupRCV();
    }

    private void setupRCV() {
        roomAdapter = new AdapterRoom(this,roomsList);

        displayRooms();
    }

    private void initiate() {
        houses = getIntent().getParcelableExtra("Data_House_Parcelable");
        txt_houseName.setText(houses.gethName());

        img_addRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RoomsSystem.this, HousesSystem.class));
                RoomsSystem.this.finish();
            }
        });



    }

    private void displayRooms()
    {

    }

    private void initUI() {
        imgBack         =  findViewById(R.id.imgBack);
        img_addRoom     =  findViewById(R.id.img_addRoom);

        rcv_rooms     =  findViewById(R.id.rcv_rooms);

        txt_houseName     =  findViewById(R.id.txt_houseName);

    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(RoomsSystem.this, HousesSystem.class));
        RoomsSystem.this.finish();

        super.onBackPressed();
    }
}