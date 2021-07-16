package com.nda.quanlyphongtro_free.Setting;

import android.app.Dialog;
import android.app.SearchManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.nda.quanlyphongtro_free.R;
import com.nda.quanlyphongtro_free.Setting.Billing.Billing;


public class Setting extends AppCompatActivity {
    ImageView imgBack;
    CardView cvShareApp,cvBilling,cvTimetable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        mapting();
        initiate();
    }

    private void initiate() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        shareApp();
        cvTimetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogTimetable();
            }
        });

        cvBilling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Setting.this, Billing.class));

            }
        });


    }
    private void dialogTimetable() {
        Dialog dialog = new Dialog((Setting.this));
        dialog.setContentView(R.layout.dialog_goto_timetable);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView btn_copyLink             = (ImageView) dialog.findViewById(R.id.copy_Link_text);
        Button btn_searchLink_openApp      = (Button) dialog.findViewById(R.id.searchLink_openapp);
        TextView txtResult_link            = (TextView) dialog.findViewById(R.id.txtLink);
        TextView txtTitle_luyenTap         = (TextView) dialog.findViewById(R.id.title_luyenTap);
        LinearLayout ll_link               = (LinearLayout) dialog.findViewById(R.id.ll_link_luyenNao);

        btn_copyLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("ImgView",txtResult_link.getText().toString());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(getApplicationContext(),"Link Copied",Toast.LENGTH_LONG).show();
            }
        });

        String packageName = "https://play.google.com/store/apps/details?id=com.nda.timetable";
        if (isPackageAvailable(packageName))
        {
            ll_link.setVisibility(View.INVISIBLE);
            txtTitle_luyenTap.setText("Thời Khóa Biểu - THời Gian Biểu\n(ĐÃ TẢI)");
            btn_searchLink_openApp.setText("Mở Ứng Dụng");
            btn_searchLink_openApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentOpen = getPackageManager().getLaunchIntentForPackage(packageName);
                    startActivity(intentOpen);
                }
            });
        }
        else
        {
            btn_searchLink_openApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentSearch = new Intent(Intent.ACTION_WEB_SEARCH);
                    String term = txtResult_link.getText().toString();
                    intentSearch.putExtra(SearchManager.QUERY, term);
                    startActivity(intentSearch);
                }
            });
        }
        dialog.show();

    }
    private boolean isPackageAvailable(String packageName)
    {
        boolean available = true;
        try{
            getPackageManager().getPackageInfo(packageName,0);
        }catch(PackageManager.NameNotFoundException e)
        {
            available = false;
        }
        return available;
    }
    private void shareApp() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        String shareBody = "https://play.google.com/store/apps/details?id=com.nda.quanlyphongtro_free";
        /*The type of the content is text, obviously.*/
        intent.setType("text/plain");
        /*Applying information Subject and Body.*/
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share_subject));
        intent.putExtra(Intent.EXTRA_TEXT, shareBody);
        cvShareApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(Intent.createChooser(intent, getString(R.string.share_using)));

//                    startActivity(sendIntent);
                } catch (Exception e)
                { e.printStackTrace();}

            }
        });
    }
    private void mapting() {
        imgBack = (ImageView) findViewById(R.id.imgBack);
        cvShareApp  = (CardView) findViewById(R.id.cvShareApp);
        cvBilling   = (CardView) findViewById(R.id.cvBilling);
        cvTimetable = (CardView) findViewById(R.id.cvTimetable);
    }
}