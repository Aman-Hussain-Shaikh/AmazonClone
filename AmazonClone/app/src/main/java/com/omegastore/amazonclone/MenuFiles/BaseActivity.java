package com.omegastore.amazonclone.MenuFiles;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import com.omegastore.amazonclone.HomeActivity;
import com.omegastore.amazonclone.MenuFiles.AddProduct;
import com.omegastore.amazonclone.MenuFiles.CartActivity;
import com.omegastore.amazonclone.MenuFiles.ProfileActivity;
import com.omegastore.amazonclone.MenuFiles.SearchActivity;
import com.omegastore.amazonclone.R;

public class BaseActivity extends AppCompatActivity {

    RadioGroup radioGroup1;
    RadioButton home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        radioGroup1=(RadioGroup)findViewById(R.id.radioGroup1);
        home = (RadioButton)findViewById(R.id.bottom_home);
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                Intent in;
                if (checkedId == R.id.bottom_home) {
                    in = new Intent(getBaseContext(), HomeActivity.class);
                    startActivity(in);
                    overridePendingTransition(0, 0);
                } else if (checkedId == R.id.bottom_addprod) {
                    in = new Intent(getBaseContext(), AddProduct.class);
                    startActivity(in);
                    overridePendingTransition(0, 0);
                } else if (checkedId == R.id.bottom_search) {
                    in = new Intent(getBaseContext(), SearchActivity.class);
                    startActivity(in);
                    overridePendingTransition(0, 0);
                } else if (checkedId == R.id.bottom_cart) {
                    in = new Intent(getBaseContext(), CartActivity.class);
                    startActivity(in);
                    overridePendingTransition(0, 0);
                } else if (checkedId == R.id.bottom_profile) {
                    in = new Intent(getBaseContext(), ProfileActivity.class);
                    startActivity(in);
                    overridePendingTransition(0, 0);
                }
            }
        });
    }
}