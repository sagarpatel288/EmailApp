package com.example.android.emailapp.constants;

public final class AppApi {
    public static final String OUTLOOK_TOKEN = "token";
    public static final String API_OUTLOOK_TOKEN = "https://login.microsoftonline.com/common/oauth2/v2.0/token";
    public static final String MS_OUTLOOK_GRAPH_API = "https://graph.microsoft.com/v1.0/me/messages";
    public static final String MS_OFFICE_OUTLOOK_GRAPH_API = "https://outlook.office.com/api/v2.0/me/messages";
    public static final String MS_GRAPH_URL = "https://graph.microsoft.com/v2.0/me/messages";
    public static final String OUTLOOK_OFFICE_BASE_URL = "https://outlook.office.com/api/v2.0";
    public static final String REDIRECT_URI = "https://www.google.com";

    private AppApi() {
    }
}
