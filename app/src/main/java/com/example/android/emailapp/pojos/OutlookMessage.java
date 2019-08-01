package com.example.android.emailapp.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.microsoft.services.outlook.Attachment;

import java.util.List;

public class OutlookMessage implements Parcelable {

    public final static Parcelable.Creator<OutlookMessage> CREATOR = new Creator<OutlookMessage>() {


        @SuppressWarnings({
                "unchecked"
        })
        public OutlookMessage createFromParcel(Parcel in) {
            return new OutlookMessage(in);
        }

        public OutlookMessage[] newArray(int size) {
            return (new OutlookMessage[size]);
        }

    };
    @SerializedName("bccRecipients")
    @Expose
    private List<BccRecipient> bccRecipients = null;
    @SerializedName("body")
    @Expose
    private Body body;
    @SerializedName("bodyPreview")
    @Expose
    private String bodyPreview;
    @SerializedName("categories")
    @Expose
    private List<String> categories = null;
    @SerializedName("ccRecipients")
    @Expose
    private List<CcRecipient> ccRecipients = null;
    @SerializedName("changeKey")
    @Expose
    private String changeKey;
    @SerializedName("conversationId")
    @Expose
    private String conversationId;
    @SerializedName("createdDateTime")
    @Expose
    private String createdDateTime;
    @SerializedName("flag")
    @Expose
    private Flag flag;
    @SerializedName("from")
    @Expose
    private From from;
    @SerializedName("hasAttachments")
    @Expose
    private boolean hasAttachments;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("importance")
    @Expose
    private String importance;
    @SerializedName("inferenceClassification")
    @Expose
    private String inferenceClassification;
    @SerializedName("internetMessageHeaders")
    @Expose
    private List<InternetMessageHeader> internetMessageHeaders = null;
    @SerializedName("internetMessageId")
    @Expose
    private String internetMessageId;
    @SerializedName("isDeliveryReceiptRequested")
    @Expose
    private boolean isDeliveryReceiptRequested;
    @SerializedName("isDraft")
    @Expose
    private boolean isDraft;
    @SerializedName("isRead")
    @Expose
    private boolean isRead;
    @SerializedName("isReadReceiptRequested")
    @Expose
    private boolean isReadReceiptRequested;
    @SerializedName("lastModifiedDateTime")
    @Expose
    private String lastModifiedDateTime;
    @SerializedName("parentFolderId")
    @Expose
    private String parentFolderId;
    @SerializedName("receivedDateTime")
    @Expose
    private String receivedDateTime;
    @SerializedName("replyTo")
    @Expose
    private List<ReplyTo> replyTo = null;
    @SerializedName("sender")
    @Expose
    private Sender sender;
    @SerializedName("sentDateTime")
    @Expose
    private String sentDateTime;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("toRecipients")
    @Expose
    private List<ToRecipient> toRecipients = null;
    @SerializedName("uniqueBody")
    @Expose
    private UniqueBody uniqueBody;
    @SerializedName("webLink")
    @Expose
    private String webLink;
    @SerializedName("attachments")
    @Expose
    private List<Attachment> attachments = null;
    @SerializedName("extensions")
    @Expose
    private List<Extension> extensions = null;
    @SerializedName("multiValueExtendedProperties")
    @Expose
    private List<MultiValueExtendedProperty> multiValueExtendedProperties = null;
    @SerializedName("singleValueExtendedProperties")
    @Expose
    private List<SingleValueExtendedProperty> singleValueExtendedProperties = null;

