package com.example.android.emailapp.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sender implements Parcelable {

    public final static Parcelable.Creator<Sender> CREATOR = new Creator<Sender>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Sender createFromParcel(Parcel in) {
            return new Sender(in);
        }

        public Sender[] newArray(int size) {
            return (new Sender[size]);
        }

    };
    @SerializedName("@odata.type")
    @Expose
    private String odataType;

    protected Sender(Parcel in) {
        this.odataType = ((String) in.readValue((String.class.getClassLoader())));
    }

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

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(odataType);
    }

}