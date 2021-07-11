package com.nda.quanlyphongtro_free.Tenants;

import android.app.Dialog;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.nda.quanlyphongtro_free.MainActivity;
import com.nda.quanlyphongtro_free.R;

import java.util.ArrayList;

public class AllTenantsSystem extends AppCompatActivity {
    ArrayList<AllTenants> allTenantsArrayList;
    AllTenantsAdapter allTenantsAdapter;
    ListView lvAllTenants;
    ImageView   imgBack ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tenants);
        mapting();
        initiate();
        setUpListView();

    }
    private void setUpListView() {
        allTenantsArrayList = new ArrayList<>();
        allTenantsAdapter = new AllTenantsAdapter(this,R.layout.item_layout_tenants,allTenantsArrayList);
        lvAllTenants.setAdapter(allTenantsAdapter);

        displayAllTenants();
    }

    private void displayAllTenants() {
        Cursor cursor = MainActivity.database.GetData(
                "SELECT * FROM Tenants ");
        allTenantsArrayList.clear();
        while (cursor.moveToNext())
        {
            int id = cursor.getInt(0);
            int roomID = cursor.getInt(1);
            String name = cursor.getString(2);
            String phoneNumber = cursor.getString(3);
            String note = cursor.getString(4);

            allTenantsArrayList.add(new AllTenants(id,name,phoneNumber,note));
        }
        allTenantsAdapter.notifyDataSetChanged();
    }
    public void dialogShowNote(String note)
    {
        Dialog dialog = new Dialog(AllTenantsSystem.this);
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

    private void initiate() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void mapting() {
        lvAllTenants    = (ListView) findViewById(R.id.lvAllTenants);
        imgBack = (ImageView) findViewById(R.id.imgBack);

    }
}