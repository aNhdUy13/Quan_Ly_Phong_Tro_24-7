package com.nda.quanlyphongtro_free.Services;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nda.quanlyphongtro_free.Model.Service;
import com.nda.quanlyphongtro_free.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class AdapterServices extends RecyclerView.Adapter<AdapterServices.HolderServices> {
    ServicesSystem context;
    List<Service> serviceList;

    public AdapterServices(ServicesSystem context, List<Service> serviceList) {
        this.context = context;
        this.serviceList = serviceList;
    }

    @NonNull
    @Override
    public AdapterServices.HolderServices onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_services, parent,false);
        return new AdapterServices.HolderServices(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterServices.HolderServices holder, int position) {
        Service service = serviceList.get(position);

        holder.txtServicesName.setText(service.getName());
        /**
         * Format cost lấy về từ firebase
         * theo định dạng money
         * */
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        formatter.applyPattern("#,###,###,###");
        Double cost = Double.parseDouble(service.getPrice());
        holder.txtServicesCost.setText(formatter.format(cost) + " đ/" + service.getUnit());

        holder.ll_serviceItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.dialogUpdateService(service);
            }
        });

        holder.ll_serviceItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                context.dialogDeleteService(service);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }


    public class HolderServices extends RecyclerView.ViewHolder {
        LinearLayout ll_serviceItem;
        ImageView img_service;
        TextView txtServicesName, txtServicesCost;

        public HolderServices(@NonNull View itemView) {
            super(itemView);

            ll_serviceItem = itemView.findViewById(R.id.ll_serviceItem);

            img_service = itemView.findViewById(R.id.img_service);

            txtServicesName = itemView.findViewById(R.id.txtServicesName);
            txtServicesCost = itemView.findViewById(R.id.txtServicesCost);

        }
    }



}
