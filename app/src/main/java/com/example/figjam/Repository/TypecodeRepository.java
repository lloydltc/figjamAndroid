package com.example.figjam.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;

import com.example.figjam.Database.FigJamDatabase;
import com.example.figjam.Database.TypecodeDAO;
import com.example.figjam.Models.TypecodeUserResponse;
import com.example.figjam.Models.TypecodeUsers;
import com.example.figjam.Models.TypecodeUsersModel;
import com.example.figjam.Network.ApiInterface;
import com.example.figjam.Network.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TypecodeRepository {
    private  TypecodeDAO typecodeDAO;
    private static TypecodeRepository typecodeRepository;



    public static TypecodeRepository getInstance(){
        if (typecodeRepository== null){
           typecodeRepository = new TypecodeRepository();

        }
        return typecodeRepository;
    }
    private ApiInterface apiInterface;


    public TypecodeRepository() {
        apiInterface = RetrofitInstance.cteateService(ApiInterface.class);
    }


    public MutableLiveData<List<TypecodeUsers>> getTypecodeUsers(){MutableLiveData<List<TypecodeUsers>>typecodeUserData = new MutableLiveData<>();
       apiInterface.getTypecodeUsers().enqueue(new Callback<List<TypecodeUsers>>() {
            @Override
            public void onResponse(Call<List<TypecodeUsers>> call, Response<List<TypecodeUsers>> response) {
                if (response.isSuccessful()){
                    typecodeUserData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<TypecodeUsers>> call, Throwable t) {

//           weatherData.setValue(null);

            }
        });
        return typecodeUserData;
    }

}
