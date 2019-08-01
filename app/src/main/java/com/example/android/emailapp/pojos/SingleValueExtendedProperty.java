package com.example.android.emailapp.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SingleValueExtendedProperty implements Parcelable {

    public final static Parcelable.Creator<SingleValueExtendedProperty> CREATOR = new Creator<SingleValueExtendedProperty>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SingleValueExtendedProperty createFromParcel(Parcel in) {
            return new SingleValueExtendedProperty(in);
        }

        public SingleValueExtendedProperty[] newArray(int size) {
            return (new SingleValueExtendedProperty[size]);
        }

    };
    @SerializedName("@odata.type")
    @Expose
    private String odataType;

    protected SingleValueExtendedProperty(Parcel in) {
        this.odataType = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public SingleValueExtendedProperty() {
    }

    /**
     * @param odataType
     */
    public SingleValueExtendedProperty(String odataType) {
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