package com.example.figjam.Network;

import com.example.figjam.Models.TypecodeUserResponse;
import com.example.figjam.Models.TypecodeUsers;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("users")
    Call<List<TypecodeUsers>> getTypecodeUsers();
}
