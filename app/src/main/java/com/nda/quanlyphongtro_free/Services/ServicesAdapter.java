package com.nda.quanlyphongtro_free.Services;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.nda.quanlyphongtro_free.R;

import java.util.List;

public class ServicesAdapter extends BaseAdapter {
    private List<Service> serviceList;
    private int layout;
    private ServicesSystem context;

    public ServicesAdapter(List<Service> serviceList, int layout, ServicesSystem context) {
        this.serviceList = serviceList;
        this.layout = layout;
        this.context = context;
    }

    @Override
    public int getCount() {
        return serviceList.size();
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
        TextView txtServicesName,txtServicesCost;
        ImageView imgUpdateServices,imgDeleteServices,imgShowServicesNote;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null)
        {
            holder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater)   context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout,null);

            holder.txtServicesName  = (TextView) convertView.findViewById(R.id.txtServicesName);
            holder.txtServicesCost = (TextView) convertView.findViewById(R.id.txtServicesCost);
            holder.imgUpdateServices    = (ImageView) convertView.findViewById(R.id.imgUpdateServices);
            holder.imgDeleteServices    = (ImageView) convertView.findViewById(R.id.imgDeleteServices);
            holder.imgShowServicesNote = (ImageView) convertView.findViewById(R.id.imgShowServicesNote);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        Service service = serviceList.get(position);

        holder.txtServicesName.setText(service.getName());
        holder.txtServicesCost.setText(service.getPrice());

        holder.imgShowServicesNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.dialogShowNote(service.getNote());
            }
        });
        holder.imgUpdateServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.dialogUpdateService(service.getId(),service.getName(),service.getPrice(),service.getNote());
            }
        });

        holder.imgDeleteServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.dialogDeleteService(service.getId());
            }
        });

        return convertView;
    }
}
