package com.dongdong.animal.termite.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class TermiteEventContainer implements Parcelable {

    private String code;
    private String type;
    private String msg;

    public TermiteEventContainer() {
    }


    protected TermiteEventContainer(Parcel in) {
        code = in.readString();
        type = in.readString();
        msg = in.readString();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static final Creator<TermiteEventContainer> CREATOR = new Creator<TermiteEventContainer>() {
        @Override
        public TermiteEventContainer createFromParcel(Parcel in) {
            return new TermiteEventContainer(in);
        }

        @Override
        public TermiteEventContainer[] newArray(int size) {
            return new TermiteEventContainer[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(code);
        dest.writeString(type);
        dest.writeString(msg);
    }

    public void readFromParcel(Parcel in) {
        code = in.readString();
        type = in.readString();
        msg = in.readString();
    }
}
