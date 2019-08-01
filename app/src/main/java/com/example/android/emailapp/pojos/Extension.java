package com.example.android.emailapp.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Extension implements Parcelable {

    public final static Parcelable.Creator<Extension> CREATOR = new Creator<Extension>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Extension createFromParcel(Parcel in) {
            return new Extension(in);
        }

        public Extension[] newArray(int size) {
            return (new Extension[size]);
        }

    };
    @SerializedName("@odata.type")
    @Expose
    private String odataType;

    protected Extension(Parcel in) {
        this.odataType = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public Extension() {
    }

    /**
     * @param odataType
     */
    public Extension(String odataType) {
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

