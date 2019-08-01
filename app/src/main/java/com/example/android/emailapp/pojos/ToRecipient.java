package com.example.android.emailapp.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ToRecipient implements Parcelable {

    public final static Parcelable.Creator<ToRecipient> CREATOR = new Creator<ToRecipient>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ToRecipient createFromParcel(Parcel in) {
            return new ToRecipient(in);
        }

        public ToRecipient[] newArray(int size) {
            return (new ToRecipient[size]);
        }

    };
    @SerializedName("@odata.type")
    @Expose
    private String odataType;

    protected ToRecipient(Parcel in) {
        this.odataType = ((String) in.readValue((String.class.getClassLoader())));
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

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(odataType);
    }

}