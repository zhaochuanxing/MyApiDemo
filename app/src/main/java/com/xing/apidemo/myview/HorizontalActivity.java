package com.xing.apidemo.myview;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.xing.apidemo.R;

public class HorizontalActivity extends Activity {

    private ListView mOneListView;
    private ListView mTwoListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal);
        initView();
    }

    private void initView() {
        mOneListView = (ListView)findViewById(R.id.list_one);
        mTwoListView = (ListView)findViewById(R.id.list_two);
        String[] oneList = {"1","2","3","4"};
        String[] twoList = {"a","b","c","d"};
        ArrayAdapter<String> oneAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,oneList);
        mOneListView.setAdapter(oneAdapter);
        ArrayAdapter<String> twoAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,twoList);
        mTwoListView.setAdapter(twoAdapter);
    }
}
