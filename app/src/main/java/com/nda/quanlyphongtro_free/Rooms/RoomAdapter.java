package com.nda.quanlyphongtro_free.Rooms;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nda.quanlyphongtro_free.MainActivity;
import com.nda.quanlyphongtro_free.R;

import java.util.ArrayList;
import java.util.List;

public class RoomAdapter extends BaseAdapter {
    private RoomsSystem context;
    private int layout;
    private ArrayList<Rooms> roomsArrayList;

    public RoomAdapter(RoomsSystem context, int layout, ArrayList<Rooms> roomsArrayList) {
        this.context = context;
        this.layout = layout;
        this.roomsArrayList = roomsArrayList;
    }

    @Override
    public int getCount() {
        return roomsArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    class ViewHolder {
        TextView txtRoomName, txtRoomTenants,txtShowRoomPrice;
        ImageView imgUpdateRoom, imgDeleteRoom,imgShowRoomNote;
        LinearLayout llRooms;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null)
        {
            holder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater)   context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout,null);

            holder.txtRoomName  = (TextView) convertView.findViewById(R.id.txtRoomName);
            holder.txtRoomTenants = (TextView) convertView.findViewById(R.id.txtRoomTenants);
            holder.imgUpdateRoom    = (ImageView) convertView.findViewById(R.id.imgUpdateRoom);
            holder.imgDeleteRoom    = (ImageView) convertView.findViewById(R.id.imgDeleteRoom);
            holder.txtShowRoomPrice = (TextView) convertView.findViewById(R.id.txtShowRoomPrice);
            holder.llRooms          = (LinearLayout) convertView.findViewById(R.id.llRooms);
            holder.imgShowRoomNote  = (ImageView) convertView.findViewById(R.id.imgShowRoomNote);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        Rooms rooms = roomsArrayList.get(position);

        holder.txtRoomName.setText(rooms.getRoomsName());
        holder.txtShowRoomPrice.setText(rooms.getRoomPrice());




        Cursor cursor = MainActivity.database.GetData(
                "SELECT tenantsId FROM Tenants WHERE roomId = '" + rooms.getId() + "' ");
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
        holder.txtRoomTenants.setText(String.valueOf(count));

        holder.imgShowRoomNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.showNote(rooms.getRoomsNote());
            }
        });
        holder.llRooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.goToTenants(rooms.getId(),rooms.getRoomsName());
            }
        });

        holder.imgDeleteRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.dialogDeleteRoom(rooms.getId());
            }
        });

        holder.imgUpdateRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.dialogUpdateRoom(rooms.getId(),rooms.getRoomPrice(),
                        rooms.getRoomsNote(),rooms.getRoomsName());
            }
        });

        return convertView;
    }
}
