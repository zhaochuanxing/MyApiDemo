package com.xing.apidemo.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.xing.apidemo.R;
import com.xing.apidemo.drawable.CustomDrawable;
import com.xing.apidemo.drawable.RoundImageDrawable;

public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        View myView = findViewById(R.id.my_view);
        CustomDrawable customDrawable = new CustomDrawable(Color.YELLOW);
        myView.setBackgroundDrawable(customDrawable);

        ImageView imageView = (ImageView)findViewById(R.id.img_show);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.card_history);
        RoundImageDrawable imageDrawable = new RoundImageDrawable(bitmap,true);
        imageView.setImageDrawable(imageDrawable);
    }
}
