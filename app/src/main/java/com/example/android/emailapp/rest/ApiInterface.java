package com.example.android.emailapp.rest;


import com.example.android.emailapp.constants.AppApi;
import com.example.android.emailapp.constants.AppUrls;
import com.example.android.emailapp.constants.JsonKeys;
import com.example.android.emailapp.pojos.OutlookAccess;
import com.example.android.emailapp.pojos.OutlookResponse;
import com.google.gson.JsonObject;
import com.squareup.okhttp.ResponseBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    /*https://futurestud.io/tutorials/retrofit-add-custom-request-header*/
    /*@Headers("Cache-Control: max-age=640000")*/
    /*@Headers({
        "Accept: application/vnd.yourapi.v1.full+json",
        "User-Agent: Your-App-Name"
    })*/
    /*Object yourMethodName(@Header("Content-Type") String contentType, @Body JsonObject postData);*/

    /*headers.put("Authorization", "Bearer " + authResult.getAccessToken());*/
    @GET(AppApi.MS_OUTLOOK_GRAPH_API)
    Call<OutlookResponse> getAllMessages(@Header("Authorization") String bearerPlusToken);

    @FormUrlEncoded
    @POST("/your_endpoint")
    Object yourMethodName(@Body JsonObject postData);

    @POST("/your_endpoint")
    Object yourMethodName(@Header("Content-Type") String contentType, @Body JsonObject postData);

    /*POST https://login.microsoftonline.com/common/oauth2/v2.0/token
Content-Type: application/x-www-form-urlencoded
grant_type=authorization_code
&code=AwABAAAA...cZZ6IgAA
&redirect_uri=http%3A%2F%2Flocalhost%2Fmyapp%2F
&client_id=<CLIENT ID>
&client_secret=<CLIENT SECRET>*/
    @FormUrlEncoded
    @POST(AppApi.API_OUTLOOK_TOKEN)
    Call<OutlookAccess> getOutLookAccesToken(@Field(JsonKeys.GRANT_TYPE) String grantType,
                                             @Field(JsonKeys.CODE) String authCode,
                                             @Field(JsonKeys.REDIRECT_URI) String redirectUri,
                                             @Field(JsonKeys.CLIENT_ID) String clientId,
                                             @Field(JsonKeys.CLIENT_SECRET) String clientSecret);

    /*DELETE https://graph.microsoft.com/v1.0/me/messages/{id}*/
    /* // delete Gist
      @DELETE("gists/{id}")
    Call<ResponseBody> deleteGist(@Path("id") String id);*/
    @DELETE(AppUrls.OutlookUrls.DELETE_EMAIL)
    Call<ResponseBody> deleteOutlookEmail(@Header("Authorization") String bearerPlusToken, @Path("id") String emailId);

    /*POST /me/sendMail
POST /users/{id | userPrincipalName}/sendMail*/
    /*Header	Value
Authorization	Bearer {token}. Required.
Content-Type	application/json*/
    @POST(AppUrls.OutlookUrls.SEND_EMAIL)
    Call<ResponseBody> sendEmail(@Header("Authorization") String bearerPlusToken, @Body JsonObject message);
}
