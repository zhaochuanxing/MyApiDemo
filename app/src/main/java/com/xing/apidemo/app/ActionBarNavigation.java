package com.xing.apidemo.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.xing.apidemo.R;

public class ActionBarNavigation extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar_navigation);
        ActionBar actionBar = getActionBar();
        if(actionBar!=null){
            //// TODO: 17-10-5
//            actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP,ActionBar.DISPLAY_HOME_AS_UP);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayUseLogoEnabled(true);
//            actionBar.setIcon(getResources().getDrawable(R.drawable.card_history));
        }
        Button button =(Button)findViewById(R.id.button_press);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActionBarNavigation.this,ActionBarNavigation.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
                }
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
