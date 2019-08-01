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
    public static final Parcelable.Creator<OutlookResponse> CREATOR = new Parcelable.Creator<OutlookResponse>() {
        @Override
        public OutlookResponse createFromParcel(Parcel source) {
            return new OutlookResponse(source);
        }

        @Override
        public OutlookResponse[] newArray(int size) {
            return new OutlookResponse[size];
        }
    };
    @SerializedName("@odata.context")
    private String oDataContext;
    @SerializedName("value")
    private List<OutlookDetail> values;

    public OutlookResponse() {
    }

    protected OutlookResponse(Parcel in) {
        this.oDataContext = in.readString();
        this.values = in.createTypedArrayList(OutlookDetail.CREATOR);
    }

    public String getoDataContext() {
        return oDataContext;
    }

    public void setoDataContext(String oDataContext) {
        this.oDataContext = oDataContext;
    }

    public List<OutlookDetail> getValues() {
        return values;
    }

    public void setValues(List<OutlookDetail> values) {
        this.values = values;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.oDataContext);
        dest.writeTypedList(this.values);
    }
}
