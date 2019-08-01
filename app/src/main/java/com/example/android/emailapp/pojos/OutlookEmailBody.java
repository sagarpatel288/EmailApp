package com.example.android.emailapp.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class OutlookEmailBody implements Parcelable {

    public final static Parcelable.Creator<OutlookEmailBody> CREATOR = new Creator<OutlookEmailBody>() {


        @SuppressWarnings({
                "unchecked"
        })
        public OutlookEmailBody createFromParcel(Parcel in) {
            return new OutlookEmailBody(in);
        }

        public OutlookEmailBody[] newArray(int size) {
            return (new OutlookEmailBody[size]);
        }

    };
    @SerializedName("contentType")
    @Expose
    private String contentType;
    @SerializedName("content")
    @Expose
    private String content;

    protected OutlookEmailBody(Parcel in) {
        this.contentType = ((String) in.readValue((String.class.getClassLoader())));
        this.content = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public OutlookEmailBody() {
    }

    /**
     * @param content
     * @param contentType
     */
    public OutlookEmailBody(String contentType, String content) {
        super();
        this.contentType = contentType;
        this.content = content;
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

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(contentType);
        dest.writeValue(content);
    }

}



