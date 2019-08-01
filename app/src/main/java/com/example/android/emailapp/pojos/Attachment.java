package com.example.android.emailapp.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attachment implements Parcelable {

    public final static Parcelable.Creator<Attachment> CREATOR = new Creator<Attachment>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Attachment createFromParcel(Parcel in) {
            return new Attachment(in);
        }

        public Attachment[] newArray(int size) {
            return (new Attachment[size]);
        }

    };
    @SerializedName("@odata.type")
    @Expose
    private String odataType;

    protected Attachment(Parcel in) {
        this.odataType = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public Attachment() {
    }

    /**
     * @param odataType
     */
    public Attachment(String odataType) {
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