    protected OutlookMessage(Parcel in) {
        in.readList(this.bccRecipients, (com.example.android.emailapp.pojos.BccRecipient.class.getClassLoader()));
        this.body = ((Body) in.readValue((Body.class.getClassLoader())));
        this.bodyPreview = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.categories, (java.lang.String.class.getClassLoader()));
        in.readList(this.ccRecipients, (com.example.android.emailapp.pojos.CcRecipient.class.getClassLoader()));
        this.changeKey = ((String) in.readValue((String.class.getClassLoader())));
        this.conversationId = ((String) in.readValue((String.class.getClassLoader())));
        this.createdDateTime = ((String) in.readValue((String.class.getClassLoader())));
        this.flag = ((Flag) in.readValue((Flag.class.getClassLoader())));
        this.from = ((From) in.readValue((From.class.getClassLoader())));
        this.hasAttachments = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.importance = ((String) in.readValue((String.class.getClassLoader())));
        this.inferenceClassification = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.internetMessageHeaders, (com.example.android.emailapp.pojos.InternetMessageHeader.class.getClassLoader()));
        this.internetMessageId = ((String) in.readValue((String.class.getClassLoader())));
        this.isDeliveryReceiptRequested = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.isDraft = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.isRead = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.isReadReceiptRequested = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.lastModifiedDateTime = ((String) in.readValue((String.class.getClassLoader())));
        this.parentFolderId = ((String) in.readValue((String.class.getClassLoader())));
        this.receivedDateTime = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.replyTo, (com.example.android.emailapp.pojos.ReplyTo.class.getClassLoader()));
        this.sender = ((Sender) in.readValue((Sender.class.getClassLoader())));
        this.sentDateTime = ((String) in.readValue((String.class.getClassLoader())));
        this.subject = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.toRecipients, (com.example.android.emailapp.pojos.ToRecipient.class.getClassLoader()));
        this.uniqueBody = ((UniqueBody) in.readValue((UniqueBody.class.getClassLoader())));
        this.webLink = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.attachments, (com.example.android.emailapp.pojos.Attachment.class.getClassLoader()));
        in.readList(this.extensions, (com.example.android.emailapp.pojos.Extension.class.getClassLoader()));
        in.readList(this.multiValueExtendedProperties, (com.example.android.emailapp.pojos.MultiValueExtendedProperty.class.getClassLoader()));
        in.readList(this.singleValueExtendedProperties, (com.example.android.emailapp.pojos.SingleValueExtendedProperty.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     */
    public OutlookMessage() {
    }

    /**
     * @param body
     * @param subject
     * @param sentDateTime
     * @param isReadReceiptRequested
     * @param conversationId
     * @param webLink
     * @param from
     * @param lastModifiedDateTime
     * @param extensions
     * @param sender
     * @param id
     * @param isDraft
     * @param multiValueExtendedProperties
     * @param receivedDateTime
     * @param attachments
     * @param internetMessageHeaders
     * @param isDeliveryReceiptRequested
     * @param bccRecipients
     * @param changeKey
     * @param uniqueBody
     * @param parentFolderId
     * @param ccRecipients
     * @param internetMessageId
     * @param singleValueExtendedProperties
     * @param flag
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
    public OutlookMessage(List<BccRecipient> bccRecipients, Body body, String bodyPreview, List<String> categories, List<CcRecipient> ccRecipients, String changeKey, String conversationId, String createdDateTime, Flag flag, From from, boolean hasAttachments, String id, String importance, String inferenceClassification, List<InternetMessageHeader> internetMessageHeaders, String internetMessageId, boolean isDeliveryReceiptRequested, boolean isDraft, boolean isRead, boolean isReadReceiptRequested, String lastModifiedDateTime, String parentFolderId, String receivedDateTime, List<ReplyTo> replyTo, Sender sender, String sentDateTime, String subject, List<ToRecipient> toRecipients, UniqueBody uniqueBody, String webLink, List<Attachment> attachments, List<Extension> extensions, List<MultiValueExtendedProperty> multiValueExtendedProperties, List<SingleValueExtendedProperty> singleValueExtendedProperties) {
        super();
        this.bccRecipients = bccRecipients;
        this.body = body;
        this.bodyPreview = bodyPreview;
        this.categories = categories;
        this.ccRecipients = ccRecipients;
        this.changeKey = changeKey;
        this.conversationId = conversationId;
        this.createdDateTime = createdDateTime;
        this.flag = flag;
        this.from = from;
        this.hasAttachments = hasAttachments;
        this.id = id;
        this.importance = importance;
        this.inferenceClassification = inferenceClassification;
        this.internetMessageHeaders = internetMessageHeaders;
        this.internetMessageId = internetMessageId;
        this.isDeliveryReceiptRequested = isDeliveryReceiptRequested;
        this.isDraft = isDraft;
        this.isRead = isRead;
        this.isReadReceiptRequested = isReadReceiptRequested;
        this.lastModifiedDateTime = lastModifiedDateTime;
        this.parentFolderId = parentFolderId;
        this.receivedDateTime = receivedDateTime;
        this.replyTo = replyTo;
        this.sender = sender;
        this.sentDateTime = sentDateTime;
        this.subject = subject;
        this.toRecipients = toRecipients;
        this.uniqueBody = uniqueBody;
        this.webLink = webLink;
        this.attachments = attachments;
        this.extensions = extensions;
        this.multiValueExtendedProperties = multiValueExtendedProperties;
        this.singleValueExtendedProperties = singleValueExtendedProperties;
    }

    public List<BccRecipient> getBccRecipients() {
        return bccRecipients;
    }

    public void setBccRecipients(List<BccRecipient> bccRecipients) {
        this.bccRecipients = bccRecipients;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public String getBodyPreview() {
        return bodyPreview;
    }

    public void setBodyPreview(String bodyPreview) {
        this.bodyPreview = bodyPreview;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<CcRecipient> getCcRecipients() {
        return ccRecipients;
    }

    public void setCcRecipients(List<CcRecipient> ccRecipients) {
        this.ccRecipients = ccRecipients;
    }

    public String getChangeKey() {
        return changeKey;
    }

    public void setChangeKey(String changeKey) {
        this.changeKey = changeKey;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public Flag getFlag() {
        return flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    public From getFrom() {
        return from;
    }

    public void setFrom(From from) {
        this.from = from;
    }

    public boolean isHasAttachments() {
        return hasAttachments;
    }

    public void setHasAttachments(boolean hasAttachments) {
        this.hasAttachments = hasAttachments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    public String getInferenceClassification() {
        return inferenceClassification;
    }

    public void setInferenceClassification(String inferenceClassification) {
        this.inferenceClassification = inferenceClassification;
    }

    public List<InternetMessageHeader> getInternetMessageHeaders() {
        return internetMessageHeaders;
    }

    public void setInternetMessageHeaders(List<InternetMessageHeader> internetMessageHeaders) {
        this.internetMessageHeaders = internetMessageHeaders;
    }

    public String getInternetMessageId() {
        return internetMessageId;
    }

    public void setInternetMessageId(String internetMessageId) {
        this.internetMessageId = internetMessageId;
    }

    public boolean isIsDeliveryReceiptRequested() {
        return isDeliveryReceiptRequested;
    }

    public void setIsDeliveryReceiptRequested(boolean isDeliveryReceiptRequested) {
        this.isDeliveryReceiptRequested = isDeliveryReceiptRequested;
    }

    public boolean isIsDraft() {
        return isDraft;
    }

    public void setIsDraft(boolean isDraft) {
        this.isDraft = isDraft;
    }

    public boolean isIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public boolean isIsReadReceiptRequested() {
        return isReadReceiptRequested;
    }

    public void setIsReadReceiptRequested(boolean isReadReceiptRequested) {
        this.isReadReceiptRequested = isReadReceiptRequested;
    }

    public String getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    public void setLastModifiedDateTime(String lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public String getParentFolderId() {
        return parentFolderId;
    }

    public void setParentFolderId(String parentFolderId) {
        this.parentFolderId = parentFolderId;
    }

    public String getReceivedDateTime() {
        return receivedDateTime;
    }

    public void setReceivedDateTime(String receivedDateTime) {
        this.receivedDateTime = receivedDateTime;
    }

    public List<ReplyTo> getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(List<ReplyTo> replyTo) {
        this.replyTo = replyTo;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public String getSentDateTime() {
        return sentDateTime;
    }

    public void setSentDateTime(String sentDateTime) {
        this.sentDateTime = sentDateTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<ToRecipient> getToRecipients() {
        return toRecipients;
    }

    public void setToRecipients(List<ToRecipient> toRecipients) {
        this.toRecipients = toRecipients;
    }

    public UniqueBody getUniqueBody() {
        return uniqueBody;
    }

    public void setUniqueBody(UniqueBody uniqueBody) {
        this.uniqueBody = uniqueBody;
    }

    public String getWebLink() {
        return webLink;
    }

    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public List<Extension> getExtensions() {
        return extensions;
    }

    public void setExtensions(List<Extension> extensions) {
        this.extensions = extensions;
    }

    public List<MultiValueExtendedProperty> getMultiValueExtendedProperties() {
        return multiValueExtendedProperties;
    }

    public void setMultiValueExtendedProperties(List<MultiValueExtendedProperty> multiValueExtendedProperties) {
        this.multiValueExtendedProperties = multiValueExtendedProperties;
    }

    public List<SingleValueExtendedProperty> getSingleValueExtendedProperties() {
        return singleValueExtendedProperties;
    }

    public void setSingleValueExtendedProperties(List<SingleValueExtendedProperty> singleValueExtendedProperties) {
        this.singleValueExtendedProperties = singleValueExtendedProperties;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(bccRecipients);
        dest.writeValue(body);
        dest.writeValue(bodyPreview);
        dest.writeList(categories);
        dest.writeList(ccRecipients);
        dest.writeValue(changeKey);
        dest.writeValue(conversationId);
        dest.writeValue(createdDateTime);
        dest.writeValue(flag);
        dest.writeValue(from);
        dest.writeValue(hasAttachments);
        dest.writeValue(id);
        dest.writeValue(importance);
        dest.writeValue(inferenceClassification);
        dest.writeList(internetMessageHeaders);
        dest.writeValue(internetMessageId);
        dest.writeValue(isDeliveryReceiptRequested);
        dest.writeValue(isDraft);
        dest.writeValue(isRead);
        dest.writeValue(isReadReceiptRequested);
        dest.writeValue(lastModifiedDateTime);
        dest.writeValue(parentFolderId);
        dest.writeValue(receivedDateTime);
        dest.writeList(replyTo);
        dest.writeValue(sender);
        dest.writeValue(sentDateTime);
        dest.writeValue(subject);
        dest.writeList(toRecipients);
        dest.writeValue(uniqueBody);
        dest.writeValue(webLink);
        dest.writeList(attachments);
        dest.writeList(extensions);
        dest.writeList(multiValueExtendedProperties);
        dest.writeList(singleValueExtendedProperties);
    }

}

