package com.example.figjam.Views.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.figjam.Models.TypecodeUsers;

import com.example.figjam.Models.TypecodeUsersModel;
import com.example.figjam.R;
import com.example.figjam.ViewModel.TypecodeUsersViewModel;
import com.example.figjam.Views.Adapters.TypecodeAdapter;
import com.example.figjam.Views.Interfaces.AdpterOnclick;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdpterOnclick {
    ArrayList<TypecodeUsers> typecodeUsersArrayList = new ArrayList<>();
    TypecodeUsersViewModel typecodeUsersViewModel;
    TypecodeUsersModel typecodeUsersModel;
    TypecodeAdapter typecodeAdapter;
    String mUID;
    private FirebaseAuth mAuth;
    RecyclerView typecodeuserRecyclerview;

    FloatingActionButton fab;
    protected void onStart() {
        super.onStart();
        checkUser();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null){
            Intent move=new Intent(MainActivity.this,RegisterActivity.class );
            startActivity(move);

        }
//        else {
//            startActivity(new Intent(MainActivity.this, FingerPrintActivity.class));
//        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        typecodeuserRecyclerview = findViewById(R.id.recleview);
        fab = findViewById(R.id.floating_action_button);
        mAuth = FirebaseAuth.getInstance();
        typecodeUsersModel= new TypecodeUsersModel();
        typecodeUsersViewModel = ViewModelProviders.of(this).get(TypecodeUsersViewModel.class);
        typecodeUsersViewModel.init();
        typecodeUsersViewModel.getTypecodeRepository().observe(this, typecodeUserResponse -> {

            typecodeUsersArrayList.addAll(typecodeUserResponse);
            typecodeAdapter.notifyDataSetChanged();

        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CreateTypeCodeUser.class));
            }
        });
        setupRecyclerView();


    }

     void setupRecyclerView() {
        if (typecodeAdapter==null){
            typecodeAdapter = new TypecodeAdapter(MainActivity.this,typecodeUsersArrayList);
            typecodeuserRecyclerview.setLayoutManager(new LinearLayoutManager(this));
            typecodeuserRecyclerview.setAdapter(typecodeAdapter);
            typecodeuserRecyclerview.setItemAnimator(new DefaultItemAnimator());
            typecodeuserRecyclerview.setNestedScrollingEnabled(true);


        }else{
            typecodeAdapter.notifyDataSetChanged();
        }
    }
public void saveToRoom() {
        for (int i = 0; i <typecodeUsersArrayList.size(); i++) {
//    int i = 0;
            String id = (String.valueOf(System.currentTimeMillis()));
            String name = typecodeUsersArrayList.get(i).getName();
            String username = typecodeUsersArrayList.get(i).getUsername();
            String email = typecodeUsersArrayList.get(i).getEmail();
            String phone = typecodeUsersArrayList.get(i).getPhone();
            String website = typecodeUsersArrayList.get(i).getWebsite();
            String lat = typecodeUsersArrayList.get(i).getAddress().getGeo().getLat();
            String lng = typecodeUsersArrayList.get(i).getAddress().getGeo().getLng();
            String companyName = typecodeUsersArrayList.get(i).getCompany().getName();
            String catchPhrase = typecodeUsersArrayList.get(i).getCompany().getCatchPhrase();
            String street = typecodeUsersArrayList.get(i).getAddress().getStreet();
            String suite = typecodeUsersArrayList.get(i).getAddress().getSuite();
            String city = typecodeUsersArrayList.get(i).getAddress().getCity();
            String zipcode = typecodeUsersArrayList.get(i).getAddress().getZipcode();

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
            typecodeUsersViewModel.insert(typecodeUsersModel);

        }
    Toast.makeText(MainActivity.this,"Data successfully saved", Toast.LENGTH_LONG).show();

}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.load_db:
               startActivity(new Intent(MainActivity.this, LoadFromDatabaseActivity.class));
                return true;
            case R.id.sync_db:
                saveToRoom();
                startActivity(new Intent(MainActivity.this, LoadFromDatabaseActivity.class));

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void viewDetails(int i) {
        String id = (String.valueOf(System.currentTimeMillis()));
        String name = typecodeUsersArrayList.get(i).getName();
        String username = typecodeUsersArrayList.get(i).getUsername();
        String email = typecodeUsersArrayList.get(i).getEmail();
        String phone = typecodeUsersArrayList.get(i).getPhone();
        String website = typecodeUsersArrayList.get(i).getWebsite();
        String lat = typecodeUsersArrayList.get(i).getAddress().getGeo().getLat();
        String lng = typecodeUsersArrayList.get(i).getAddress().getGeo().getLng();
        String companyName = typecodeUsersArrayList.get(i).getCompany().getName();
        String catchPhrase = typecodeUsersArrayList.get(i).getCompany().getCatchPhrase();
        String street = typecodeUsersArrayList.get(i).getAddress().getStreet();
        String suite = typecodeUsersArrayList.get(i).getAddress().getSuite();
        String city = typecodeUsersArrayList.get(i).getAddress().getCity();
        String zipcode = typecodeUsersArrayList.get(i).getAddress().getZipcode();

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
        Intent intent = new Intent(this,DetailsActivity.class);
        intent.putExtra("userModel",typecodeUsersModel );
        startActivity(intent);
    }

    @Override
    public void update(int pos) {

    }

    @Override
    public void delete(int pos) {

    }
    private void checkUser(){
        String usId = mAuth.getUid();
        if(usId != null){
            mUID = usId;
            SharedPreferences sp = getSharedPreferences("SP_USER", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("Current_USERID", mUID);
            editor.apply();
        }
    }
}

