package com.xing.apidemo.greendao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xing.apidemo.MyApplication;
import com.xing.apidemo.R;
import com.xing.apidemo.roomdata.UserDatabase;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class GreenActivity extends AppCompatActivity {

    private Button mInsertBtn;
    private UserDao userDao;
    private Button mShowBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green);
        mInsertBtn = (Button)findViewById(R.id.btn_insert);
        mShowBtn = (Button)findViewById(R.id.btn_show);

        initDb();
        mInsertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setAge(12);
                user.setId(234);
                user.setName("zhao");
                userDao.insertOrReplace(user);

            }
        });
        mShowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<User> users = userDao.loadAll();
                User user = userDao.loadByRowId(1);
                List<User> users1 = userDao.queryRaw("where AGE>?", "10");

                QueryBuilder<User> builder = userDao.queryBuilder();
                List<User> list = builder.where(UserDao.Properties.Age.gt(10)).build().list();



            }
        });
    }

    private void initDb() {
        MyApplication myApplication = MyApplication.getApplication();
        DaoSession daoSession = myApplication.getDaoSession();
        userDao = daoSession.getUserDao();


    }
}
