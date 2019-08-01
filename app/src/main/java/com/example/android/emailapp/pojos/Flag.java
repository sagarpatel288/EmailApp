package com.example.android.emailapp.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Flag implements Parcelable {

    public final static Parcelable.Creator<Flag> CREATOR = new Creator<Flag>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Flag createFromParcel(Parcel in) {
            return new Flag(in);
        }

        public Flag[] newArray(int size) {
            return (new Flag[size]);
        }

    };
    @SerializedName("@odata.type")
    @Expose
    private String odataType;

    protected Flag(Parcel in) {
        this.odataType = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public Flag() {
    }

    /**
     * @param odataType
     */
    public Flag(String odataType) {
        super();
        this.odataType = odataType;
    }

    public String getOdataType() {
        return odataType;
    }

    public void setOdataType(String odataType) {
        this.odataType = odataType;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(odataType);
    }

}