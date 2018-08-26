package com.xing.apidemo.roomdata;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {User.class},version = 1)
public abstract class UserDatabase extends RoomDatabase {

    public abstract UserDao getUserDao();

}
