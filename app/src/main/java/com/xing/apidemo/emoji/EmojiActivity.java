package com.xing.apidemo.emoji;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.xing.apidemo.R;

public class EmojiActivity extends AppCompatActivity {

    private TextView mEmojiTx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emoji);
        mEmojiTx  = (TextView)findViewById(R.id.tx_emoji);
        int code = 0x1F602;
        code = 0x1F60B;
        code = 0x1F60D;
        setEmojiText(mEmojiTx,code);
    }

    private void setEmojiText(TextView textView,int uniCode){
        if(textView!=null){
            String value = getEmojiStringByUnicode(uniCode);
            textView.setText(value);
        }
    }

    private String getEmojiStringByUnicode(int uniCode){
        return new String(Character.toChars(uniCode));

    }
}
