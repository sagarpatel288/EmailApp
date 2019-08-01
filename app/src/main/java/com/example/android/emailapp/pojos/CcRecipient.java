package com.example.android.emailapp.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CcRecipient implements Parcelable {

    public final static Parcelable.Creator<CcRecipient> CREATOR = new Creator<CcRecipient>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CcRecipient createFromParcel(Parcel in) {
            return new CcRecipient(in);
        }

        public CcRecipient[] newArray(int size) {
            return (new CcRecipient[size]);
        }

    };
    @SerializedName("@odata.type")
    @Expose
    private String odataType;

    protected CcRecipient(Parcel in) {
        this.odataType = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public CcRecipient() {
    }

    /**
     * @param odataType
     */
    public CcRecipient(String odataType) {
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