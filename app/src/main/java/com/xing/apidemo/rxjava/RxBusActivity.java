package com.xing.apidemo.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.xing.apidemo.R;

import rx.Subscription;
import rx.functions.Action1;

public class RxBusActivity extends AppCompatActivity {

    private Button mRxBtn;
    private Subscription mSubsciption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_bus);

        //订阅，以MessageEvent为类型;并生成一个订阅对象
        mSubsciption = RxBus.getInstance().toObservable(MessageEvent.class)
                .subscribe(new Action1<MessageEvent>() {
                    @Override
                    public void call(MessageEvent messageEvent) {
                        //接收和处理事件
                        Toast.makeText(RxBusActivity.this,"messageEvent "+messageEvent.mMsg,Toast.LENGTH_LONG).show();

                    }
                });
        initView();
    }

    private void initView() {
        mRxBtn = (Button)findViewById(R.id.btn_bus);
        mRxBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发送事件
                RxBus.getInstance().post(new MessageEvent("zcx"));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消订阅
        if(mSubsciption!=null && !mSubsciption.isUnsubscribed()){
            mSubsciption.unsubscribe();
        }
    }

    class MessageEvent{
        public String mMsg;

        public MessageEvent(String msg) {
            mMsg = msg;
        }
    }
}
