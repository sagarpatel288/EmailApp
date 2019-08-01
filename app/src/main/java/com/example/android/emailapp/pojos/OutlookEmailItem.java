package com.example.android.emailapp.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OutlookEmailItem implements Parcelable {

    public final static Parcelable.Creator<OutlookEmailItem> CREATOR = new Creator<OutlookEmailItem>() {


        @SuppressWarnings({
                "unchecked"
        })
        public OutlookEmailItem createFromParcel(Parcel in) {
            return new OutlookEmailItem(in);
        }

        public OutlookEmailItem[] newArray(int size) {
            return (new OutlookEmailItem[size]);
        }

    };
    @SerializedName("@odata.etag")
    @Expose
    private String odataEtag;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("outlookEmailFrom")
    @Expose
    private OutlookEmailFrom outlookEmailFrom;
    @SerializedName("receivedDateTime")
    @Expose
    private String receivedDateTime;

    protected OutlookEmailItem(Parcel in) {
        this.odataEtag = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.subject = ((String) in.readValue((String.class.getClassLoader())));
        this.outlookEmailFrom = ((OutlookEmailFrom) in.readValue((OutlookEmailFrom.class.getClassLoader())));
        this.receivedDateTime = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public OutlookEmailItem() {
    }

    /**
     * @param id
     * @param subject
     * @param receivedDateTime
     * @param outlookEmailFrom
     * @param odataEtag
     */
    public OutlookEmailItem(String odataEtag, String id, String subject, OutlookEmailFrom outlookEmailFrom, String receivedDateTime) {
        super();
        this.odataEtag = odataEtag;
        this.id = id;
        this.subject = subject;
        this.outlookEmailFrom = outlookEmailFrom;
        this.receivedDateTime = receivedDateTime;
    }

    public String getOdataEtag() {
        return odataEtag;
    }

    public void setOdataEtag(String odataEtag) {
        this.odataEtag = odataEtag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public OutlookEmailFrom getOutlookEmailFrom() {
        return outlookEmailFrom;
    }

    public void setOutlookEmailFrom(OutlookEmailFrom outlookEmailFrom) {
        this.outlookEmailFrom = outlookEmailFrom;
    }

    public String getReceivedDateTime() {
        return receivedDateTime;
    }

    public void setReceivedDateTime(String receivedDateTime) {
        this.receivedDateTime = receivedDateTime;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(odataEtag);
        dest.writeValue(id);
        dest.writeValue(subject);
        dest.writeValue(outlookEmailFrom);
        dest.writeValue(receivedDateTime);
    }

}