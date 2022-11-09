package com.nda.quanlyphongtro_free.Houses.Rooms;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
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
        Rooms rooms = roomsList.get(position);

        holder.txt_roomName.setText(rooms.getrName());
        holder.txt_roomMembers.setText("Số người : 0/" + rooms.getrLimitTenants());
        holder.txt_roomFloor.setText("Tầng : " + rooms.getrFloorNumber());
        holder.txt_roomFee.setText("Giá Phòng : " + rooms.getrPrice() + " đ");

        holder.cv_roomItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return roomsList.size();
    }

    public class HolderRooms extends RecyclerView.ViewHolder {
        CardView cv_roomItem;
        TextView txt_roomName, txt_roomMembers, txt_roomFloor, txt_roomFee;
        public HolderRooms(@NonNull View itemView) {
            super(itemView);

            txt_roomName       = itemView.findViewById(R.id.txt_roomName);
            txt_roomMembers    = itemView.findViewById(R.id.txt_roomMembers);
            txt_roomFloor      = itemView.findViewById(R.id.txt_roomFloor);
            txt_roomFee        = itemView.findViewById(R.id.txt_roomFee);

            cv_roomItem        = itemView.findViewById(R.id.cv_roomItem);

        }
    }
}
