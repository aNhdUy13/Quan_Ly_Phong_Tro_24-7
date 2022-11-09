package com.nda.quanlyphongtro_free.Houses.Rooms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nda.quanlyphongtro_free.Houses.HousesSystem;
import com.nda.quanlyphongtro_free.Houses.Rooms.AddRoom.AddRoom;
import com.nda.quanlyphongtro_free.Model.Houses;
import com.nda.quanlyphongtro_free.Model.Rooms;
import com.nda.quanlyphongtro_free.Model.Service;
import com.nda.quanlyphongtro_free.R;

import java.util.ArrayList;
import java.util.List;

public class RoomsSystem extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef  = database.getReference();
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

    LinearLayout ll_danhSachPhong, ll_chiTietNha, ll_showDanhSachPhong, ll_showChiTietNha;
    TextView txt_bgColor1,txt_bgColor2;

    Houses houses;

    ImageView imgBack,img_addRoom;

    RecyclerView rcv_rooms;
    TextView txt_houseName;

    List<Service> serviceList = new ArrayList<>();
    List<Rooms> roomsList = new ArrayList<>();
    AdapterRoom roomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms_system);

        initUI();

        init();
        setupRCV();
    }

    private void setupRCV() {
        roomAdapter = new AdapterRoom(this,roomsList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),
                RecyclerView.VERTICAL,false);
        rcv_rooms.setLayoutManager(linearLayoutManager);
        rcv_rooms.setAdapter(roomAdapter);
        displayRooms();
    }

    private void init() {
        houses = getIntent().getParcelableExtra("Data_House_Parcelable");
        txt_houseName.setText(houses.gethName());

        getServicesPassed();


        img_addRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoomsSystem.this, AddRoom.class);

                intent.putExtra("Data_House_Parcelable", houses);
                startActivity(intent);
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RoomsSystem.this, HousesSystem.class));
                RoomsSystem.this.finish();
            }
        });

        ll_danhSachPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        ll_danhSachPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_showDanhSachPhong.setVisibility(View.VISIBLE);
                ll_showChiTietNha.setVisibility(View.GONE);
                txt_bgColor1.setBackgroundColor(Color.parseColor("#4CAF50"));
                txt_bgColor2.setBackgroundColor(Color.parseColor("#FFFFFF"));

            }
        });
        ll_chiTietNha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_showChiTietNha.setVisibility(View.VISIBLE);
                ll_showDanhSachPhong.setVisibility(View.GONE);
                txt_bgColor2.setBackgroundColor(Color.parseColor("#4CAF50"));
                txt_bgColor1.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }
        });
    }


    private void getServicesPassed() {
        serviceList.clear();

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Service service = dataSnapshot.getValue(Service.class);

                    serviceList.add(service);
                }


            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        };
        Query query = myRef.child("houses").child(firebaseUser.getUid()).child(houses.gethId()).child("serviceList");
        query.addListenerForSingleValueEvent(valueEventListener);
    }

    private void displayRooms()
    {
        roomsList.clear();

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Rooms rooms = dataSnapshot.getValue(Rooms.class);

                    roomsList.add(rooms);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        };
        Query query = myRef.child("rooms").child(firebaseUser.getUid()).child(houses.gethId());
        query.addListenerForSingleValueEvent(valueEventListener);



    }

    private void initUI() {
        imgBack         =  findViewById(R.id.imgBack);
        img_addRoom     =  findViewById(R.id.img_addRoom);

        rcv_rooms     =  findViewById(R.id.rcv_rooms);

        txt_houseName     =  findViewById(R.id.txt_houseName);

        ll_danhSachPhong  =  findViewById(R.id.ll_danhSachPhong);
        ll_chiTietNha     =  findViewById(R.id.ll_chiTietNha);
        txt_bgColor1      =  findViewById(R.id.txt_bgColor1);
        txt_bgColor2      =  findViewById(R.id.txt_bgColor2);
        ll_showDanhSachPhong  =  findViewById(R.id.ll_showDanhSachPhong);
        ll_showChiTietNha     =  findViewById(R.id.ll_showChiTietNha);

    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(RoomsSystem.this, HousesSystem.class));
        RoomsSystem.this.finish();

        super.onBackPressed();
    }
}