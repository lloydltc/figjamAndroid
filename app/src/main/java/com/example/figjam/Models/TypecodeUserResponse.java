package com.example.figjam.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TypecodeUserResponse {
    @SerializedName("typecodeUsersList")
    @Expose
    private List<TypecodeUsers> typecodeUsersList = null;

    public List<TypecodeUsers> getTypecodeUsersList() {
        return typecodeUsersList;
    }

    public void setTypecodeUsersList(List<TypecodeUsers> typecodeUsersList) {
        this.typecodeUsersList = typecodeUsersList;
    }
}
