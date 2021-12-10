package com.example.figjam.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.figjam.Database.FigJamDatabase;
import com.example.figjam.Database.TypecodeDAO;
import com.example.figjam.Models.TypecodeUsersModel;

import java.util.List;

public class TypecodeRoomRepository {
    private TypecodeDAO typecodeDAO;
    private LiveData<List<TypecodeUsersModel>> allData;
    private static TypecodeRepository typecodeRepository;

    public TypecodeRoomRepository(Application application){
        FigJamDatabase db = FigJamDatabase.getDatabase(application);
        typecodeDAO = db.typecodeDAO();
        allData = typecodeDAO.selectAll();

    }
    public LiveData<List<TypecodeUsersModel>> getAllData() {
        return allData;
    }
    public void insertData(TypecodeUsersModel data){
        new TypecodeUserInsertion(typecodeDAO).execute(data);
    }
    public void delete(TypecodeUsersModel typecodeUsersModel){
        new DeleteUserAsyncTask(typecodeDAO).execute(typecodeUsersModel);
    }
    public void update(TypecodeUsersModel typecodeUsersModel){
        new UpdateUserAsyncTask(typecodeDAO).execute(typecodeUsersModel);
    }
    private class UpdateUserAsyncTask extends AsyncTask<TypecodeUsersModel, Void, Void>{
        private TypecodeDAO typecodeDAO;

        public UpdateUserAsyncTask(TypecodeDAO typecodeDAO) {
            this.typecodeDAO = typecodeDAO;
        }
        @Override
        protected Void doInBackground(TypecodeUsersModel... data){
            typecodeDAO.update(data[0]);
            return null;
        }
    }
    public static class TypecodeUserInsertion extends AsyncTask<TypecodeUsersModel,Void, Void> {
        private TypecodeDAO typecodeDAO;

        private TypecodeUserInsertion(TypecodeDAO typecodeDAO){
            this.typecodeDAO = typecodeDAO;
        }
        @Override
        protected Void doInBackground(TypecodeUsersModel... data){

            typecodeDAO.insert(data[0]);
            return null;
        }
    }

    private class DeleteUserAsyncTask extends AsyncTask<TypecodeUsersModel, Void, Void>{
        private TypecodeDAO typecodeDAO;

        public DeleteUserAsyncTask(TypecodeDAO typecodeDAO) {
            this.typecodeDAO = typecodeDAO;
        }
        @Override
        protected Void doInBackground(TypecodeUsersModel... data){
            typecodeDAO.delete(data[0]);
            return null;
        }
    }
}
