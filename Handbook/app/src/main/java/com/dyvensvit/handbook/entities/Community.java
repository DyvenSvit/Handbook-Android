package com.dyvensvit.handbook.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Maksym on 3/29/2015.
 */
public class Community  implements Parcelable {
    public String mCode;
    public String mDepCode;
    public String mTitle;
    public String mChief;
    public String mAddress;
    public String mEmail;
    public String mWebsite;
    public String mPhone;
    public String mFather;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mCode);
        dest.writeString(mDepCode);
        dest.writeString(mTitle);
        dest.writeString(mChief);
        dest.writeString(mAddress);
        dest.writeString(mEmail);
        dest.writeString(mWebsite);
        dest.writeString(mPhone);
        dest.writeString(mFather);
    }

    public Community()
    {}

    public Community(Parcel in) {
        this.mCode = in.readString();
        this.mDepCode = in.readString();
        this.mTitle = in.readString();
        this.mChief = in.readString();
        this.mAddress = in.readString();
        this.mEmail = in.readString();
        this.mWebsite = in.readString();
        this.mPhone = in.readString();
        this.mFather = in.readString();
    }

    public static final Parcelable.Creator<Community> CREATOR
            = new Parcelable.Creator<Community>() {

        public Community createFromParcel(Parcel in) {
            return new Community(in);
        }

        public Community[] newArray(int size) {
            return new Community[size];
        }
    };
}