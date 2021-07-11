package com.nda.quanlyphongtro_free.Services;

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

public class ServicesSystem extends AppCompatActivity {

    ImageView imgAddServices,imgBack;
    EditText edtGetServiceName,edtGetServicePrice,edtGetServiceNote;
    ArrayList<Service> serviceArrayList;
    ServicesAdapter servicesAdapter;
    ListView lvServices;
    TextView txtTitleService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_system);
        mapting();
        initiation();
        setUpListView();

    }

    private void setUpListView() {
        serviceArrayList = new ArrayList<>();
        servicesAdapter = new ServicesAdapter(serviceArrayList,R.layout.item_layout_services,this);
        lvServices.setAdapter(servicesAdapter);

        displayServices();
    }

    private void displayServices() {
        Cursor cursor = MainActivity.database.GetData(
                "SELECT * FROM Services ");
        serviceArrayList.clear();
        while (cursor.moveToNext())
        {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String cost = cursor.getString(2);
            String note = cursor.getString(3);

            serviceArrayList.add(new Service(id,name,cost,note));
        }
        servicesAdapter.notifyDataSetChanged();
    }

    private void initiation() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imgAddServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAddServices();
            }
        });
    }
    public void dialogShowNote(String note)
    {
        Dialog dialogShowNote = new Dialog(ServicesSystem.this);
        dialogShowNote.setContentView(R.layout.dialog_show_note);
        dialogShowNote.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView txtShowNote    = (TextView) dialogShowNote.findViewById(R.id.txtShowNote);
        Button btnOKNote    = (Button) dialogShowNote.findViewById(R.id.btnOKNote);

        txtShowNote.setText(note);
        btnOKNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogShowNote.dismiss();
            }
        });
        dialogShowNote.show();
    }
    public void dialogUpdateService(int id, String name, String price, String note)
    {
        Dialog dialog_update = new Dialog(ServicesSystem.this);
        dialog_update.setContentView(R.layout.dialog_add_update_services);
        dialog_update.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        txtTitleService = (TextView) dialog_update.findViewById(R.id.txtTitleService);
        edtGetServiceName   = (EditText) dialog_update.findViewById(R.id.edtGetServiceName);
        edtGetServicePrice   = (EditText) dialog_update.findViewById(R.id.edtGetServicePrice);
        edtGetServiceNote   = (EditText) dialog_update.findViewById(R.id.edtGetServiceNote);

        edtGetServiceName.setText(name);
        edtGetServicePrice.setText(price);
        edtGetServiceNote.setText(note);

        txtTitleService.setText(R.string.serciesSystem_update_title);
        Button btnService   = (Button) dialog_update.findViewById(R.id.btnService);
        Button btnCancel   = (Button) dialog_update.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_update.dismiss();
            }
        });

        btnService.setText(R.string.housesSystem_update_button);
        btnService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtGetServiceName.getText().toString().trim();
                String price = edtGetServicePrice.getText().toString().trim();
                String note = edtGetServiceNote.getText().toString().trim();


                MainActivity.database.QueryDate("UPDATE Services SET servicesName = '" + name + "'" +
                        ", servicesCost = '" + price  + "' , servicesNote = '" + note +
                        "'  WHERE servicesId = '" + id + "' ");

                Toast.makeText(getApplicationContext(),"Update Successful ! " ,Toast.LENGTH_LONG).show();
                displayServices();
                dialog_update.dismiss();
            }
        });
        dialog_update.show();


    }
    public void dialogDeleteService(int id)
    {
        Dialog dialog_delete = new Dialog(ServicesSystem.this);
        dialog_delete.setContentView(R.layout.dialog_detele);
        dialog_delete.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button btnYes               = (Button) dialog_delete.findViewById(R.id.btn_allow_delete);
        Button btnNo                = (Button) dialog_delete.findViewById(R.id.btn_cancel_delete);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.database.QueryDate("DELETE FROM Services WHERE servicesId = '" + id + "'");

                Toast.makeText(ServicesSystem.this,"Delete Successful ! ",Toast.LENGTH_SHORT).show();
                displayServices();
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

    private void dialogAddServices() {
        Dialog dialog = new Dialog(ServicesSystem.this);
        dialog.setContentView(R.layout.dialog_add_update_services);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        edtGetServiceName   = (EditText) dialog.findViewById(R.id.edtGetServiceName);
        edtGetServicePrice   = (EditText) dialog.findViewById(R.id.edtGetServicePrice);
        edtGetServiceNote   = (EditText) dialog.findViewById(R.id.edtGetServiceNote);

        Button btnService   = (Button) dialog.findViewById(R.id.btnService);
        Button btnCancel   = (Button) dialog.findViewById(R.id.btnCancel);

        btnService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String serviceName = edtGetServiceName.getText().toString().trim();
                String servicePrice = edtGetServicePrice.getText().toString().trim();
                String serviceNote = edtGetServiceNote.getText().toString().trim();

                MainActivity.database.INSERT_SERVICES(serviceName,servicePrice,serviceNote);
                displayServices();
                dialog.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });



        dialog.show();
    }

    private void mapting() {
        imgAddServices  = (ImageView) findViewById(R.id.imgAddServices);
        imgBack  = (ImageView) findViewById(R.id.imgBack);

        lvServices  = (ListView) findViewById(R.id.lvServices);

    }
}