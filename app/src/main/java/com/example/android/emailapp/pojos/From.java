package com.example.android.emailapp.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class From implements Parcelable {

    public final static Parcelable.Creator<From> CREATOR = new Creator<From>() {


        @SuppressWarnings({
                "unchecked"
        })
        public From createFromParcel(Parcel in) {
            return new From(in);
        }

        public From[] newArray(int size) {
            return (new From[size]);
        }

    };
    @SerializedName("@odata.type")
    @Expose
    private String odataType;

    protected From(Parcel in) {
        this.odataType = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public From() {
    }

    /**
     * @param odataType
     */
    public From(String odataType) {
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