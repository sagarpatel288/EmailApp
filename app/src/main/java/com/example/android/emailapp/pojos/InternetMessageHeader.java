package com.example.android.emailapp.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InternetMessageHeader implements Parcelable {

    public final static Parcelable.Creator<InternetMessageHeader> CREATOR = new Creator<InternetMessageHeader>() {


        @SuppressWarnings({
                "unchecked"
        })
        public InternetMessageHeader createFromParcel(Parcel in) {
            return new InternetMessageHeader(in);
        }

        public InternetMessageHeader[] newArray(int size) {
            return (new InternetMessageHeader[size]);
        }

    };
    @SerializedName("@odata.type")
    @Expose
    private String odataType;

    protected InternetMessageHeader(Parcel in) {
        this.odataType = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public InternetMessageHeader() {
    }

    /**
     * @param odataType
     */
    public InternetMessageHeader(String odataType) {
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