package com.example.android.emailapp.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UniqueBody implements Parcelable {

    public final static Parcelable.Creator<UniqueBody> CREATOR = new Creator<UniqueBody>() {


        @SuppressWarnings({
                "unchecked"
        })
        public UniqueBody createFromParcel(Parcel in) {
            return new UniqueBody(in);
        }

        public UniqueBody[] newArray(int size) {
            return (new UniqueBody[size]);
        }

    };
    @SerializedName("@odata.type")
    @Expose
    private String odataType;

    protected UniqueBody(Parcel in) {
        this.odataType = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public UniqueBody() {
    }

    /**
     * @param odataType
     */
    public UniqueBody(String odataType) {
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