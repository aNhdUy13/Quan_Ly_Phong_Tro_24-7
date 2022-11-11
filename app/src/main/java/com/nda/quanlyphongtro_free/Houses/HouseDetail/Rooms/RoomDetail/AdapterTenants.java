package com.nda.quanlyphongtro_free.Houses.HouseDetail.Rooms.RoomDetail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nda.quanlyphongtro_free.Model.Tenants;
import com.nda.quanlyphongtro_free.R;

import java.util.List;

public class AdapterTenants extends RecyclerView.Adapter<AdapterTenants.HolderTenants> {
    RoomDetailSystem context;
    List<Tenants> tenantsList;

    public AdapterTenants(RoomDetailSystem context, List<Tenants> tenantsList) {
        this.context = context;
        this.tenantsList = tenantsList;
    }

    @NonNull
    @Override
    public AdapterTenants.HolderTenants onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tenants, parent, false);
        return new AdapterTenants.HolderTenants(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTenants.HolderTenants holder, int position) {
        Tenants tenants = tenantsList.get(position);

        holder.txt_tenantName.setText(tenants.gettName());
        holder.txt_tenantPhonenumber.setText(tenants.gettPhoneNumber());
        holder.txt_tenantRentHouseAndRoom.setText(tenants.gettRentHouse() + ", " + tenants.gettRentRoom());

        holder.cv_tenant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return tenantsList.size();
    }

    public class HolderTenants extends RecyclerView.ViewHolder {
        CardView cv_tenant;
        TextView txt_tenantName, txt_tenantPhonenumber, txt_tenantRentHouseAndRoom;

        public HolderTenants(@NonNull View itemView) {
            super(itemView);

            cv_tenant = itemView.findViewById(R.id.cv_tenant);
            txt_tenantName        = itemView.findViewById(R.id.txt_tenantName);
            txt_tenantPhonenumber = itemView.findViewById(R.id.txt_tenantPhonenumber);
            txt_tenantRentHouseAndRoom  = itemView.findViewById(R.id.txt_tenantRentHouseAndRoom);

        }
    }
}
