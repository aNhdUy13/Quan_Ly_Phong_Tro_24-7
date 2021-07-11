package com.nda.quanlyphongtro_free.Houses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nda.quanlyphongtro_free.R;

import java.util.ArrayList;

public class HousesAdapter extends BaseAdapter {
    private HousesSystem context;
    private int layout;
    ArrayList<Houses> housesArrayList;

    public HousesAdapter(HousesSystem context, int layout, ArrayList<Houses> housesArrayList) {
        this.context = context;
        this.layout = layout;
        this.housesArrayList = housesArrayList;
    }

    @Override
    public int getCount() {
        return housesArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    class ViewHolder{
        TextView txtHouseName, txtHouseLocation;
        ImageView imgUpdateHouse,imgDeleteHouse;
        LinearLayout llItemHouses;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null)
        {
            holder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater)   context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout,null);

            holder.txtHouseName  = (TextView) convertView.findViewById(R.id.txtHouseName);
            holder.txtHouseLocation = (TextView) convertView.findViewById(R.id.txtHouseLocation);
            holder.imgDeleteHouse   = (ImageView) convertView.findViewById(R.id.imgDeleteHouse);
            holder.imgUpdateHouse   = (ImageView) convertView.findViewById(R.id.imgUpdateHouse);
            holder.llItemHouses     = (LinearLayout) convertView.findViewById(R.id.llItemHouses);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)   convertView.getTag();

        }

        Houses houses = housesArrayList.get(position);

        holder.txtHouseName.setText(houses.getHousesName());
        holder.txtHouseLocation.setText(houses.getHousesLocation());
        holder.llItemHouses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.gotoRooms(houses.getHousesId(),houses.getHousesName());
            }
        });
        holder.imgDeleteHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.dialogDelete(houses.getHousesId());
            }
        });

        holder.imgUpdateHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.dialogUpdateHouses(houses.getHousesId(),houses.getHousesName(),houses.getHousesLocation());
            }
        });
        return convertView;
    }
}
