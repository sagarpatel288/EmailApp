package com.example.android.emailapp.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OutlookEmailFrom implements Parcelable {

    public final static Parcelable.Creator<OutlookEmailFrom> CREATOR = new Creator<OutlookEmailFrom>() {


        @SuppressWarnings({
                "unchecked"
        })
        public OutlookEmailFrom createFromParcel(Parcel in) {
            return new OutlookEmailFrom(in);
        }

        public OutlookEmailFrom[] newArray(int size) {
            return (new OutlookEmailFrom[size]);
        }

    };
    @SerializedName("outlookEmailAddress")
    @Expose
    private OutlookEmailAddress outlookEmailAddress;

    protected OutlookEmailFrom(Parcel in) {
        this.outlookEmailAddress = ((OutlookEmailAddress) in.readValue((OutlookEmailAddress.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public OutlookEmailFrom() {
    }

    /**
     * @param outlookEmailAddress
     */
    public OutlookEmailFrom(OutlookEmailAddress outlookEmailAddress) {
        super();
        this.outlookEmailAddress = outlookEmailAddress;
    }

    public OutlookEmailAddress getOutlookEmailAddress() {
        return outlookEmailAddress;
    }

    public void setOutlookEmailAddress(OutlookEmailAddress outlookEmailAddress) {
        this.outlookEmailAddress = outlookEmailAddress;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(outlookEmailAddress);
    }

}