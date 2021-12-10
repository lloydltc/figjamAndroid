package com.example.figjam.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.figjam.Models.TypecodeUsersModel;

import java.util.List;


@Dao
public interface TypecodeDAO {
    @Insert
    void insert(TypecodeUsersModel typecodeUsersModel);
    @Update
    void update(TypecodeUsersModel typecodeUsersModel);
    //Deletion methods must either return void or return int (the number of deleted rows).
    @Delete
    void   delete(TypecodeUsersModel typecodeUsersModel);
    @Query("select * from typecodeTB")
    LiveData<List<TypecodeUsersModel>> selectAll();
    //NB= Deletion methods must either return void or return int (the number of deleted
    // rows).
    @Query("delete from typecodeTB")
    int deleteAll();
}
