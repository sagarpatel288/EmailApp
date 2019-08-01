package com.example.android.emailapp.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MultiValueExtendedProperty implements Parcelable {

    public final static Parcelable.Creator<MultiValueExtendedProperty> CREATOR = new Creator<MultiValueExtendedProperty>() {

        @SuppressWarnings({
                "unchecked"
        })
        public MultiValueExtendedProperty createFromParcel(Parcel in) {
            return new MultiValueExtendedProperty(in);
        }

        public MultiValueExtendedProperty[] newArray(int size) {
            return (new MultiValueExtendedProperty[size]);
        }

    };
    @SerializedName("@odata.type")
    @Expose
    private String odataType;

    protected MultiValueExtendedProperty(Parcel in) {
        this.odataType = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public MultiValueExtendedProperty() {
    }

    /**
     * @param odataType
     */
    public MultiValueExtendedProperty(String odataType) {
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