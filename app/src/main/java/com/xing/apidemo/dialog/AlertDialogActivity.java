package com.xing.apidemo.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.xing.apidemo.R;

public class AlertDialogActivity extends AppCompatActivity {

    private static final int DIALOG_YES_NO_MESSAGE = 1;
    private static final int DIALOG_HOOL_LIGHT = 2;
    private static final int DIALOG_LIST = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_YES_NO_MESSAGE);
            }
        });
        button = (Button)findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_HOOL_LIGHT);
            }
        });

        button = (Button)findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_LIST);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id){
            case DIALOG_YES_NO_MESSAGE:
                return new AlertDialog.Builder(AlertDialogActivity.this)
                        .setTitle("dialog title")
                        .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton("cancel",null).create();
            case DIALOG_HOOL_LIGHT:
                int styleId = android.R.style.Theme_Material_Light_Dialog_Alert;
                styleId = android.R.style.Theme_DeviceDefault_Dialog_Alert;
                styleId = android.R.style.Theme_DeviceDefault_Light_Dialog_Alert;
                return new AlertDialog.Builder(AlertDialogActivity.this,styleId)
                        .setTitle("material title")
                        .setMessage("material dialog")
                        .setPositiveButton("好的",null)
                        .setNeutralButton("看看",null)
                        .setNegativeButton("算了",null)
                        .create();
            case DIALOG_LIST:
                return new AlertDialog.Builder(AlertDialogActivity.this)
                        .setTitle("list dialog")
                        .setItems(new String[] { "one", "two", "three" }, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(AlertDialogActivity.this,""+which,Toast.LENGTH_LONG).show();
                            }
                        }).create();
        }
        return null;
    }
}
