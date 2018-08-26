package com.xing.apidemo.roomdata;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.support.annotation.IntDef;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user_table")
    public List<User> getAllUsers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertUser(User... users);

    @Update
    public void updateUser(User...users);

    @Delete
    public void deleteUser(User...users);


}
