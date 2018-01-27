package com.xing.apidemo.emoji;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.google.gson.TypeAdapterFactory;

import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by zhao on 18-1-23.
 */

@SuppressLint("AppCompatCustomView")
public class FontTextView extends TextView {

    private static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android" ;

    public FontTextView(Context context) {
        super(context);
        init(context, null);
    }

    public FontTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public FontTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        Typeface typeface = null;
        if(attrs!=null){
            int textStyle = attrs.getAttributeIntValue(ANDROID_SCHEMA, "textStyle", Typeface.NORMAL);
            switch(textStyle){
                case Typeface.BOLD:
                    typeface = FontCache.getTypeFace("OpenSans-Bold.ttf",context);
                    break;
                case Typeface.ITALIC:
                    typeface = FontCache.getTypeFace("OpenSans-Italic.ttf",context);
                    break;
                case Typeface.BOLD_ITALIC:
                    typeface = FontCache.getTypeFace("OpenSans-BoldItalic.ttf",context);
                    break;
                default:
                    typeface = FontCache.getTypeFace("OpenSans-Regular.ttf",context);
                    break;
            }
        }
        setTypeface(typeface);
    }
}
