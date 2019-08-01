package com.example.android.emailapp.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReplyTo implements Parcelable {

    public final static Parcelable.Creator<ReplyTo> CREATOR = new Creator<ReplyTo>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ReplyTo createFromParcel(Parcel in) {
            return new ReplyTo(in);
        }

        public ReplyTo[] newArray(int size) {
            return (new ReplyTo[size]);
        }

    };
    @SerializedName("@odata.type")
    @Expose
    private String odataType;

    protected ReplyTo(Parcel in) {
        this.odataType = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public ReplyTo() {
    }

    /**
     * @param odataType
     */
    public ReplyTo(String odataType) {
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
