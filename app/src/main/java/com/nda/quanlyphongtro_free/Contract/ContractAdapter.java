package com.nda.quanlyphongtro_free.Contract;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


import com.nda.quanlyphongtro_free.R;

import java.util.ArrayList;

public class ContractAdapter extends BaseAdapter {
    private ContractSystem context;
    private int layout;
    private ArrayList<Contract> contractArrayList;

    public ContractAdapter(ContractSystem context, int layout, ArrayList<Contract> contractArrayList) {
        this.context = context;
        this.layout = layout;
        this.contractArrayList = contractArrayList;
    }

    @Override
    public int getCount() {
        return contractArrayList.size();
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
        ImageView  imgDeleteContract, imgContract;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null)
        {
            holder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater)   context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout,null);

            holder.imgDeleteContract      = (ImageView) convertView.findViewById(R.id.imgDeleteContract);
            holder.imgContract          = (ImageView) convertView.findViewById(R.id.imgContract);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        Contract contract = contractArrayList.get(position);

        byte[] getImg = contract.getImgContract();
        Bitmap bitmap = BitmapFactory.decodeByteArray(getImg,0,getImg.length);
        holder.imgContract.setImageBitmap(bitmap);

        holder.imgDeleteContract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.dialogDeleteContract(contract.getContractID());
            }
        });
        return convertView;
    }
}
