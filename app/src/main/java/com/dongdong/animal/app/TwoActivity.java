package com.dongdong.animal.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.dongdong.animal.termite.ITermiteAidlInterface;
import com.dongdong.animal.termite.bean.TermiteEventContainer;
import com.dongdong.animal.termite.callback.TermiteCallBack;

public class TwoActivity extends AppCompatActivity {


    private ITermiteAidlInterface termiteAidlInterface;
    private boolean isConnect = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);


        findViewById(R.id.sendEvent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TermiteEventContainer container = new TermiteEventContainer();
                container.setCode("2");
                container.setMsg("TwoActivity Send Message");
                try {
                    termiteAidlInterface.sendEventMessage(container);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }
        });

        bindservice();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isConnect) {
            unbindService(serviceConnection);
        }
    }

    private void bindservice() {
        if (!isConnect) {
            Intent intent = new Intent();
            intent.setPackage(getApplicationContext().getPackageName());
            intent.setAction("android.intent.action.aidl.termite.service");
            bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

        }
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            termiteAidlInterface = ITermiteAidlInterface.Stub.asInterface(service);
            try {
                termiteAidlInterface.registerCallback(callBack);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            isConnect = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isConnect = false;
        }
    };


    private TermiteCallBack callBack = new TermiteCallBack.Stub() {
        @Override
        public void receiveEventMmessageListener(TermiteEventContainer msg) throws RemoteException {
            if (msg != null) {
                Log.d("TwoActivity", "接收到到消息为：" + msg.getMsg());
            }
        }
    };
}
