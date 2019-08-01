package com.example.android.emailapp.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OutlookDetail implements Parcelable {

    public final static Parcelable.Creator<OutlookDetail> CREATOR = new Creator<OutlookDetail>() {


        @SuppressWarnings({
                "unchecked"
        })
        public OutlookDetail createFromParcel(Parcel in) {
            return new OutlookDetail(in);
        }

        public OutlookDetail[] newArray(int size) {
            return (new OutlookDetail[size]);
        }

    };
    @SerializedName("@odata.etag")
    @Expose
    private String odataEtag;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("createdDateTime")
    @Expose
    private String createdDateTime;
    @SerializedName("lastModifiedDateTime")
    @Expose
    private String lastModifiedDateTime;
    @SerializedName("changeKey")
    @Expose
    private String changeKey;
    @SerializedName("categories")
    @Expose
    private List<Object> categories = null;
    @SerializedName("receivedDateTime")
    @Expose
    private String receivedDateTime;
    @SerializedName("sentDateTime")
    @Expose
    private String sentDateTime;
    @SerializedName("hasAttachments")
    @Expose
    private boolean hasAttachments;
    @SerializedName("internetMessageId")
    @Expose
    private Object internetMessageId;
    @SerializedName("subject")
    @Expose
    private Object subject;
    @SerializedName("bodyPreview")
    @Expose
    private String bodyPreview;
    @SerializedName("importance")
    @Expose
    private String importance;
    @SerializedName("parentFolderId")
    @Expose
    private String parentFolderId;
    @SerializedName("conversationId")
    @Expose
    private String conversationId;
    @SerializedName("isDeliveryReceiptRequested")
    @Expose
    private Object isDeliveryReceiptRequested;
    @SerializedName("isReadReceiptRequested")
    @Expose
    private Object isReadReceiptRequested;
    @SerializedName("isRead")
    @Expose
    private Object isRead;
    @SerializedName("isDraft")
    @Expose
    private boolean isDraft;
    @SerializedName("webLink")
    @Expose
    private String webLink;
    @SerializedName("inferenceClassification")
    @Expose
    private String inferenceClassification;
    @SerializedName("outlookEmailBody")
    @Expose
    private OutlookEmailBody outlookEmailBody;
    @SerializedName("toRecipients")
    @Expose
    private List<Object> toRecipients = null;
    @SerializedName("ccRecipients")
    @Expose
    private List<Object> ccRecipients = null;
    @SerializedName("bccRecipients")
    @Expose
    private List<Object> bccRecipients = null;
    @SerializedName("replyTo")
    @Expose
    private List<Object> replyTo = null;
    @SerializedName("outlookFlag")
    @Expose
    private OutlookFlag outlookFlag;

    protected OutlookDetail(Parcel in) {
        this.odataEtag = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.createdDateTime = ((String) in.readValue((String.class.getClassLoader())));
        this.lastModifiedDateTime = ((String) in.readValue((String.class.getClassLoader())));
        this.changeKey = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.categories, (java.lang.Object.class.getClassLoader()));
        this.receivedDateTime = ((String) in.readValue((String.class.getClassLoader())));
        this.sentDateTime = ((String) in.readValue((String.class.getClassLoader())));
        this.hasAttachments = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.internetMessageId = ((Object) in.readValue((Object.class.getClassLoader())));
        this.subject = ((Object) in.readValue((Object.class.getClassLoader())));
        this.bodyPreview = ((String) in.readValue((String.class.getClassLoader())));
        this.importance = ((String) in.readValue((String.class.getClassLoader())));
        this.parentFolderId = ((String) in.readValue((String.class.getClassLoader())));
        this.conversationId = ((String) in.readValue((String.class.getClassLoader())));
        this.isDeliveryReceiptRequested = ((Object) in.readValue((Object.class.getClassLoader())));
        this.isReadReceiptRequested = ((Object) in.readValue((Object.class.getClassLoader())));
        this.isRead = ((Object) in.readValue((Object.class.getClassLoader())));
        this.isDraft = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.webLink = ((String) in.readValue((String.class.getClassLoader())));
        this.inferenceClassification = ((String) in.readValue((String.class.getClassLoader())));
        this.outlookEmailBody = ((OutlookEmailBody) in.readValue((OutlookEmailBody.class.getClassLoader())));
        in.readList(this.toRecipients, (java.lang.Object.class.getClassLoader()));
        in.readList(this.ccRecipients, (java.lang.Object.class.getClassLoader()));
        in.readList(this.bccRecipients, (java.lang.Object.class.getClassLoader()));
        in.readList(this.replyTo, (java.lang.Object.class.getClassLoader()));
        this.outlookFlag = ((OutlookFlag) in.readValue((OutlookFlag.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public OutlookDetail() {
    }

    /**
     * @param outlookEmailBody
     * @param subject
     * @param sentDateTime
     * @param isReadReceiptRequested
     * @param conversationId
     * @param webLink
     * @param lastModifiedDateTime
     * @param id
     * @param isDraft
     * @param receivedDateTime
     * @param isDeliveryReceiptRequested
     * @param bccRecipients
     * @param changeKey
     * @param parentFolderId
     * @param ccRecipients
     * @param odataEtag
     * @param internetMessageId
     * @param outlookFlag
     * @param replyTo
     * @param importance
     * @param hasAttachments
     * @param createdDateTime
     * @param categories
     * @param inferenceClassification
     * @param toRecipients
     * @param bodyPreview
     * @param isRead
     */
    public OutlookDetail(String odataEtag, String id, String createdDateTime, String lastModifiedDateTime, String changeKey, List<Object> categories, String receivedDateTime, String sentDateTime, boolean hasAttachments, Object internetMessageId, Object subject, String bodyPreview, String importance, String parentFolderId, String conversationId, Object isDeliveryReceiptRequested, Object isReadReceiptRequested, Object isRead, boolean isDraft, String webLink, String inferenceClassification, OutlookEmailBody outlookEmailBody, List<Object> toRecipients, List<Object> ccRecipients, List<Object> bccRecipients, List<Object> replyTo, OutlookFlag outlookFlag) {
        super();
        this.odataEtag = odataEtag;
        this.id = id;
        this.createdDateTime = createdDateTime;
        this.lastModifiedDateTime = lastModifiedDateTime;
        this.changeKey = changeKey;
        this.categories = categories;
        this.receivedDateTime = receivedDateTime;
        this.sentDateTime = sentDateTime;
        this.hasAttachments = hasAttachments;
        this.internetMessageId = internetMessageId;
        this.subject = subject;
        this.bodyPreview = bodyPreview;
        this.importance = importance;
        this.parentFolderId = parentFolderId;
        this.conversationId = conversationId;
        this.isDeliveryReceiptRequested = isDeliveryReceiptRequested;
        this.isReadReceiptRequested = isReadReceiptRequested;
        this.isRead = isRead;
        this.isDraft = isDraft;
        this.webLink = webLink;
        this.inferenceClassification = inferenceClassification;
        this.outlookEmailBody = outlookEmailBody;
        this.toRecipients = toRecipients;
        this.ccRecipients = ccRecipients;
        this.bccRecipients = bccRecipients;
        this.replyTo = replyTo;
        this.outlookFlag = outlookFlag;
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

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    public void setLastModifiedDateTime(String lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public String getChangeKey() {
        return changeKey;
    }

    public void setChangeKey(String changeKey) {
        this.changeKey = changeKey;
    }

    public List<Object> getCategories() {
        return categories;
    }

    public void setCategories(List<Object> categories) {
        this.categories = categories;
    }

    public String getReceivedDateTime() {
        return receivedDateTime;
    }

    public void setReceivedDateTime(String receivedDateTime) {
        this.receivedDateTime = receivedDateTime;
    }

    public String getSentDateTime() {
        return sentDateTime;
    }

    public void setSentDateTime(String sentDateTime) {
        this.sentDateTime = sentDateTime;
    }

    public boolean isHasAttachments() {
        return hasAttachments;
    }

    public void setHasAttachments(boolean hasAttachments) {
        this.hasAttachments = hasAttachments;
    }

    public Object getInternetMessageId() {
        return internetMessageId;
    }

    public void setInternetMessageId(Object internetMessageId) {
        this.internetMessageId = internetMessageId;
    }

    public Object getSubject() {
        return subject;
    }

    public void setSubject(Object subject) {
        this.subject = subject;
    }

    public String getBodyPreview() {
        return bodyPreview;
    }

    public void setBodyPreview(String bodyPreview) {
        this.bodyPreview = bodyPreview;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    public String getParentFolderId() {
        return parentFolderId;
    }

    public void setParentFolderId(String parentFolderId) {
        this.parentFolderId = parentFolderId;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public Object getIsDeliveryReceiptRequested() {
        return isDeliveryReceiptRequested;
    }

    public void setIsDeliveryReceiptRequested(Object isDeliveryReceiptRequested) {
        this.isDeliveryReceiptRequested = isDeliveryReceiptRequested;
    }

    public Object getIsReadReceiptRequested() {
        return isReadReceiptRequested;
    }

    public void setIsReadReceiptRequested(Object isReadReceiptRequested) {
        this.isReadReceiptRequested = isReadReceiptRequested;
    }

    public Object getIsRead() {
        return isRead;
    }

    public void setIsRead(Object isRead) {
        this.isRead = isRead;
    }

    public boolean isIsDraft() {
        return isDraft;
    }

    public void setIsDraft(boolean isDraft) {
        this.isDraft = isDraft;
    }

    public String getWebLink() {
        return webLink;
    }

    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }

    public String getInferenceClassification() {
        return inferenceClassification;
    }

    public void setInferenceClassification(String inferenceClassification) {
        this.inferenceClassification = inferenceClassification;
    }

    public OutlookEmailBody getOutlookEmailBody() {
        return outlookEmailBody;
    }

    public void setOutlookEmailBody(OutlookEmailBody outlookEmailBody) {
        this.outlookEmailBody = outlookEmailBody;
    }

    public List<Object> getToRecipients() {
        return toRecipients;
    }

    public void setToRecipients(List<Object> toRecipients) {
        this.toRecipients = toRecipients;
    }

    public List<Object> getCcRecipients() {
        return ccRecipients;
    }

    public void setCcRecipients(List<Object> ccRecipients) {
        this.ccRecipients = ccRecipients;
    }

    public List<Object> getBccRecipients() {
        return bccRecipients;
    }

    public void setBccRecipients(List<Object> bccRecipients) {
        this.bccRecipients = bccRecipients;
    }

    public List<Object> getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(List<Object> replyTo) {
        this.replyTo = replyTo;
    }

    public OutlookFlag getOutlookFlag() {
        return outlookFlag;
    }

    public void setOutlookFlag(OutlookFlag outlookFlag) {
        this.outlookFlag = outlookFlag;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(odataEtag);
        dest.writeValue(id);
        dest.writeValue(createdDateTime);
        dest.writeValue(lastModifiedDateTime);
        dest.writeValue(changeKey);
        dest.writeList(categories);
        dest.writeValue(receivedDateTime);
        dest.writeValue(sentDateTime);
        dest.writeValue(hasAttachments);
        dest.writeValue(internetMessageId);
        dest.writeValue(subject);
        dest.writeValue(bodyPreview);
        dest.writeValue(importance);
        dest.writeValue(parentFolderId);
        dest.writeValue(conversationId);
        dest.writeValue(isDeliveryReceiptRequested);
        dest.writeValue(isReadReceiptRequested);
        dest.writeValue(isRead);
        dest.writeValue(isDraft);
        dest.writeValue(webLink);
        dest.writeValue(inferenceClassification);
        dest.writeValue(outlookEmailBody);
        dest.writeList(toRecipients);
        dest.writeList(ccRecipients);
        dest.writeList(bccRecipients);
        dest.writeList(replyTo);
        dest.writeValue(outlookFlag);
    }

}