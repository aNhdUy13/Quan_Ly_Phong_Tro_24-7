package com.nda.quanlyphongtro_free.Tenants;

import android.app.Dialog;
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

import androidx.appcompat.app.AppCompatActivity;


import com.nda.quanlyphongtro_free.MainActivity;
import com.nda.quanlyphongtro_free.R;

import java.util.ArrayList;

public class TenantsSystem extends AppCompatActivity {

    ImageView imgBack, imgAddTenants;
    TextView txtHRoomNameInTerants;
    private int roomID;
    EditText edtGetTenantName,edtGetPhoneNumber,edtGetNote;


    ListView lvTenants;
    ArrayList<Tenants> tenantsArrayList;
    TenantsAdapter tenantsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenants_system);
        mapting();
        initiate();
        setUpListView();



    }
    private void setUpListView() {
        tenantsArrayList = new ArrayList<>();
        tenantsAdapter = new TenantsAdapter(this,R.layout.item_layout_tenants,tenantsArrayList);
        lvTenants.setAdapter(tenantsAdapter);

        displayTenants();
    }

    private void initiate() {
        roomID  = getIntent().getIntExtra("roomID",0);
        String roomName = getIntent().getStringExtra("roomName");
        txtHRoomNameInTerants.setText(roomName);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imgAddTenants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAddTenant();
            }
        });
    }

    private void dialogAddTenant() {
        Dialog dialog = new Dialog(TenantsSystem.this);
        dialog.setContentView(R.layout.dialog_add_update_tenant);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        EditText edtGetTenantName    = (EditText) dialog.findViewById(R.id.edtGetTenantName);
        EditText edtGetPhoneNumber    = (EditText) dialog.findViewById(R.id.edtGetPhoneNumber);
        EditText edtGetNote    = (EditText) dialog.findViewById(R.id.edtGetTenantNote);

        Button btnAddTenant  = (Button) dialog.findViewById(R.id.btnAddTenant);
        Button  btnCancel  = (Button) dialog.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnAddTenant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenantName, note,tenantPhoneNumber;

                tenantPhoneNumber   = edtGetPhoneNumber.getText().toString().trim();
                note   = edtGetNote.getText().toString().trim();
                tenantName   = edtGetTenantName.getText().toString().trim();

                MainActivity.database.INSERT_TENANTS(roomID,tenantName,tenantPhoneNumber,note);
                displayTenants();
                dialog.dismiss();
            }
        });


        dialog.show();
    }

    private void displayTenants() {
        Cursor cursor = MainActivity.database.GetData(
                "SELECT * FROM Tenants WHERE roomId = " + roomID);
        tenantsArrayList.clear();
        while (cursor.moveToNext())
        {
            int id = cursor.getInt(0);
            int roomID = cursor.getInt(1);
            String name = cursor.getString(2);
            String phoneNumber = cursor.getString(3);
            String note = cursor.getString(4);

            tenantsArrayList.add(new Tenants(id,name,phoneNumber,note));
        }
        tenantsAdapter.notifyDataSetChanged();
    }

    public void dialogUpdateTenant(int tenantId, String tenantName, String tenantPhoneNumber, String tenantNote)
    {
        Dialog dialogUpdateTenant = new Dialog(TenantsSystem.this);
        dialogUpdateTenant.setContentView(R.layout.dialog_add_update_tenant);
        dialogUpdateTenant.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        edtGetTenantName  = (EditText) dialogUpdateTenant.findViewById(R.id.edtGetTenantName) ;
        edtGetPhoneNumber = (EditText) dialogUpdateTenant.findViewById(R.id.edtGetPhoneNumber);
        edtGetNote        = (EditText) dialogUpdateTenant.findViewById(R.id.edtGetTenantNote);

        Button  btnUpdateTenant  = (Button) dialogUpdateTenant.findViewById(R.id.btnAddTenant);
        Button  btnCancel  = (Button) dialogUpdateTenant.findViewById(R.id.btnCancel);
        TextView txtTitleTenant   = (TextView) dialogUpdateTenant.findViewById(R.id.txtTitleTenant);

        edtGetTenantName.setText(tenantName);
        edtGetPhoneNumber.setText(tenantPhoneNumber);
        edtGetNote.setText(tenantNote);

        txtTitleTenant.setText(R.string.tenantsSystem_update_title);
        btnUpdateTenant.setText(R.string.housesSystem_update_button);

        btnUpdateTenant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenantName = edtGetTenantName.getText().toString().trim();
                String noteTenant = edtGetNote.getText().toString().trim();
                String tenantPhoneNumber = edtGetPhoneNumber.getText().toString().trim();

                MainActivity.database.QueryDate("UPDATE Tenants SET tenantsName = '" + tenantName + "'" +
                        ", tenantsPhonenumber = '" + tenantPhoneNumber  + "' , tenantsNote = '" + noteTenant +
                        "'  WHERE tenantsId = '" + tenantId + "' ");

                Toast.makeText(getApplicationContext(),"Update Successful ! " ,Toast.LENGTH_LONG).show();
                displayTenants();
                dialogUpdateTenant.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogUpdateTenant.dismiss();
            }
        });


        dialogUpdateTenant.show();
    }
    public void dialogDeleteTenant(int id)
    {
        Dialog dialog_delete = new Dialog(TenantsSystem.this);
        dialog_delete.setContentView(R.layout.dialog_detele);
        dialog_delete.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button btnYes               = (Button) dialog_delete.findViewById(R.id.btn_allow_delete);
        Button btnNo                = (Button) dialog_delete.findViewById(R.id.btn_cancel_delete);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.database.QueryDate("DELETE FROM Tenants WHERE tenantsId = '" + id + "'");

                Toast.makeText(TenantsSystem.this,"Delete Successful ! ",Toast.LENGTH_LONG).show();
                displayTenants();
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

    public void dialogShowNote(String note)
    {
        Dialog dialog = new Dialog(TenantsSystem.this);
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

    private void mapting() {
        imgBack = (ImageView) findViewById(R.id.imgBack);
        txtHRoomNameInTerants   = (TextView) findViewById(R.id.txtHRoomNameInTerants);
        imgAddTenants   = (ImageView) findViewById(R.id.imgAddTenants);
        lvTenants       = (ListView) findViewById(R.id.lvTenants);
    }
}