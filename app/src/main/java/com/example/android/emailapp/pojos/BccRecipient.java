package com.example.android.emailapp.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BccRecipient implements Parcelable {

    public final static Parcelable.Creator<BccRecipient> CREATOR = new Creator<BccRecipient>() {


        @SuppressWarnings({
                "unchecked"
        })
        public BccRecipient createFromParcel(Parcel in) {
            return new BccRecipient(in);
        }

        public BccRecipient[] newArray(int size) {
            return (new BccRecipient[size]);
        }

    };
    @SerializedName("@odata.type")
    @Expose
    private String odataType;

    protected BccRecipient(Parcel in) {
        this.odataType = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public BccRecipient() {
    }

    /**
     * @param odataType
     */
    public BccRecipient(String odataType) {
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