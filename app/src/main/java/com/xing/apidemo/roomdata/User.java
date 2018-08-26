package com.xing.apidemo.roomdata;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {

    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name="userName")
    public String name;
    @ColumnInfo(name="userAge")
    public int age;
    @ColumnInfo(name="updateTime")
    public long updateTime;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", updateTime=" + updateTime +
                '}';
    }
}
