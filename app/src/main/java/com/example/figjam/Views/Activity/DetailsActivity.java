package com.example.figjam.Views.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.figjam.Models.TypecodeUsersModel;
import com.example.figjam.R;

public class DetailsActivity extends AppCompatActivity {
    TextView txtName, txtUsername, txtEmail, txtPhone,txtWebsite,
            txtlat,txtlng,txtCompanyName,txtCatchPhrase,txtBs, txtStreet,txtCity,txtZipcode;

    TypecodeUsersModel typecodeUsersModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();
        typecodeUsersModel  = (TypecodeUsersModel)intent.getSerializableExtra("userModel");

        txtBs = findViewById(R.id.txtvbs);
        txtCity =findViewById(R.id.txtvcity);
        txtName = findViewById(R.id.txtvname);
        txtUsername = findViewById(R.id.txtvusername);
        txtEmail = findViewById(R.id.txtvemail);
        txtPhone = findViewById(R.id.txtvphone);
        txtWebsite = findViewById(R.id.txtvweb);
        txtlat = findViewById(R.id.txtvlat);
        txtlng = findViewById(R.id.txtvlng);
        txtCompanyName = findViewById(R.id.txtvcompany_name);
        txtCatchPhrase = findViewById(R.id.txtvcatch_prase);
        txtStreet = findViewById(R.id.txtvstreet);
        txtZipcode = findViewById(R.id.txtvzipcode);

        txtBs.setText(typecodeUsersModel.getBs());
        txtlat.setText(typecodeUsersModel.getLat());
        txtlng.setText(typecodeUsersModel.getLng());
        txtCatchPhrase.setText(typecodeUsersModel.getCatchPhrase());
        txtEmail.setText(typecodeUsersModel.getEmail());
        txtCity.setText(typecodeUsersModel.getCity());
        txtCompanyName.setText(typecodeUsersModel.getCompanyName());
        txtName.setText(typecodeUsersModel.getName());
        txtPhone.setText(typecodeUsersModel.getPhone());
        txtStreet.setText(typecodeUsersModel.getStreet());
        txtUsername.setText(typecodeUsersModel.getUsername());
        txtWebsite.setText(typecodeUsersModel.getWebsite());
        txtZipcode.setText(typecodeUsersModel.getZipcode());
    }
}