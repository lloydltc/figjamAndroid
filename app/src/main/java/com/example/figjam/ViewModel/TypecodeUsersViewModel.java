package com.example.figjam.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.figjam.Models.TypecodeUsers;
import com.example.figjam.Models.TypecodeUsersModel;
import com.example.figjam.Repository.TypecodeRepository;
import com.example.figjam.Repository.TypecodeRoomRepository;

import java.util.List;

public class TypecodeUsersViewModel extends AndroidViewModel {
    private MutableLiveData<List<TypecodeUsers>>  mutableLiveData;
    private LiveData<List<TypecodeUsersModel>> getAllData;
    private final TypecodeRoomRepository typecodeRoomRepository;

    public void init(){
        if (mutableLiveData !=null){
            return;
        }
        TypecodeRepository typecodeRepository = TypecodeRepository.getInstance();
        mutableLiveData = typecodeRepository.getTypecodeUsers();
    }
    public LiveData<List<TypecodeUsers>> getTypecodeRepository(){
        return mutableLiveData;
    }


    public TypecodeUsersViewModel(@NonNull Application application){
        super(application);

        typecodeRoomRepository = new TypecodeRoomRepository(application);
        getAllData = typecodeRoomRepository.getAllData();
    }
    public LiveData<List<TypecodeUsersModel>>getData(){
        return getAllData;}
    public void delete(TypecodeUsersModel data){
        typecodeRoomRepository.delete(data);
    }
    public void update(TypecodeUsersModel data){
        typecodeRoomRepository.update(data);
    }
    public void insert(TypecodeUsersModel data){
        typecodeRoomRepository.insertData(data);

    }
}
