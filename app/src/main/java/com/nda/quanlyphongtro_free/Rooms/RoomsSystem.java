package com.nda.quanlyphongtro_free.Rooms;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nda.quanlyphongtro_free.MainActivity;
import com.nda.quanlyphongtro_free.R;
import com.nda.quanlyphongtro_free.Tenants.TenantsSystem;

import java.util.ArrayList;

public class RoomsSystem extends AppCompatActivity {
    ImageView imgBack,imgAddRooms;
    ListView lvRooms;
    TextView txtHouseNameInRoom;
    EditText edtGetRoomName,edtGetRoomPrice,edtGetNote;
    Button btnAddRoom;
    public int house_id;

    ArrayList<Rooms> roomsArrayList;
    RoomAdapter roomAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms_system);

        mapting();

        house_id = getIntent().getIntExtra("house_id",0);
        String houseName = getIntent().getStringExtra("house_name");
        txtHouseNameInRoom.setText(houseName);

        initiate();
        setUpListView();
    }

    private void setUpListView() {
        roomsArrayList = new ArrayList<>();
        roomAdapter = new RoomAdapter(this,R.layout.item_layout_rooms,roomsArrayList);
        lvRooms.setAdapter(roomAdapter);

        displayAvailableRoom();
    }

    private void initiate() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        imgAddRooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAddRoom();
            }
        });



    }


    public void goToTenants(int id,String roomName)
    {
        Intent intent = new Intent(RoomsSystem.this, TenantsSystem.class);
        intent.putExtra("roomID",id);
        intent.putExtra("roomName",roomName);
        startActivity(intent);

    }
    public void showNote(String note)
    {
        Dialog dialog = new Dialog(RoomsSystem.this);
        dialog.setContentView(R.layout.dialog_show_note);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView txtShowNote    = (TextView) dialog.findViewById(R.id.txtShowNote);
        Button btnOKNote    = (Button) dialog.findViewById(R.id.btnOKNote);

        btnOKNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        txtShowNote.setText(note);
        dialog.show();
    }
    public void dialogUpdateRoom(int id , String roomPrice, String roomNote, String roomName)
    {
        Dialog dialogUpdateRoom = new Dialog(RoomsSystem.this);
        dialogUpdateRoom.setContentView(R.layout.dialog_add_update_rooms);
        dialogUpdateRoom.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        edtGetRoomName  = (EditText) dialogUpdateRoom.findViewById(R.id.edtGetHousesName) ;
        edtGetRoomPrice = (EditText) dialogUpdateRoom.findViewById(R.id.edtGetRoomPrice);
        edtGetNote              = (EditText) dialogUpdateRoom.findViewById(R.id.edtGetNote);
        Button  btnUpdateRoom  = (Button) dialogUpdateRoom.findViewById(R.id.btnAddRoom);
        Button  btnCancel  = (Button) dialogUpdateRoom.findViewById(R.id.btnCancel);
        TextView txtTitleRoom   = (TextView) dialogUpdateRoom.findViewById(R.id.txtTitleRoom);

        edtGetRoomPrice.setText(roomPrice);
        edtGetNote.setText(roomNote);
        edtGetRoomName.setText(roomName);
        txtTitleRoom.setText(R.string.roomSystem_update_layout_main_title);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogUpdateRoom.dismiss();
            }
        });

        btnUpdateRoom.setText(R.string.housesSystem_update_button);
        btnUpdateRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String roomPrice = edtGetRoomPrice.getText().toString().trim();
                String noteRoom = edtGetNote.getText().toString().trim();
                String roomName = edtGetRoomName.getText().toString().trim();

                MainActivity.database.QueryDate("UPDATE Rooms SET roomPrice = '" + roomPrice + "'" +
                        ", roomsNote = '" + noteRoom  + "', roomsName = '" + roomName + "'  WHERE roomId = '" + id + "' ");

                Toast.makeText(getApplicationContext(),"Update Successful ! ",Toast.LENGTH_LONG).show();
                displayAvailableRoom();
                dialogUpdateRoom.dismiss();
            }
        });
        dialogUpdateRoom.show();
    }
    public void dialogDeleteRoom(int id)
    {
        Dialog dialog_delete = new Dialog(RoomsSystem.this);
        dialog_delete.setContentView(R.layout.dialog_detele);
        dialog_delete.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button btnYes               = (Button) dialog_delete.findViewById(R.id.btn_allow_delete);
        Button btnNo                = (Button) dialog_delete.findViewById(R.id.btn_cancel_delete);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.database.QueryDate("DELETE FROM Rooms WHERE roomId = '" + id + "'");
                MainActivity.database.QueryDate("DELETE FROM Tenants WHERE roomId = '" + id + "'");

                Toast.makeText(RoomsSystem.this,"Delete Successful ! ",Toast.LENGTH_LONG).show();
                displayAvailableRoom();
                dialog_delete.dismiss();
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_delete.dismiss();
            }
        });
        dialog_delete.show();
    }
    private void dialogAddRoom() {
        Dialog dialog = new Dialog(RoomsSystem.this);
        dialog.setContentView(R.layout.dialog_add_update_rooms);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        edtGetRoomName    = (EditText) dialog.findViewById(R.id.edtGetHousesName);
        edtGetRoomPrice    = (EditText) dialog.findViewById(R.id.edtGetRoomPrice);
        edtGetNote    = (EditText) dialog.findViewById(R.id.edtGetNote);
        btnAddRoom  = (Button) dialog.findViewById(R.id.btnAddRoom);
        Button  btnCancel  = (Button) dialog.findViewById(R.id.btnCancel);


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnAddRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String roomPrice, note,roomName;
                roomPrice   = edtGetRoomPrice.getText().toString().trim();
                note   = edtGetNote.getText().toString().trim();
                roomName   = edtGetRoomName.getText().toString().trim();

                MainActivity.database.INSERT_ROOMS(house_id,roomPrice,note,roomName);
                displayAvailableRoom();
                dialog.dismiss();
            }
        });


        dialog.show();
    }

    private void displayAvailableRoom()
    {
        Cursor cursor = MainActivity.database.GetData(
                "SELECT * FROM Rooms WHERE housesId = " + house_id);
        roomsArrayList.clear();
        while (cursor.moveToNext())
        {
            int id = cursor.getInt(0);
            int houseID = cursor.getInt(1);
            String roomPrice = cursor.getString(2);
            String roomNote = cursor.getString(3);
            String roomName = cursor.getString(4);

            roomsArrayList.add(new Rooms(id,roomPrice,roomNote,roomName));
        }
        roomAdapter.notifyDataSetChanged();
    }

    private void mapting() {
        imgBack             = (ImageView) findViewById(R.id.imgBack);
        imgAddRooms         = (ImageView) findViewById(R.id.imgAddRooms);
        lvRooms             = (ListView) findViewById(R.id.lvRooms);
        txtHouseNameInRoom  = (TextView) findViewById(R.id.txtHouseNameInRoom);


    }

}