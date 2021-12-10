package com.example.figjam.Views.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.figjam.Models.TypecodeUsersModel;
import com.example.figjam.R;
import com.example.figjam.ViewModel.TypecodeUsersViewModel;

public class UpdateRecord extends AppCompatActivity {
    TextView txtName, txtUsername, txtEmail, txtPhone,txtWebsite,
            txtlat,txtlng,txtCompanyName,txtCatchPhrase,txtBs, txtStreet,txtCity,txtZipcode;
    Button btnSubmit;
    TypecodeUsersModel typecodeUsersModel;
    TypecodeUsersViewModel typecodeUsersViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_record);
        txtBs = findViewById(R.id.txtbs);
        txtCity =findViewById(R.id.txtcity);
        txtName = findViewById(R.id.txtname);
        txtUsername = findViewById(R.id.txtusername);
        txtEmail = findViewById(R.id.txtemail);
        txtPhone = findViewById(R.id.txtphone);
        txtWebsite = findViewById(R.id.txtweb);
        txtlat = findViewById(R.id.txtlat);
        txtlng = findViewById(R.id.txtlng);
        txtCompanyName = findViewById(R.id.txtcompany_name);
        txtCatchPhrase = findViewById(R.id.txtcatch_prase);
        txtStreet = findViewById(R.id.txtstreet);
        txtZipcode = findViewById(R.id.txtzipcode);
        btnSubmit = findViewById(R.id.submit);
        Intent intent = getIntent();
         typecodeUsersModel  = (TypecodeUsersModel)intent.getSerializableExtra("userModel");
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
        String updateId = typecodeUsersModel.getId();
        typecodeUsersViewModel = ViewModelProviders.of(this).get(TypecodeUsersViewModel.class);
        typecodeUsersViewModel.init();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = (updateId);
                String name = txtName.getText().toString();
                String username = txtUsername.getText().toString();;
                String email = txtEmail.getText().toString();
                String phone =txtPhone.getText().toString();
                String website = txtWebsite.getText().toString();
                String lat = txtlat.getText().toString();
                String lng = txtlng.getText().toString();
                String companyName = txtCompanyName.getText().toString();
                String catchPhrase = txtCatchPhrase.getText().toString();
                String street = txtStreet.getText().toString();
                String suite = txtBs.getText().toString();
                String city = txtCity.getText().toString();
                String zipcode = txtZipcode.getText().toString();

                typecodeUsersModel = new TypecodeUsersModel();
                typecodeUsersModel.setId(id);
                typecodeUsersModel.setName(name);
                typecodeUsersModel.setUsername(username);
                typecodeUsersModel.setEmail(email);
                typecodeUsersModel.setPhone(phone);
                typecodeUsersModel.setWebsite(website);
                typecodeUsersModel.setLat(lat);
                typecodeUsersModel.setLng(lng);
                typecodeUsersModel.setCompanyName(companyName);
                typecodeUsersModel.setCatchPhrase(catchPhrase);
                typecodeUsersModel.setStreet(street);
                typecodeUsersModel.setSuite(suite);
                typecodeUsersModel.setCity(city);
                typecodeUsersModel.setZipcode(zipcode);
                typecodeUsersViewModel.update(typecodeUsersModel);
                Toast.makeText(UpdateRecord.this, "ypecodeUsersModel.getEmail()", Toast.LENGTH_LONG).show();

            }
        });

    }
}