package com.example.figjam.Views.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.figjam.Models.TypecodeUsersModel;
import com.example.figjam.R;
import com.example.figjam.ViewModel.TypecodeUsersViewModel;

public class CreateTypeCodeUser extends AppCompatActivity {
    TextView txtName, txtUsername, txtEmail, txtPhone,txtWebsite,
            txtlat,txtlng,txtCompanyName,txtCatchPhrase,txtBs, txtStreet,txtCity,txtZipcode;
    Button btnSubmit;
    TypecodeUsersViewModel typecodeUsersViewModel;
    TypecodeUsersModel typecodeUsersModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_type_code_user);
        txtBs = findViewById(R.id.txtbs);
        txtName = findViewById(R.id.txtname);
        txtUsername = findViewById(R.id.txtusername);
        txtEmail = findViewById(R.id.txtemail);
        txtPhone = findViewById(R.id.txtphone);
        txtWebsite = findViewById(R.id.txtweb);
        txtCity =findViewById(R.id.txtcity);
        txtlat = findViewById(R.id.txtlat);
        txtlng = findViewById(R.id.txtlng);
        txtCompanyName = findViewById(R.id.txtcompany_name);
        txtCatchPhrase = findViewById(R.id.txtcatch_prase);
        txtStreet = findViewById(R.id.txtstreet);
        txtZipcode = findViewById(R.id.txtzipcode);
        btnSubmit = findViewById(R.id.submit);
        typecodeUsersModel= new TypecodeUsersModel();
        typecodeUsersViewModel = ViewModelProviders.of(this).get(TypecodeUsersViewModel.class);
        typecodeUsersViewModel.init();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = (String.valueOf(System.currentTimeMillis()));
                String name = txtName.getText().toString();
                String username = txtUsername.getText().toString();
                String email = txtEmail.getText().toString();
                String bs = txtBs.getText().toString();
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
                typecodeUsersModel.setBs(bs);
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
                typecodeUsersViewModel.insert(typecodeUsersModel);
                Toast.makeText(CreateTypeCodeUser.this, "ypecodeUsersModel.getEmail()", Toast.LENGTH_LONG).show();
            }

        });


    }
}