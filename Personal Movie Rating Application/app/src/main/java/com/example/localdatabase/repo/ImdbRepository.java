package com.example.localdatabase.repo;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.example.localdatabase.model.ModelImdb;
import com.example.localdatabase.room.MyRoomDataBase;
import com.example.localdatabase.room.RoomDao;

import java.util.List;

public class ImdbRepository {

    private RoomDao roomDao;
    private MyRoomDataBase roomDataBase;
    private LiveData<List<ModelImdb>> allData;
    private Context context;

    public ImdbRepository(Context context) {
        this.context = context;
        roomDataBase = MyRoomDataBase.getInstance(context);
        roomDao = roomDataBase.roomDao();
        allData = roomDao.getAllData();
    }


    public LiveData<List<ModelImdb>> getAllData() {
        return this.allData;
    }

    public void  deleteSingleData(Double id)
    {
        new DeleteSingleData(roomDao).execute(id);
    }

    public void insertSingleData(ModelImdb imdb) {
        new InsertData(roomDao).execute(imdb);
    }

    public void updateData(ModelImdb imdb)
    {
        new UpdateSingleData(roomDao).execute(imdb);
    }

    private class InsertData extends AsyncTask<ModelImdb, Void, Void> {

        RoomDao roomDao;

        InsertData(RoomDao roomDao) {
            this.roomDao = roomDao;
        }

        @Override
        protected Void doInBackground(ModelImdb... modelImdbs) {

            roomDao.insertSingleData(modelImdbs[0]);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Toast.makeText(context, "Insertion Successful!", Toast.LENGTH_SHORT).show();

        }
    }

    private class DeleteSingleData extends AsyncTask<Double,Void,Void> {

        RoomDao roomDao;
        public DeleteSingleData(RoomDao roomDao) {
            this.roomDao = roomDao;
        }

        @Override
        protected Void doInBackground(Double... ints) {
            roomDao.deleteSingleData(ints[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(context, "Delete SuccessFul", Toast.LENGTH_SHORT).show();
        }
    }

    private class UpdateSingleData extends AsyncTask<ModelImdb,Void,Void>{

        private RoomDao roomDao;

        public UpdateSingleData(RoomDao roomDao) {
            this.roomDao = roomDao;
        }

        @Override
        protected Void doInBackground(ModelImdb... modelImdbs) {

            roomDao.updateSingleData(modelImdbs[0]);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(context, "Update Successful", Toast.LENGTH_SHORT).show();
        }
    }
}
