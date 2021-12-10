package com.example.figjam.Views.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.figjam.Models.TypecodeUsers;
import com.example.figjam.Models.TypecodeUsersModel;
import com.example.figjam.R;
import com.example.figjam.ViewModel.TypecodeUsersViewModel;
import com.example.figjam.Views.Adapters.LoadFromDatabaseAdapter;
import com.example.figjam.Views.Adapters.TypecodeAdapter;
import com.example.figjam.Views.Interfaces.AdpterOnclick;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class LoadFromDatabaseActivity extends AppCompatActivity implements AdpterOnclick {
    ArrayList<TypecodeUsersModel> typecodeUserModelArrayList = new ArrayList<>();
    TypecodeUsersViewModel typecodeUsersViewModel;
    TypecodeUsersModel typecodeUsersModel;
    LoadFromDatabaseAdapter loadFromDatabaseAdapter;
    RecyclerView loadFromDbRecyclerview;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_from_database);
        loadFromDbRecyclerview = findViewById(R.id.recleview_load_from_db);
        fab = findViewById(R.id.floating_action_button);

        typecodeUsersModel= new TypecodeUsersModel();
        typecodeUsersViewModel = ViewModelProviders.of(this).get(TypecodeUsersViewModel.class);
        typecodeUsersViewModel.init();
        typecodeUsersViewModel.getData().observe(this, typecodeUsersModelList -> {

            typecodeUserModelArrayList.addAll(typecodeUsersModelList);
            loadFromDatabaseAdapter.notifyDataSetChanged();

        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoadFromDatabaseActivity.this,CreateTypeCodeUser.class));
            }
        });
        setupRecyclerView();
    }
    void setupRecyclerView() {
        if (loadFromDatabaseAdapter==null){
            loadFromDatabaseAdapter = new LoadFromDatabaseAdapter(LoadFromDatabaseActivity.this,typecodeUserModelArrayList);
            loadFromDbRecyclerview.setLayoutManager(new LinearLayoutManager(this));
            loadFromDbRecyclerview.setAdapter(loadFromDatabaseAdapter);
            loadFromDbRecyclerview.setItemAnimator(new DefaultItemAnimator());
            loadFromDbRecyclerview.setNestedScrollingEnabled(true);


        }else{
            loadFromDatabaseAdapter.notifyDataSetChanged();
        }
    }
    @Override
    public void viewDetails(int pos) {
        TypecodeUsersModel typecodeUsersModel = new TypecodeUsersModel();
        typecodeUsersModel = typecodeUserModelArrayList.get(pos);
        Intent intent = new Intent(this,DetailsActivity.class);
        intent.putExtra("userModel",typecodeUsersModel );
        startActivity(intent);
    }

    @Override
    public void update(int pos) {
        TypecodeUsersModel typecodeUsersModel = new TypecodeUsersModel();
        typecodeUsersModel = typecodeUserModelArrayList.get(pos);
        Intent intent = new Intent(this,UpdateRecord.class);
        intent.putExtra("userModel",typecodeUsersModel );
        startActivity(intent);
    }

    @Override
    public void delete(int pos) {
        TypecodeUsersModel typecodeUsersModel = new TypecodeUsersModel();
        typecodeUsersModel= typecodeUserModelArrayList.get(pos);
        typecodeUsersViewModel.delete(typecodeUsersModel);

    }

}