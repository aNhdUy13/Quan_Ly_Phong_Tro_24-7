package com.nda.quanlyphongtro_free.Rooms.Payment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nda.quanlyphongtro_free.R;
import com.nda.quanlyphongtro_free.Rooms.RoomAdapter;
import com.nda.quanlyphongtro_free.Rooms.Rooms;

import java.util.ArrayList;

public class PaymentAdapter extends BaseAdapter {
    private PaymentSystem context;
    private int layout;
    private ArrayList<Payments> paymentsArrayList;

    public PaymentAdapter(PaymentSystem context, int layout, ArrayList<Payments> paymentsArrayList) {
        this.context = context;
        this.layout = layout;
        this.paymentsArrayList = paymentsArrayList;
    }

    @Override
    public int getCount() {
        return paymentsArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    class ViewHolder
    {
        TextView txtShowPaymentDate, txtShowPaymentStatus,txtShowPaymentMoney;
        ImageView imgShowNotePayment,imgUpdatePayment,imgDeletePayment;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null)
        {
            holder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater)   context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout,null);

            holder.txtShowPaymentDate   = (TextView) convertView.findViewById(R.id.txtShowPaymentDate);
            holder.txtShowPaymentStatus   = (TextView) convertView.findViewById(R.id.txtShowPaymentStatus);
            holder.txtShowPaymentMoney   = (TextView) convertView.findViewById(R.id.txtShowPaymentMoney);

            holder.imgShowNotePayment   = (ImageView) convertView.findViewById(R.id.imgShowNotePayment);
            holder.imgUpdatePayment   = (ImageView) convertView.findViewById(R.id.imgUpdatePayment);
            holder.imgDeletePayment   = (ImageView) convertView.findViewById(R.id.imgDeletePayment);


            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        Payments payments = paymentsArrayList.get(position);

        holder.txtShowPaymentDate.setText(payments.getPaymentPurchaseDate());
        holder.txtShowPaymentStatus.setText(payments.getPaymentStatus());
        holder.txtShowPaymentMoney.setText(payments.getPaymentPurchased());

        holder.imgShowNotePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.dialogShowPaymentNote(payments.getPaymentNote());
            }
        });
        holder.imgUpdatePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.dialogUpdatePayment(payments.getPaymentId(),payments.getPaymentPurchased(),payments.getPaymentStatus(),
                        payments.getPaymentPurchaseDate(),payments.getPaymentNote());
            }
        });

        holder.imgDeletePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.dialogDeletePayment(payments.getPaymentId());
            }
        });
        return convertView;
    }
}
