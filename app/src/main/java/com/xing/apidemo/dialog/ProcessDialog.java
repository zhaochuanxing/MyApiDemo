package com.xing.apidemo.dialog;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;

import java.text.NumberFormat;

/**
 * Created by zhao on 17-9-24.
 */

public class ProcessDialog extends AlertDialog {

    private String mProgressNumberFormat;
    private NumberFormat mProgressPercentFormat;

    protected ProcessDialog(Context context) {
        this(context,0);
    }

    protected ProcessDialog(Context context, int themeResId) {
        super(context, themeResId);
        initFormate();
    }

    private void initFormate() {
        mProgressNumberFormat = "%1d/%2d";
        mProgressPercentFormat = NumberFormat.getPercentInstance();
        mProgressPercentFormat.setMaximumFractionDigits(0);
    }

    public static ProgressDialog show(Context context,CharSequence title,CharSequence message,boolean indeterminate,
            boolean cancelable,OnCancelListener cancelListener){
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.setIndeterminate(indeterminate);
        progressDialog.setOnCancelListener(cancelListener);
        progressDialog.show();
        return progressDialog;
    }


}
