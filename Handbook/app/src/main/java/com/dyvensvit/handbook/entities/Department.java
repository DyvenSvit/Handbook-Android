package com.dyvensvit.handbook.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Maksym on 3/29/2015.
 */
public class Department  implements Parcelable {

    public  String mCode;
    public  String mTitle;
    public  String mChief;
    public  String mAddress;
    public  String mEmail;
    public  String mWebsite;
    public  String mPhone;
    public  String mActivity;
    public  String mMagazine;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mCode);
        dest.writeString(mTitle);
        dest.writeString(mChief);
        dest.writeString(mAddress);
        dest.writeString(mEmail);
        dest.writeString(mWebsite);
        dest.writeString(mPhone);
        dest.writeString(mActivity);
        dest.writeString(mMagazine);
    }

    public Department()
    {}

    public Department(Parcel in) {
        this.mCode = in.readString();
        this.mTitle = in.readString();
        this.mChief = in.readString();
        this.mAddress = in.readString();
        this.mEmail = in.readString();
        this.mWebsite = in.readString();
        this.mPhone = in.readString();
        this.mActivity = in.readString();
        this.mMagazine = in.readString();
    }

    public static final Parcelable.Creator<Department> CREATOR
            = new Parcelable.Creator<Department>() {

        public Department createFromParcel(Parcel in) {
            return new Department(in);
        }

        public Department[] newArray(int size) {
            return new Department[size];
        }
    };
}
