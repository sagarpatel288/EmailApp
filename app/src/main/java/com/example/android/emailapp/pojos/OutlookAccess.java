package com.example.android.emailapp.pojos;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OutlookAccess implements Parcelable {

    public final static Parcelable.Creator<OutlookAccess> CREATOR = new Creator<OutlookAccess>() {


        @SuppressWarnings({
                "unchecked"
        })
        public OutlookAccess createFromParcel(Parcel in) {
            return new OutlookAccess(in);
        }

        public OutlookAccess[] newArray(int size) {
            return (new OutlookAccess[size]);
        }

    };
    @SerializedName("token_type")
    @Expose
    private String tokenType;
    @SerializedName("expires_in")
    @Expose
    private String expiresIn;
    @SerializedName("access_token")
    @Expose
    private String accessToken;
    @SerializedName("scope")
    @Expose
    private String scope;

    protected OutlookAccess(Parcel in) {
        this.tokenType = ((String) in.readValue((String.class.getClassLoader())));
        this.expiresIn = ((String) in.readValue((String.class.getClassLoader())));
        this.accessToken = ((String) in.readValue((String.class.getClassLoader())));
        this.scope = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public OutlookAccess() {
    }

    /**
     * @param scope
     * @param tokenType
     * @param accessToken
     * @param expiresIn
     */
    public OutlookAccess(String tokenType, String expiresIn, String accessToken, String scope) {
        super();
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
        this.accessToken = accessToken;
        this.scope = scope;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(tokenType);
        dest.writeValue(expiresIn);
        dest.writeValue(accessToken);
        dest.writeValue(scope);
    }

}