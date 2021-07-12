package com.nda.quanlyphongtro_free.Rooms.Payment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nda.quanlyphongtro_free.MainActivity;
import com.nda.quanlyphongtro_free.R;
import com.nda.quanlyphongtro_free.Rooms.Rooms;
import com.nda.quanlyphongtro_free.Rooms.RoomsSystem;
import com.nda.quanlyphongtro_free.Tenants.TenantsSystem;

import java.util.ArrayList;

public class PaymentSystem extends AppCompatActivity {
    LinearLayout llGoBackRoom;
    private int roomFromTenantId, house_id;
    ImageView  imgAddPaymentForRoom;
    TextView txtGetRoom;
    EditText edtGetPaymentMoney,edtGetPaymentTime,edtGetPaymentStatus,edtGetPaymentNote;
    Button btnAddPayment,btnCancel;

    ArrayList<Payments> paymentsArrayList;
    PaymentAdapter paymentAdapter;
    ListView lvPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_system);
        mapting();
        initiate();
        setupListView();
    }

    private void setupListView() {
        paymentsArrayList = new ArrayList<>();
        paymentAdapter = new PaymentAdapter(this,R.layout.item_layout_payment,paymentsArrayList);
        lvPayment.setAdapter(paymentAdapter);

        displayPayment();

    }

    private void initiate() {

        roomFromTenantId  = getIntent().getIntExtra("roomIDFromTenant",0);
        String roomFromTenantName = getIntent().getStringExtra("roomNameFromTenant");
        txtGetRoom.setText(roomFromTenantName);

        house_id  = getIntent().getIntExtra("houseIDFromRoom",0);

        imgAddPaymentForRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAddPayment();
            }
        });
        llGoBackRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void dialogDeletePayment(int id)
    {
        Dialog dialog_delete = new Dialog(PaymentSystem.this);
        dialog_delete.setContentView(R.layout.dialog_detele);
        dialog_delete.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button btnYes               = (Button) dialog_delete.findViewById(R.id.btn_allow_delete);
        Button btnNo                = (Button) dialog_delete.findViewById(R.id.btn_cancel_delete);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.database.QueryDate("DELETE FROM Payments WHERE paymentsId = '" + id + "'");

                Toast.makeText(PaymentSystem.this,"Delete Successful ! ",Toast.LENGTH_LONG).show();
                displayPayment();
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
    private void dialogAddPayment() {
        Dialog dialogPayment = new Dialog(PaymentSystem.this);
        dialogPayment.setContentView(R.layout.dialog_add_update_payment);
        dialogPayment.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        edtGetPaymentMoney      = (EditText) dialogPayment.findViewById(R.id.edtGetPaymentMoney);
        edtGetPaymentTime       = (EditText) dialogPayment.findViewById(R.id.edtGetPaymentTime);
        edtGetPaymentStatus     = (EditText) dialogPayment.findViewById(R.id.edtGetPaymentStatus);
        edtGetPaymentNote       = (EditText) dialogPayment.findViewById(R.id.edtGetPaymentNote);
        btnCancel       = (Button) dialogPayment.findViewById(R.id.btnCancel);
        btnAddPayment   = (Button) dialogPayment.findViewById(R.id.btnAddPayment);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogPayment.dismiss();
            }
        });
        btnAddPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String money = edtGetPaymentMoney.getText().toString().trim();
                String status = edtGetPaymentStatus.getText().toString().trim();
                String date = edtGetPaymentTime.getText().toString().trim();
                String note = edtGetPaymentNote.getText().toString().trim();

                MainActivity.database.INSERT_PAYMENT(roomFromTenantId,money,status,date,note,house_id);
                displayPayment();
                dialogPayment.dismiss();

            }
        });

        dialogPayment.show();
    }

    private void displayPayment() {
        Cursor cursor = MainActivity.database.GetData(
                "SELECT * FROM Payments WHERE roomId = " + roomFromTenantId);
        paymentsArrayList.clear();
        while (cursor.moveToNext())
        {
            int id = cursor.getInt(0);
            int roomId = cursor.getInt(1);
            String paymentMoney = cursor.getString(2);
            String paymentStatus = cursor.getString(3);
            String paymentDate = cursor.getString(4);
            String paymentNote = cursor.getString(5);

            paymentsArrayList.add(new Payments(id,paymentMoney,paymentStatus,paymentDate,paymentNote));
        }
        paymentAdapter.notifyDataSetChanged();
    }

    private void mapting() {

        imgAddPaymentForRoom    = (ImageView) findViewById(R.id.imgAddPaymentForRoom);
        lvPayment       = (ListView) findViewById(R.id.lvPayment);
        txtGetRoom      = (TextView) findViewById(R.id.txtGetRoom);
        llGoBackRoom    = (LinearLayout) findViewById(R.id.llGoBackRoom);

    }



}