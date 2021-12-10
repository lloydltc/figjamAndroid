package com.example.figjam.Models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "TypecodeTB")
public class TypecodeUsersModel implements Serializable {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    @Expose
    private String id;
    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    private String name;
    @ColumnInfo(name = "username")
    @SerializedName("username")
    @Expose
    private String username;
    @ColumnInfo(name = "email")
    @SerializedName("email")
    @Expose
    private String email;
    @ColumnInfo(name = "phone")
    @SerializedName("phone")
    @Expose
    private String phone;
    @ColumnInfo(name = "website")
    @SerializedName("website")
    @Expose
    private String website;
    @ColumnInfo(name = "lat")
    @SerializedName("lat")
    @Expose
    private String lat;
    @ColumnInfo(name = "long")
    @SerializedName("lng")
    @Expose
    private String lng;
    @ColumnInfo(name = "companyName")
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @ColumnInfo(name = "catchPhrase")
    @SerializedName("catchPhrase")
    @Expose
    private String catchPhrase;
    @ColumnInfo(name = "bs")
    @SerializedName("bs")
    @Expose
    private String bs;
    @ColumnInfo(name = "street")
    @SerializedName("street")
    @Expose
    private String street;
    @ColumnInfo(name = "suite")
    @SerializedName("suite")
    @Expose
    private String suite;
    @ColumnInfo(name = "city")
    @SerializedName("city")
    @Expose
    private String city;
    @ColumnInfo(name = "zipcode0")
    @SerializedName("zipcode")
    @Expose
    private String zipcode;

    public TypecodeUsersModel(){

    }

    public TypecodeUsersModel(String id, String name, String username, String email, String phone, String website, String lat, String lng, String companyName, String catchPhrase, String bs, String street, String suite, String city, String zipcode) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.website = website;
        this.lat = lat;
        this.lng = lng;
        this.companyName = companyName;
        this.catchPhrase = catchPhrase;
        this.bs = bs;
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
