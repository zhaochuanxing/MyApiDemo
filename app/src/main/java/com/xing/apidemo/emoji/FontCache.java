package com.xing.apidemo.emoji;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by zhao on 18-1-23.
 */

public class FontCache {

    private static final String TAG = FontCache.class.getSimpleName();

    private static HashMap<String, Typeface> sFontCache = new HashMap<>();

    public static Typeface getTypeFace(String fontName, Context context) {
        Typeface typeface = sFontCache.get(fontName);
        if (typeface == null) {
            try {
                typeface = Typeface.createFromAsset(context.getAssets(), fontName);
            } catch (Exception e) {
                Log.e(TAG, "getFont ", e);
            }
            sFontCache.put(fontName, typeface);
        }
        return typeface;
    }
}
