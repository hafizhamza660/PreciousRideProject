package com.example.task.RoomDatabaseFiles;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RideDao {

    @Query("SELECT * FROM ridedatatable")
    List<RideDataTable> getAll();

    @Insert
    void insert(RideDataTable task);

    @Delete
    void delete(RideDataTable task);

    @Update
    void update(RideDataTable task);
}
