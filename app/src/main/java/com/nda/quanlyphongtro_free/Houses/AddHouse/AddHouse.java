package com.nda.quanlyphongtro_free.Houses.AddHouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nda.quanlyphongtro_free.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddHouse extends AppCompatActivity {

    TextView txt_selectThanhPho, txt_selectQuanHuyen;
    String strTinhThanhPho = "", strQuanHuyen = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_house);

        initUI();
        init();
    }
    private void init()
    {
        executeSelectCityState();
    }


    /**
     * (Related to) Select City State
     * */
    private void executeSelectCityState() {
        txt_selectThanhPho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogSelectThanhPho();
            }
        });

        txt_selectQuanHuyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txt_selectThanhPho.getText().toString().trim().equals(""))
                {
                    Toast.makeText(AddHouse.this, "Warning : Vui lòng chọn Tỉnh/Thành Phố !", Toast.LENGTH_SHORT).show();
                }
                else {
                    dialogSelectQuanHuyen();
                }
            }
        });
    }

    public void dialogSelectThanhPho() {
        Dialog dialog = new Dialog(AddHouse.this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_select_state_city);

        Button btnCancel = dialog.findViewById(R.id.btnCancel);
        androidx.appcompat.widget.SearchView  searchView_service = dialog.findViewById(R.id.searchView_service);
        RecyclerView rcv_stateCitySelection = dialog.findViewById(R.id.rcv_stateCitySelection);

        AdapterAddHouse adapterAddHouse = new AdapterAddHouse(this, ListTinhThanhPhoVN.listTinhThanhPho, dialog,
                txt_selectThanhPho);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AddHouse.this,RecyclerView.VERTICAL,false);
        rcv_stateCitySelection.setLayoutManager(linearLayoutManager);
        rcv_stateCitySelection.setAdapter(adapterAddHouse);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        dialog.show();
    }

    public void dialogSelectQuanHuyen() {
        Dialog dialog = new Dialog(AddHouse.this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_select_state_city);

        Button btnCancel = dialog.findViewById(R.id.btnCancel);
        androidx.appcompat.widget.SearchView  searchView_service = dialog.findViewById(R.id.searchView_service);
        RecyclerView rcv_stateCitySelection = dialog.findViewById(R.id.rcv_stateCitySelection);


        AdapterAddHouse adapterAddHouse = null;
        String strThanhPho = txt_selectThanhPho.getText().toString().trim();

        // Set default list for adapter
        adapterAddHouse = new AdapterAddHouse(this, ListTinhThanhPhoVN.listQuanHuyen_HaNoi, dialog, txt_selectThanhPho);

        if (strThanhPho.equals("Hà Nội"))
        {
            adapterAddHouse = new AdapterAddHouse(this, ListTinhThanhPhoVN.listQuanHuyen_HaNoi, dialog, txt_selectThanhPho);
        }
        else if (strThanhPho.equals("Hà Giang")){
            adapterAddHouse = new AdapterAddHouse(this, ListTinhThanhPhoVN.listQuanHuyen_HaGiang, dialog, txt_selectThanhPho);
        }
        else if (strThanhPho.equals("Cao Bằng")){
            adapterAddHouse = new AdapterAddHouse(this, ListTinhThanhPhoVN.listQuanHuyen_CaoBang, dialog, txt_selectThanhPho);
        }
        else if (strThanhPho.equals("Bắc Kạn")){
            adapterAddHouse = new AdapterAddHouse(this, ListTinhThanhPhoVN.listQuanHuyen_BacKan, dialog, txt_selectThanhPho);
        }
        else if (strThanhPho.equals("Tuyên Quang")){
            adapterAddHouse = new AdapterAddHouse(this, ListTinhThanhPhoVN.listQuanHuyen_TuyenQuang, dialog, txt_selectThanhPho);
        }
        else if (strThanhPho.equals("Lào Cai")){
            adapterAddHouse = new AdapterAddHouse(this, ListTinhThanhPhoVN.listQuanHuyen_LaoCai, dialog, txt_selectThanhPho);
        }
        else if (strThanhPho.equals("Điện Biên")){
            adapterAddHouse = new AdapterAddHouse(this, ListTinhThanhPhoVN.listQuanHuyen_DienBien, dialog, txt_selectThanhPho);
        }
        else if (strThanhPho.equals("Lai Châu")){
            adapterAddHouse = new AdapterAddHouse(this, ListTinhThanhPhoVN.listQuanHuyen_LaiChau, dialog, txt_selectThanhPho);
        }
        else if (strThanhPho.equals("Sơn La")){
            adapterAddHouse = new AdapterAddHouse(this, ListTinhThanhPhoVN.listQuanHuyen_SonLa, dialog, txt_selectThanhPho);
        }
        else if (strThanhPho.equals("Yên Bái")){
            adapterAddHouse = new AdapterAddHouse(this, ListTinhThanhPhoVN.listQuanHuyen_YenBai, dialog, txt_selectThanhPho);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AddHouse.this,RecyclerView.VERTICAL,false);
        rcv_stateCitySelection.setLayoutManager(linearLayoutManager);
        rcv_stateCitySelection.setAdapter(adapterAddHouse);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        dialog.show();
    }


    private void initUI() {
        txt_selectThanhPho     = findViewById(R.id.txt_selectThanhPho);
        txt_selectQuanHuyen    = findViewById(R.id.txt_selectQuanHuyen);

    }
}