package com.nda.quanlyphongtro_free.Tenants;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.nda.quanlyphongtro_free.R;

import java.util.ArrayList;

public class AllTenantsAdapter extends BaseAdapter {
    private static final int REQUEST_CALL   = 1;

    private AllTenantsSystem context;
    private int layout;
    private ArrayList<AllTenants> tenantsArrayList;

    public AllTenantsAdapter(AllTenantsSystem context, int layout, ArrayList<AllTenants> tenantsArrayList) {
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
        ImageView imgUpdateTenant,imgDeleteTenant,imgShowNote,imgCall;
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
            holder.imgCall              = (ImageView) convertView.findViewById(R.id.imgCall);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        AllTenants tenants = tenantsArrayList.get(position);

        holder.imgCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = tenants.getPhonenumber();
                if (phoneNumber.isEmpty())
                {
                    Toast.makeText(context, "Không Tìm Thấy Số Điện Thoại !", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE)
                            != PackageManager.PERMISSION_GRANTED)
                    {
                        ActivityCompat.requestPermissions(context,
                                new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);


                    }
                    else
                    {
                        String s = "tel:" + phoneNumber;
                        context.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(s)));

                    }

                }

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + "phone_number"));

//                if (Build.VERSION.SDK_INT > 23) {
//                    startActivity(intent);
//                } else {
//
//                    if (ActivityCompat.checkSelfPermission(PurchaseItemDetails.this,
//                            Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                        Toast.makeText(YourActivity.this, "Permission Not Granted ", Toast.LENGTH_SHORT).show();
//                    } else {
//                        final String[] PERMISSIONS_STORAGE = {Manifest.permission.CALL_PHONE};
//                        ActivityCompat.requestPermissions(YourActivity.this, PERMISSIONS_STORAGE, 9);
//                        startActivity(intent);
//                    }
//                }
            }
        });


        holder.txtTenantName.setText(tenants.getName());
        holder.txtTenantPhonenumber.setText(tenants.getPhonenumber());

        holder.imgShowNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.dialogShowNote(tenants.getNote());
            }
        });
        holder.imgDeleteTenant.setVisibility(View.INVISIBLE);
        holder.imgUpdateTenant.setVisibility(View.INVISIBLE);

        return convertView;
    }
}
