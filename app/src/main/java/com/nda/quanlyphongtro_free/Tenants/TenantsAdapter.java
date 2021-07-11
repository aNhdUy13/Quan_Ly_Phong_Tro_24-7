package com.nda.quanlyphongtro_free.Tenants;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.nda.quanlyphongtro_free.R;

import java.util.ArrayList;

public class TenantsAdapter extends BaseAdapter {

    private TenantsSystem context;
    private int layout;
    private ArrayList<Tenants> tenantsArrayList;

    public TenantsAdapter(TenantsSystem context, int layout, ArrayList<Tenants> tenantsArrayList) {
        this.context = context;
        this.layout = layout;
        this.tenantsArrayList = tenantsArrayList;
    }

    @Override
    public int getCount() {
        return tenantsArrayList.size();
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
        TextView txtTenantName,txtTenantPhonenumber;
        ImageView imgUpdateTenant,imgDeleteTenant,imgShowNote;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null)
        {
            holder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater)   context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout,null);

            holder.txtTenantName  = (TextView) convertView.findViewById(R.id.txtTenantName);
            holder.txtTenantPhonenumber = (TextView) convertView.findViewById(R.id.txtTenantPhonenumber);
            holder.imgUpdateTenant      = (ImageView) convertView.findViewById(R.id.imgUpdateTenant);
            holder.imgDeleteTenant      = (ImageView) convertView.findViewById(R.id.imgDeleteTenant);
            holder.imgShowNote          = (ImageView) convertView.findViewById(R.id.imgShowNote);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        Tenants tenants = tenantsArrayList.get(position);

        Log.d("CHECK_TENANT","" + tenants.getId());

        holder.txtTenantName.setText(tenants.getName());
        holder.txtTenantPhonenumber.setText(tenants.getPhonenumber());

        holder.imgShowNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.dialogShowNote(tenants.getNote());
            }
        });
        holder.imgDeleteTenant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.dialogDeleteTenant(tenants.getId());
            }
        });
        holder.imgUpdateTenant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.dialogUpdateTenant(tenants.getId(),tenants.getName(),tenants.getPhonenumber(), tenants.getNote());
            }
        });

        return convertView;
    }
}
