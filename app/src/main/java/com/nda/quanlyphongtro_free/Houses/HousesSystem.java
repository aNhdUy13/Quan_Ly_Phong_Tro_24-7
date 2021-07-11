package com.nda.quanlyphongtro_free.Houses;

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
import com.nda.quanlyphongtro_free.Rooms.RoomsSystem;

import java.util.ArrayList;

public class HousesSystem extends AppCompatActivity {
    ImageView imgBack,imgAddHouses;
    Button btnAddHouses, btnUpdateHouses;
    EditText edtGetHousesName,edtGetHousesLocation;
    ArrayList<Houses> housesArrayList;
    HousesAdapter housesAdapter;
    ListView lvHouses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_houses_system);
        mapting();
        initiation();
        setUpListView();
    }

    public void gotoRooms(int id, String houseName)
    {
        Intent intent = new Intent(HousesSystem.this, RoomsSystem.class);
        intent.putExtra("house_id",id);
        intent.putExtra("house_name",houseName);
        startActivity(intent);

    }
    private void setUpListView() {
        housesArrayList = new ArrayList<>();
        housesAdapter =new HousesAdapter(this,R.layout.item_layout_houses,housesArrayList);
        lvHouses.setAdapter(housesAdapter);
        displayAvailableHOuses();

    }

    private void displayAvailableHOuses() {
        Cursor cursor = MainActivity.database.GetData(
                "SELECT * FROM Houses ");
        housesArrayList.clear();
        while (cursor.moveToNext())
        {
            int id = cursor.getInt(0);
            String housesName = cursor.getString(1);
            String housesLocation = cursor.getString(2);

            housesArrayList.add(new Houses(id,housesName,housesLocation));
        }
        housesAdapter.notifyDataSetChanged();
    }

    private void initiation() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imgAddHouses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAddHouses();

            }
        });
    }

    private void dialogAddHouses() {
        Dialog dialogAddHouses = new Dialog(HousesSystem.this);
        dialogAddHouses.setContentView(R.layout.dialog_add_update_houses);
        dialogAddHouses.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        btnAddHouses            = (Button) dialogAddHouses.findViewById(R.id.btnAddHouses);
        edtGetHousesName        = (EditText) dialogAddHouses.findViewById(R.id.edtGetHousesName);
        edtGetHousesLocation    = (EditText) dialogAddHouses.findViewById(R.id.edtGetHousesLocation);

        Button  btnCancel  = (Button) dialogAddHouses.findViewById(R.id.btnCancel);


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAddHouses.dismiss();
            }
        });

        btnAddHouses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String housesName       = edtGetHousesName.getText().toString().trim();
                String housesLocation   = edtGetHousesLocation.getText().toString().trim();

                if (housesName.isEmpty())
                {
                    edtGetHousesName.setError("Required !");
                }
                else
                {
                    MainActivity.database.INSERT_HOUSES(housesName,housesLocation);
                    displayAvailableHOuses();
                    dialogAddHouses.dismiss();

                }
            }
        });

        dialogAddHouses.show();

    }

    public void dialogUpdateHouses(int id, String houseName, String houseLocation) {
        Dialog dialogUpdateHouses = new Dialog(HousesSystem.this);
        dialogUpdateHouses.setContentView(R.layout.dialog_add_update_houses);
        dialogUpdateHouses.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        btnUpdateHouses   = (Button) dialogUpdateHouses.findViewById(R.id.btnAddHouses);
        edtGetHousesName        = (EditText) dialogUpdateHouses.findViewById(R.id.edtGetHousesName);
        edtGetHousesLocation    = (EditText) dialogUpdateHouses.findViewById(R.id.edtGetHousesLocation);
        TextView txtTitle_dialog_add_update = (TextView) dialogUpdateHouses.findViewById(R.id.txtTitle_dialog_add_update);
        EditText edtGetHousesName   = (EditText) dialogUpdateHouses.findViewById(R.id.edtGetHousesName);
        EditText edtGetHousesLocation   = (EditText) dialogUpdateHouses.findViewById(R.id.edtGetHousesLocation);

        txtTitle_dialog_add_update.setText(R.string.housesSystem_update_layout_main_title);
        btnUpdateHouses.setText(R.string.housesSystem_update_button);

        edtGetHousesName.setText(houseName);
        edtGetHousesLocation.setText(houseLocation);

        Button  btnCancel  = (Button) dialogUpdateHouses.findViewById(R.id.btnCancel);


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogUpdateHouses.dismiss();
            }
        });
        btnUpdateHouses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updateName       = edtGetHousesName.getText().toString().trim();
                String updateLocation   = edtGetHousesLocation.getText().toString().trim();

                MainActivity.database.QueryDate("UPDATE Houses SET houseName = '" + updateName + "'" +
                        ", houseLocation = '" + updateLocation  + "'  WHERE housesId = '" + id + "' ");

                Toast.makeText(getApplicationContext(),"Update Successful ! ",Toast.LENGTH_LONG).show();
                displayAvailableHOuses();
                dialogUpdateHouses.dismiss();
            }
        });

        dialogUpdateHouses.show();

    }

    public void dialogDelete(int id)
    {
        Dialog dialog_delete = new Dialog(HousesSystem.this);
        dialog_delete.setContentView(R.layout.dialog_detele);
        dialog_delete.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button btnYes               = (Button) dialog_delete.findViewById(R.id.btn_allow_delete);
        Button btnNo                = (Button) dialog_delete.findViewById(R.id.btn_cancel_delete);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.database.QueryDate("DELETE FROM Houses WHERE housesId = '" + id + "'");
                MainActivity.database.QueryDate("DELETE FROM Rooms WHERE housesId = '" + id + "'");

                Toast.makeText(HousesSystem.this,"Delete Successful ! ",Toast.LENGTH_LONG).show();
                displayAvailableHOuses();
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
    private void mapting() {
        imgBack      = (ImageView) findViewById(R.id.imgBack);
        imgAddHouses = (ImageView) findViewById(R.id.imgAddHouses);
        lvHouses     = (ListView) findViewById(R.id.lvHouses);


    }
}