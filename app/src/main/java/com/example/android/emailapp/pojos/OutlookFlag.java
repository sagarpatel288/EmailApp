package com.example.android.emailapp.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OutlookFlag implements Parcelable {

    public final static Parcelable.Creator<OutlookFlag> CREATOR = new Creator<OutlookFlag>() {


        @SuppressWarnings({
                "unchecked"
        })
        public OutlookFlag createFromParcel(Parcel in) {
            return new OutlookFlag(in);
        }

        public OutlookFlag[] newArray(int size) {
            return (new OutlookFlag[size]);
        }

    };
    @SerializedName("flagStatus")
    @Expose
    private String flagStatus;

    protected OutlookFlag(Parcel in) {
        this.flagStatus = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public OutlookFlag() {
    }

    /**
     * @param flagStatus
     */
    public OutlookFlag(String flagStatus) {
        super();
        this.flagStatus = flagStatus;
    }

    public String getFlagStatus() {
        return flagStatus;
    }

    public void setFlagStatus(String flagStatus) {
        this.flagStatus = flagStatus;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(flagStatus);
    }

}