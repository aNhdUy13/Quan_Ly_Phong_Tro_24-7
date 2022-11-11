package com.nda.quanlyphongtro_free.Houses.HouseDetail.Rooms.RoomDetail;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nda.quanlyphongtro_free.Houses.HouseDetail.HouseDetailSystem;
import com.nda.quanlyphongtro_free.Houses.HouseDetail.Rooms.RoomDetail.Tenants.AddTenant;
import com.nda.quanlyphongtro_free.MainActivity;
import com.nda.quanlyphongtro_free.Model.Houses;
import com.nda.quanlyphongtro_free.Model.Rooms;
import com.nda.quanlyphongtro_free.Model.Service;
import com.nda.quanlyphongtro_free.R;
import com.nda.quanlyphongtro_free.Model.Tenants;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RoomDetailSystem extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef  = database.getReference();
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

    ShimmerFrameLayout shimmer_view_container;

    ImageView imgBack,img_addTenants, img_editRoom, img_contact;
    TextView txt_roomName;

    LinearLayout ll_danhSachTenants, ll_chiTietPhong, ll_showTenants, ll_showRoomDetail, ll_optionRooms;
    TextView txt_bgColor1,txt_bgColor2;
    androidx.appcompat.widget.SearchView searchView_searchTenants;

    Houses houses;
    Rooms rooms;

    RecyclerView rcv_tenants;

    List<Tenants> tenantsList = new ArrayList<>();
    AdapterTenants adapterTenants;

    TextView txt_roomFee, txt_area, txt_floorNumber, txt_numberOfBedRooms, txt_numberOfLivingRooms,
            txt_limitTenants, txt_deposits;
    TextView txt_genderMale, txt_genderFemale, txt_genderOther;
    TextView txt_description, txt_noteForTenants;

    Button btn_deleteRoom;

    List<Service> serviceList = new ArrayList<>();
    AdapterServiceOfRoom adapterServiceOfRoom;
    RecyclerView rcv_servicesRoomDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail_system);

        initUI();

        init();
        setupRCV();
    }



    private void setupRCV() {
        adapterTenants = new AdapterTenants(this,tenantsList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),
                RecyclerView.VERTICAL,false);
        rcv_tenants.setLayoutManager(linearLayoutManager);
        rcv_tenants.setAdapter(adapterTenants);
        displayTenants();
    }

    private void init() {
        houses = getIntent().getParcelableExtra("Data_RoomOfHouse_Parcelable");
        rooms  = getIntent().getParcelableExtra("Data_Room_Parcelable");

        txt_roomName.setText(rooms.getrName());



        img_addTenants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoomDetailSystem.this, AddTenant.class);

                intent.putExtra("Data_House_Parcelable", houses);
                intent.putExtra("Data_Room_Parcelable", rooms);

                startActivity(intent);
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToRoomSystem();
            }
        });


        ll_danhSachTenants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_showTenants.setVisibility(View.VISIBLE);
                ll_showRoomDetail.setVisibility(View.GONE);
                searchView_searchTenants.setVisibility(View.VISIBLE);
                txt_bgColor1.setBackgroundColor(Color.parseColor("#4CAF50"));
                txt_bgColor2.setBackgroundColor(Color.parseColor("#FFFFFF"));

            }
        });
        ll_chiTietPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_showRoomDetail.setVisibility(View.VISIBLE);
                ll_showTenants.setVisibility(View.GONE);
                searchView_searchTenants.setVisibility(View.GONE);
                txt_bgColor2.setBackgroundColor(Color.parseColor("#4CAF50"));
                txt_bgColor1.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }
        });

        img_editRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }



    private void displayTenants()
    {
        tenantsList.clear();

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Tenants tenants = dataSnapshot.getValue(Tenants.class);
                    tenantsList.add(tenants);
                }

                relatedRoomDetail();

                adapterTenants.notifyDataSetChanged();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        };
        Query query = myRef.child("tenants").child(firebaseUser.getUid()).orderByChild("rentRoomId").equalTo(rooms.getId());
        query.addListenerForSingleValueEvent(valueEventListener);



    }


    /***************************
     *
     *
     * (Related) Display room detail
     *
     *
     *************************** */
    private void relatedRoomDetail() {

        txt_area.setText(rooms.getrArea() + " m2");

        txt_floorNumber.setText(rooms.getrFloorNumber());

        txt_numberOfBedRooms.setText(rooms.getrBedRoomNumber());
        txt_numberOfLivingRooms.setText(rooms.getrLivingRoomNumber());
        txt_limitTenants.setText(rooms.getrLimitTenants());

        if (rooms.getrPrice().equals(""))
        {
            txt_roomFee.setText("0 đ");
        } else {
            /**
             * Format cost lấy về từ firebase
             * theo định dạng money
             * */
            DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
            formatter.applyPattern("#,###,###,###");
            Double cost = Double.parseDouble(rooms.getrPrice());
            txt_roomFee.setText(formatter.format(cost) + " đ");
        }

        if (rooms.getrDeposit().equals(""))
        {
            txt_deposits.setText("0 đ");
        } else {
            /**
             * Format cost lấy về từ firebase
             * theo định dạng money
             * */
            DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
            formatter.applyPattern("#,###,###,###");
            Double cost = Double.parseDouble(rooms.getrDeposit());
            txt_deposits.setText(formatter.format(cost) + " đ");
        }

        txt_description.setText(rooms.getrDescription());
        txt_noteForTenants.setText(rooms.getrNoteToTenant());


        String selectedGender = rooms.getrGender();
        String[] splitGender = selectedGender.split(",");
        for (int i = 0 ; i < splitGender.length; i ++)
        {
            if (splitGender[i].equals("Nam"))
            {
                txt_genderMale.setBackgroundColor(Color.parseColor("#11C618"));

            }
            else if (splitGender[i].equals("Nữ"))
            {
                txt_genderFemale.setBackgroundColor(Color.parseColor("#11C618"));

            }
            else if (splitGender[i].equals("Khác"))
            {
                txt_genderOther.setBackgroundColor(Color.parseColor("#11C618"));

            }
        }


        adapterServiceOfRoom = new AdapterServiceOfRoom(this, serviceList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),
                RecyclerView.HORIZONTAL,false);
        rcv_servicesRoomDetail.setLayoutManager(linearLayoutManager);
        rcv_servicesRoomDetail.setAdapter(adapterServiceOfRoom);

        displayServices();
    }
    private void displayServices() {
        serviceList.clear();

        try {
            ValueEventListener valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren())
                    {
                        Service service = dataSnapshot.getValue(Service.class);

                        serviceList.add(service);
                    }

                    // When get data successfully, hide the shimmer and show all function field
                    searchView_searchTenants.setVisibility(View.VISIBLE);
                    rcv_tenants.setVisibility(View.VISIBLE);
                    img_addTenants.setVisibility(View.VISIBLE);
                    img_addTenants.setVisibility(View.VISIBLE);
                    img_contact.setVisibility(View.VISIBLE);
                    img_editRoom.setVisibility(View.VISIBLE);
                    ll_optionRooms.setVisibility(View.VISIBLE);
                    shimmer_view_container.setVisibility(View.GONE);
                    shimmer_view_container.stopShimmerAnimation();

                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            };
            Query query = myRef.child("houses").child(firebaseUser.getUid()).child(houses.gethId()).child("serviceList");
            query.addListenerForSingleValueEvent(valueEventListener);
        } catch (Exception e)
        {
            startActivity(new Intent(RoomDetailSystem.this, MainActivity.class));
            RoomDetailSystem.this.finish();

            Toast.makeText(this, "Warning : Kiểm tra đường truyền Internet !", Toast.LENGTH_SHORT).show();

        }

    }

    private void backToRoomSystem()
    {
        Intent intent = new Intent(RoomDetailSystem.this, HouseDetailSystem.class);

        intent.putExtra("Data_House_Parcelable", houses);
        startActivity(intent);

        RoomDetailSystem.this.finish();

    }

    private void initUI() {
        shimmer_view_container = findViewById(R.id.shimmer_view_container);

        imgBack         =  findViewById(R.id.imgBack);
        img_addTenants  =  findViewById(R.id.img_addTenants);
        img_editRoom    = findViewById(R.id.img_editRoom);
        img_contact     = findViewById(R.id.img_contact);

        rcv_tenants     =  findViewById(R.id.rcv_tenants);

        txt_roomName     =  findViewById(R.id.txt_roomName);


        ll_danhSachTenants  =  findViewById(R.id.ll_danhSachTenants);
        ll_chiTietPhong     =  findViewById(R.id.ll_chiTietPhong);
        txt_bgColor1        =  findViewById(R.id.txt_bgColor1);
        txt_bgColor2        =  findViewById(R.id.txt_bgColor2);
        ll_showTenants      =  findViewById(R.id.ll_showTenants);
        ll_showRoomDetail     =  findViewById(R.id.ll_showRoomDetail);
        ll_optionRooms        = findViewById(R.id.ll_optionRooms);

        searchView_searchTenants = findViewById(R.id.searchView_searchTenants);



        txt_roomFee     =  findViewById(R.id.txt_roomFee);
        txt_area     =  findViewById(R.id.txt_area);
        txt_floorNumber     =  findViewById(R.id.txt_floorNumber);
        txt_numberOfBedRooms     =  findViewById(R.id.txt_numberOfBedRooms);
        txt_numberOfLivingRooms     =  findViewById(R.id.txt_numberOfLivingRooms);
        txt_limitTenants     =  findViewById(R.id.txt_limitTenants);
        txt_deposits     =  findViewById(R.id.txt_deposits);

        txt_genderMale     =  findViewById(R.id.txt_genderMale);
        txt_genderFemale     =  findViewById(R.id.txt_genderFemale);
        txt_genderOther     =  findViewById(R.id.txt_genderOther);

        txt_description     =  findViewById(R.id.txt_description);
        txt_noteForTenants     =  findViewById(R.id.txt_noteForTenants);

        rcv_servicesRoomDetail     =  findViewById(R.id.rcv_servicesRoomDetail);

        btn_deleteRoom = findViewById(R.id.btn_deleteRoom);
    }


    @Override
    public void onBackPressed() {
        backToRoomSystem();

        super.onBackPressed();
    }

    @Override
    protected void onStart() {
        // Hide all function field and show shimmer effect
//        searchView_searchTenants.setVisibility(View.GONE);
//        rcv_tenants.setVisibility(View.GONE);
//        img_addTenants.setVisibility(View.GONE);
//        img_contact.setVisibility(View.GONE);
//        img_editRoom.setVisibility(View.GONE);
//        ll_optionRooms.setVisibility(View.GONE);
//        shimmer_view_container.setVisibility(View.VISIBLE);
//        shimmer_view_container.startShimmerAnimation();

        //displayServices();

        super.onStart();

    }
}