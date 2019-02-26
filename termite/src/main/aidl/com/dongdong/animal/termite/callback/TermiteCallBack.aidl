// TermiteCallBack.aidl
package com.dongdong.animal.termite.callback;

// Declare any non-default types here with import statements

import com.dongdong.animal.termite.bean.TermiteEventContainer;

interface TermiteCallBack {

   void receiveEventMmessageListener(inout TermiteEventContainer msg);

}
