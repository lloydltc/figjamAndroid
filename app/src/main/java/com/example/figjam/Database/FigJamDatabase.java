package com.example.figjam.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.figjam.Models.TypecodeUsersModel;

@Database(entities = {TypecodeUsersModel.class}, version = 1, exportSchema = false)
public abstract class FigJamDatabase extends RoomDatabase{
    public abstract TypecodeDAO typecodeDAO();
    private static FigJamDatabase INSTANCE;

    public static FigJamDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (FigJamDatabase.class){
                if(INSTANCE ==null){
                    INSTANCE = Room.databaseBuilder(
                            context, FigJamDatabase.class,"FigJam")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
