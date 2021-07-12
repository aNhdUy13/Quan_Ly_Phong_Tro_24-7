package com.nda.quanlyphongtro_free.Contract;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nda.quanlyphongtro_free.MainActivity;
import com.nda.quanlyphongtro_free.R;
import com.startapp.sdk.adsbase.StartAppAd;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class ContractSystem extends AppCompatActivity {
    int REQUEST_CODE_CAMERA = 2;
    int REQUEST_CODE_FOLDER = 1;
    ImageView imgBack,imgAddContractFromLibrary,imgAddContractFromCamera, imgGetContractIMG;
    Button btnAddImg,btnCancel;
    ArrayList<Contract> contractArrayList;
    ContractAdapter contractAdapter;
    ListView lvContract;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract_system);
        mapting();
        setUpListView();
        initiate();
    }

    private void setUpListView() {
        contractArrayList = new ArrayList<>();
        contractAdapter = new ContractAdapter(this,R.layout.item_layout_contract,contractArrayList);
        lvContract.setAdapter(contractAdapter);

        displayContract();

    }

    private void displayContract() {
        Cursor cursor = MainActivity.database.GetData(
                "SELECT * FROM Contracts");
        contractArrayList.clear();
        while (cursor.moveToNext())
        {
            int id = cursor.getInt(0);
            byte[] img = cursor.getBlob(1);


            contractArrayList.add(new Contract(id,img));
        }
        contractAdapter.notifyDataSetChanged();
    }

    public void dialogDeleteContract(int id)
    {
        Dialog dialog_delete = new Dialog(ContractSystem.this);
        dialog_delete.setContentView(R.layout.dialog_detele);
        dialog_delete.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button btnYes               = (Button) dialog_delete.findViewById(R.id.btn_allow_delete);
        Button btnNo                = (Button) dialog_delete.findViewById(R.id.btn_cancel_delete);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.database.QueryDate("DELETE FROM Contracts WHERE contractId = '" + id + "'");

                Toast.makeText(ContractSystem.this,"Delete Successful ! ",Toast.LENGTH_SHORT).show();
                displayContract();
                dialog_delete.dismiss();
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_delete.dismiss();
            }
        });
        dialog_delete.show();

    }
    private void initiate() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                StartAppAd.showAd(getApplicationContext());

            }
        });
        imgAddContractFromLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_FOLDER);
            }
        });
        imgAddContractFromCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CODE_CAMERA);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data!=null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                dialogGetIMG(bitmap);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(ContractSystem.this,"Not Found", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data!=null){
            Bitmap captureBitmap = (Bitmap) data.getExtras().get("data");
            dialogGetIMG(captureBitmap);


        }
    }
    public void dialogGetIMG(Bitmap bitmap)
    {
        Dialog dialogGetIMG = new Dialog(ContractSystem.this);
        dialogGetIMG.setContentView(R.layout.dialog_contract_image);
        dialogGetIMG.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        imgGetContractIMG   = (ImageView) dialogGetIMG.findViewById(R.id.imgGetContractIMG);
        btnAddImg           = (Button) dialogGetIMG.findViewById(R.id.btnAddImg);
        btnCancel           = (Button) dialogGetIMG.findViewById(R.id.btnCancel);
        imgGetContractIMG.setImageBitmap(bitmap);

        BitmapDrawable bitmapDrawable = (BitmapDrawable) imgGetContractIMG.getDrawable();

        Bitmap bitmapConverted = bitmapDrawable.getBitmap();
        Bitmap resized = Bitmap.createScaledBitmap(bitmapConverted,792,960,true);
        //int size = bitmap.getWidth()*bitmap.getHeight()*10;
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();

        resized.compress(Bitmap.CompressFormat.JPEG, 100,byteArray);

        byte[] hinhAnh = byteArray.toByteArray();

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogGetIMG.dismiss();
            }
        });

        btnAddImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.database.INSERT_CONTRACT(hinhAnh);
                displayContract();
                dialogGetIMG.dismiss();
                StartAppAd.showAd(getApplicationContext());

            }
        });


        dialogGetIMG.show();

    }
    private void mapting() {
        imgBack         = (ImageView) findViewById(R.id.imgBack);
        lvContract      = (ListView) findViewById(R.id.lvContract);
        imgAddContractFromLibrary  = (ImageView) findViewById(R.id.imgAddContractFromLibrary);
        imgAddContractFromCamera   = (ImageView) findViewById(R.id.imgAddContractFromCamera);
    }


}