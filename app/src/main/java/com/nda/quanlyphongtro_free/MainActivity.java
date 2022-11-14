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
import com.nda.quanlyphongtro_free.Setting.Setting;
import com.nda.quanlyphongtro_free.Tenants.TenantsSystem;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    CardView cvNhaTro, cvDichVu,cv_quanLyTenants;
    ImageView imgSetting;

    TextView getTotalHouse, getTotalRoom,getTotalTenants,getTotalAvailableServices;
    public static db database;
    String  dbName = "MotelManagement.sqlite";

    public static String tableHouses                 = "Houses";
    public static String column1_houses_id           = "housesId";
    public static String column2_houses_name         = "houseName";
    public static String column3_houses_location     = "houseLocation";


    public static String tableRoom = "Rooms";
    public static String column1_rooms_id               = "roomId"; // Room can belong to some houses.
    public static String column2ForeignKey_houses_id    = "housesId"; // Room can belong to some houses.
    public static String column3_rooms_roomPrice        = "roomPrice";
    public static String column4_rooms_note             = "roomsNote";
    public static String column5_rooms_name             = "roomsName";

    public static String tableTenants       = "Tenants";
    public static String column1_tenants_id             = "tenantsId";
    public static String columnForeignKey_rooms_id      = "roomId";
    public static String column2_tenants_name           = "tenantsName";
    public static String column3_tenants_phonenumber    = "tenantsPhonenumber";
    public static String column4_tenants_note           = "tenantsNote";
    public static String column5_ForeignKey_houses_id   = "housesId";

    public static String tableServices       = "Services";
    public static String column1_services_id            = "servicesId"; // Tenants can belong to some rooms.
    public static String column2_services_name          = "servicesName"; // Tenants can belong to some rooms.
    public static String column3_services_cost          = "servicesCost";
    public static String column4_services_note          = "servicesNote";

    public static String tableContracts       = "Contracts";
    public static String column1_contract_id            = "contractId";
    public static String column2_contract_img           = "contractImg";

    public static String tablePayment       = "Payments";
    public static String column1_payment_id                     = "paymentsId";
    public static String columnForeignKey_payment_rooms_id      = "roomId";
    public static String column2_payment_money                  = "paymentMoney";
    public static String column3_payment_status                 = "paymentStatus";
    public static String column4_payment_date                   = "paymentDate";
    public static String column5_payment_note                   = "paymentNote";
    public static String column6_ForeignKey_houses_id           = "housesId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapting();
        createDatabase();
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

        displayMotelData();

        imgSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Setting.class));
            }
        });

    }

    private void displayMotelData() {

        executeData("housesId","Houses",getTotalHouse);
        executeData("roomId","Rooms",getTotalRoom);
        executeData("tenantsId","Tenants",getTotalTenants);
        executeData("servicesId","Services",getTotalAvailableServices);


    }
    private void executeData(String column, String table,TextView txtDisplayData)
    {
        Cursor cursor = MainActivity.database.GetData(
                "SELECT " +column+" FROM " + table);
        List<String> list = new ArrayList<>();
        while (cursor.moveToNext())
        {
            int getId = cursor.getInt(0);
            list.add(String.valueOf(getId));
        }
        int count = 0;
        for (int i = 0 ; i < list.size() ; i++)
        {
            count ++;
        }
        txtDisplayData.setText(String.valueOf(count));
    }


    private void createDatabase() {
        database = new db(this,dbName,null,1);

        database.QueryDate("CREATE TABLE IF NOT EXISTS " + tableHouses
                + "(" + column1_houses_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + column2_houses_name + " VARCHAR(200), "
                + column3_houses_location + " VARCHAR(200) )");
        //database.QueryDate("DROP TABLE IF EXISTS " + tableHouses);

        database.QueryDate("CREATE TABLE IF NOT EXISTS " + tableRoom
                + "(" + column1_rooms_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + column2ForeignKey_houses_id + " INTEGER , "
                + column3_rooms_roomPrice + " VARCHAR(200), "
                + column4_rooms_note + " VARCHAR(200), "
                + column5_rooms_name + " VARCHAR(200) )");
//        database.QueryDate("DROP TABLE IF EXISTS " + tableRoom);

        database.QueryDate("CREATE TABLE IF NOT EXISTS " + tableTenants
                + "(" + column1_tenants_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + columnForeignKey_rooms_id + " INTEGER , "
                + column2_tenants_name + " VARCHAR(200), "
                + column3_tenants_phonenumber + " VARCHAR(200),"
                + column4_tenants_note + " VARCHAR(200),"
                + column5_ForeignKey_houses_id + " VARCHAR(200) )");
//        database.QueryDate("DROP TABLE IF EXISTS " + tableTenants);

        database.QueryDate("CREATE TABLE IF NOT EXISTS " + tableServices
                + "(" + column1_services_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + column2_services_name + " VARCHAR(200), "
                + column3_services_cost + " VARCHAR(200),"
                + column4_services_note + " VARCHAR(200) )");
        //database.QueryDate("DROP TABLE IF EXISTS " + tableHouses);

        database.QueryDate("CREATE TABLE IF NOT EXISTS " + tableContracts
                + "(" + column1_contract_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + column2_contract_img + " BLOB )");
        //database.QueryDate("DROP TABLE IF EXISTS " + tableContracts);

        database.QueryDate("CREATE TABLE IF NOT EXISTS " + tablePayment
                + "(" + column1_payment_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + columnForeignKey_payment_rooms_id + " INTEGER , "
                + column2_payment_money + " VARCHAR(200), "
                + column3_payment_status + " VARCHAR(200), "
                + column4_payment_date + " VARCHAR(200), "
                + column5_payment_note + " VARCHAR(200), "
                + column6_ForeignKey_houses_id + " VARCHAR(200) )");
//        database.QueryDate("DROP TABLE IF EXISTS " + tablePayment);

    }

    private void mapting() {
        cvNhaTro    = (CardView) findViewById(R.id.cvNhaTro);
        cvDichVu    = (CardView) findViewById(R.id.cvDichVu);
        cv_quanLyTenants    = (CardView) findViewById(R.id.cv_quanLyTenants);

        getTotalHouse   = (TextView) findViewById(R.id.getTotalHouse);
        getTotalRoom    = (TextView) findViewById(R.id.getTotalRoom);
        getTotalTenants = (TextView) findViewById(R.id.getTotalTenants);
        getTotalAvailableServices   = (TextView) findViewById(R.id.getTotalAvailableServices);

        imgSetting  = (ImageView) findViewById(R.id.img_setting);
    }
}