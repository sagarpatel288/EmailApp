package com.example.android.emailapp.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Body implements Parcelable {

    public static final Creator<Body> CREATOR = new Creator<Body>() {
        @Override
        public Body createFromParcel(Parcel source) {
            return new Body(source);
        }

        @Override
        public Body[] newArray(int size) {
            return new Body[size];
        }
    };
    @SerializedName("@odata.type")
    @Expose
    private String odataType;
    /* "contentType": "Text",
      "content": "The new cafeteria is open."*/
    @SerializedName("contentType")
    private String contentType;
    @SerializedName("content")
    private String content;

    /**
     * No args constructor for use in serialization
     */
    public Body() {
    }

    /**
     * @param odataType
     */
    public Body(String odataType) {
        super();
        this.odataType = odataType;
    }

    protected Body(Parcel in) {
        this.odataType = in.readString();
        this.contentType = in.readString();
        this.content = in.readString();
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOdataType() {
        return odataType;
    }

    public void setOdataType(String odataType) {
        this.odataType = odataType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.odataType);
        dest.writeString(this.contentType);
        dest.writeString(this.content);
    }
}