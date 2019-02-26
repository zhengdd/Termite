// ITermiteAidlInterface.aidl
package com.dongdong.animal.termite;

import com.dongdong.animal.termite.bean.TermiteEventContainer;
import com.dongdong.animal.termite.callback.TermiteCallBack;

interface ITermiteAidlInterface {

    void sendEventMessage(inout TermiteEventContainer event);

    void registerCallback(in TermiteCallBack callback);

     void unregisterCallback(in TermiteCallBack callback);
}
