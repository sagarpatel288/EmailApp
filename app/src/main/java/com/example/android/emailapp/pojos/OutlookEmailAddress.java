package com.example.android.emailapp.pojos;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OutlookEmailAddress implements Parcelable {

    public final static Parcelable.Creator<OutlookEmailAddress> CREATOR = new Creator<OutlookEmailAddress>() {


        @SuppressWarnings({
                "unchecked"
        })
        public OutlookEmailAddress createFromParcel(Parcel in) {
            return new OutlookEmailAddress(in);
        }

        public OutlookEmailAddress[] newArray(int size) {
            return (new OutlookEmailAddress[size]);
        }

    };
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("name")
    @Expose
    private String name;

    protected OutlookEmailAddress(Parcel in) {
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public OutlookEmailAddress() {
    }

    /**
     * @param address
     * @param name
     */
    public OutlookEmailAddress(String address, String name) {
        super();
        this.address = address;
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(address);
        dest.writeValue(name);
    }

}

