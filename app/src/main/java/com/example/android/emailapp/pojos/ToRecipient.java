package com.example.android.emailapp.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ToRecipient implements Parcelable {

    @SerializedName("@odata.type")
    @Expose
    private String odataType;

    public static final Creator<ToRecipient> CREATOR = new Creator<ToRecipient>() {
        @Override
        public ToRecipient createFromParcel(Parcel source) {
            return new ToRecipient(source);
        }

        @Override
        public ToRecipient[] newArray(int size) {
            return new ToRecipient[size];
        }
    };
    @SerializedName("emailAddress")
    private OutlookEmailAddress emailAddress;

    protected ToRecipient(Parcel in) {
        this.odataType = in.readString();
        this.emailAddress = in.readParcelable(OutlookEmailAddress.class.getClassLoader());
    }

    /**
     * No args constructor for use in serialization
     */
    public ToRecipient() {
    }

    /**
     * @param odataType
     */
    public ToRecipient(String odataType) {
        super();
        this.odataType = odataType;
    }

    public String getOdataType() {
        return odataType;
    }

    public void setOdataType(String odataType) {
        this.odataType = odataType;
    }

    public OutlookEmailAddress getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(OutlookEmailAddress emailAddress) {
        this.emailAddress = emailAddress;
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
}