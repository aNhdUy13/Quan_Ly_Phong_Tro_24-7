package com.nda.quanlyphongtro_free.Houses.HouseDetail.Rooms.Payment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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

import java.util.ArrayList;

public class PaymentSystem extends AppCompatActivity {
    LinearLayout llGoBackRoom;
    private int roomFromTenantId, house_id;
    ImageView  imgAddPaymentForRoom;
    TextView txtGetRoom, txtShowNote, txtTitle_dialog_add_update;
    EditText edtGetPaymentMoney,edtGetPaymentTime,edtGetPaymentStatus,edtGetPaymentNote;
    Button btnAddPayment,btnCancel, btnOKNote,btnUpdatePayment;

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

    public void dialogUpdatePayment(int paymentID, String paymentMoney, String paymentStatus, String paymentDate, String paymentNote)
    {
        Dialog dialogUpdate = new Dialog(PaymentSystem.this);
        dialogUpdate.setContentView(R.layout.dialog_add_update_payment);
        dialogUpdate.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        txtTitle_dialog_add_update  = (TextView) dialogUpdate.findViewById(R.id.txtTitle_dialog_add_update);
        edtGetPaymentMoney      = (EditText) dialogUpdate.findViewById(R.id.edtGetPaymentMoney);
        edtGetPaymentTime       = (EditText) dialogUpdate.findViewById(R.id.edtGetPaymentTime);
        edtGetPaymentStatus     = (EditText) dialogUpdate.findViewById(R.id.edtGetPaymentStatus);
        edtGetPaymentNote       = (EditText) dialogUpdate.findViewById(R.id.edtGetPaymentNote);
        btnCancel       = (Button) dialogUpdate.findViewById(R.id.btnCancel);
        btnUpdatePayment   = (Button) dialogUpdate.findViewById(R.id.btnAddPayment);

        edtGetPaymentMoney.setText(paymentMoney);
        edtGetPaymentTime.setText(paymentDate);
        edtGetPaymentStatus.setText(paymentStatus);
        edtGetPaymentNote.setText(paymentNote);


        txtTitle_dialog_add_update.setText("SỬA THANH TOÁN");
        btnUpdatePayment.setText("LƯU");
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogUpdate.dismiss();
            }
        });
        btnUpdatePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String money    = edtGetPaymentMoney.getText().toString().trim();
                String status    = edtGetPaymentStatus.getText().toString().trim();
                String date    = edtGetPaymentTime.getText().toString().trim();
                String note    = edtGetPaymentNote.getText().toString().trim();

                MainActivity.database.QueryDate("UPDATE Payments SET paymentMoney = '" + money + "'" +
                        ", paymentStatus = '" + status  + "' , paymentDate = '" + date +
                         "' , paymentNote = '" + note + "'  WHERE paymentsId = '" + paymentID + "' ");

                Toast.makeText(getApplicationContext(),"Update Successful ! " ,Toast.LENGTH_LONG).show();

                displayPayment();
                dialogUpdate.dismiss();
            }
        });


        dialogUpdate.show();
    }

    public void dialogShowPaymentNote(String paymentNote) {
        Dialog dialogNote = new Dialog(PaymentSystem.this);
        dialogNote.setContentView(R.layout.dialog_show_note);
        dialogNote.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        txtShowNote = (TextView) dialogNote.findViewById(R.id.txtShowNote);
        btnOKNote = (Button) dialogNote.findViewById(R.id.btnOKNote);
        btnOKNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogNote.dismiss();
            }
        });

        txtShowNote.setText(paymentNote);

        dialogNote.show();
    }
}