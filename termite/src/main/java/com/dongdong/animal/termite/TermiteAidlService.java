package com.dongdong.animal.termite;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;

import com.dongdong.animal.termite.bean.TermiteEventContainer;
import com.dongdong.animal.termite.callback.TermiteCallBack;

public class TermiteAidlService extends Service {


    final RemoteCallbackList<TermiteCallBack> callbackList = new RemoteCallbackList<>();

    ITermiteAidlInterface.Stub stub = new ITermiteAidlInterface.Stub() {
        @Override
        public void sendEventMessage(TermiteEventContainer event) throws RemoteException {
            int len = callbackList.beginBroadcast();
            for (int i = 0; i < len; i++) {
                TermiteCallBack callBack = callbackList.getBroadcastItem(i);
                if (callBack != null) {
                    callBack.receiveEventMmessageListener(event);
                }
            }
            callbackList.finishBroadcast();
        }

        @Override
        public void registerCallback(TermiteCallBack callback) throws RemoteException {
            if (callback != null) {
                callbackList.register(callback);
            }
        }

        @Override
        public void unregisterCallback(TermiteCallBack callback) throws RemoteException {
            if (callback != null) {
                callbackList.unregister(callback);
            }
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }
}
