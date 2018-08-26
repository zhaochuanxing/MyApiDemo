package com.xing.apidemo.roomdata;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.xing.apidemo.R;

import java.util.List;

public class RoomActivity extends AppCompatActivity {

    private static final String TAG = RoomActivity.class.getSimpleName();
    private UserDao mUserDao;
    private Button mInsertBtn;
    private Button mReadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        new Thread(new Runnable() {
            @Override
            public void run() {
                initDb();
            }
        }).start();
        mInsertBtn = (Button)findViewById(R.id.btn_insert);
        mReadBtn = (Button)findViewById(R.id.btn_read);
        mInsertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        writeDb(mUserDao,"tian",23);

                    }
                }).start();
            }
        });
        mReadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        readDb(mUserDao);
                    }
                }).start();
            }
        });

    }

    private void initDb() {
        UserDatabase userDatabase = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, "users").build();
        mUserDao = userDatabase.getUserDao();

        writeDb(mUserDao, "zhao", 12);
    }

    private List<User> readDb(UserDao userDao){
        List<User> allUsers = userDao.getAllUsers();
        Log.i(TAG,"all Users: "+allUsers);
        return allUsers;
    }

    private void writeDb(UserDao userDao, String name, int age) {
        User user = new User();
        user.age = age;
        user.name = name;
        user.updateTime = System.currentTimeMillis();
        userDao.insertUser(user);
    }

    private void deleteDb(UserDao userDao ,int userId){
        User user = new User();
        user.id = userId;
        userDao.deleteUser(user);
    }


}
