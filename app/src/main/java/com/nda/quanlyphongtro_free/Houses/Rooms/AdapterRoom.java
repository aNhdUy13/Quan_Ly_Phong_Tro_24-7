package com.nda.quanlyphongtro_free.Houses.Rooms;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nda.quanlyphongtro_free.Model.Rooms;
import com.nda.quanlyphongtro_free.R;

import java.util.List;

public class AdapterRoom extends RecyclerView.Adapter<AdapterRoom.HolderRooms> {
    RoomsSystem context;
    List<Rooms> roomsList;

    public AdapterRoom(RoomsSystem context, List<Rooms> roomsList) {
        this.context = context;
        this.roomsList = roomsList;
    }

    @NonNull
    @Override
    public HolderRooms onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout_rooms, parent, false);
        return new AdapterRoom.HolderRooms(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderRooms holder, int position) {

    }

    @Override
    public int getItemCount() {
        return roomsList.size();
    }

    public class HolderRooms extends RecyclerView.ViewHolder {
        public HolderRooms(@NonNull View itemView) {
            super(itemView);
        }
    }
}
