package com.example.android.emailapp.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Sagar on 7/31/2019 at 3:21 AM
 * <p>
 * $
 * <p>
 * Being used in {@link }
 *
 * @see
 * @since $
 */
public class OutlookResponse implements Parcelable {
    @SerializedName("@odata.context")
    private String oDataContext;

    public String getoDataNextLink() {
        return oDataNextLink;
    }

    public void setoDataNextLink(String oDataNextLink) {
        this.oDataNextLink = oDataNextLink;
    }

    @SerializedName("@odata.nextLink")
    private String oDataNextLink;
    @SerializedName("value")
    private List<OutlookMessage> values;

    public OutlookResponse() {
    }

    public String getoDataContext() {
        return oDataContext;
    }

    public void setoDataContext(String oDataContext) {
        this.oDataContext = oDataContext;
    }

    public List<OutlookMessage> getValues() {
        return values;
    }

    public void setValues(List<OutlookMessage> values) {
        this.values = values;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.oDataContext);
        dest.writeString(this.oDataNextLink);
        dest.writeTypedList(this.values);
    }

    protected OutlookResponse(Parcel in) {
        this.oDataContext = in.readString();
        this.oDataNextLink = in.readString();
        this.values = in.createTypedArrayList(OutlookMessage.CREATOR);
    }

    public static final Creator<OutlookResponse> CREATOR = new Creator<OutlookResponse>() {
        @Override
        public OutlookResponse createFromParcel(Parcel source) {
            return new OutlookResponse(source);
        }

        @Override
        public OutlookResponse[] newArray(int size) {
            return new OutlookResponse[size];
        }
    };
}
