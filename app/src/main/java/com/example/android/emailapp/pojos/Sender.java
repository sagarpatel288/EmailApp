package com.example.android.emailapp.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sender implements Parcelable {

    @SerializedName("@odata.type")
    @Expose
    private String odataType;

    public OutlookEmailAddress getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(OutlookEmailAddress emailAddress) {
        this.emailAddress = emailAddress;
    }

    @SerializedName("emailAddress")
    @Expose
    private OutlookEmailAddress emailAddress;

    /**
     * No args constructor for use in serialization
     */
    public Sender() {
    }

    /**
     * @param odataType
     */
    public Sender(String odataType) {
        super();
        this.odataType = odataType;
    }

    public String getOdataType() {
        return odataType;
    }

    public void setOdataType(String odataType) {
        this.odataType = odataType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.odataType);
        dest.writeParcelable(this.emailAddress, flags);
    }

    protected Sender(Parcel in) {
        this.odataType = in.readString();
        this.emailAddress = in.readParcelable(OutlookEmailAddress.class.getClassLoader());
    }

    public static final Creator<Sender> CREATOR = new Creator<Sender>() {
        @Override
        public Sender createFromParcel(Parcel source) {
            return new Sender(source);
        }

        @Override
        public Sender[] newArray(int size) {
            return new Sender[size];
        }
    };
}