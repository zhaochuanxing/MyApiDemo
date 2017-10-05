package com.xing.apidemo.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.xing.apidemo.R;

public class ActionBarMechanics extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
//        setContentView(R.layout.activity_action_bar_mechanics);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this,"click"+item.getTitle(),Toast.LENGTH_LONG).show();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("item");
        MenuItem menuItem = menu.add("action");
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        menuItem.setIcon(android.R.drawable.ic_search_category_default);

        return true;
    }
}
