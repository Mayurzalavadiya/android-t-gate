package com.tgate.gate.apiResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class getGuardNotificationResponse {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }
    public class Datum {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("notification_type")
        @Expose
        private String notificationType;
        @SerializedName("from_userid")
        @Expose
        private String fromUserid;
        @SerializedName("to_userid")
        @Expose
        private String toUserid;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("body")
        @Expose
        private String body;
        @SerializedName("additional_data")
        @Expose
        private AdditionalData additionalData;
        @SerializedName("is_read")
        @Expose
        private String isRead;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private Object updatedAt;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNotificationType() {
            return notificationType;
        }

        public void setNotificationType(String notificationType) {
            this.notificationType = notificationType;
        }

        public String getFromUserid() {
            return fromUserid;
        }

        public void setFromUserid(String fromUserid) {
            this.fromUserid = fromUserid;
        }

        public String getToUserid() {
            return toUserid;
        }

        public void setToUserid(String toUserid) {
            this.toUserid = toUserid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public AdditionalData getAdditionalData() {
            return additionalData;
        }

        public void setAdditionalData(AdditionalData additionalData) {
            this.additionalData = additionalData;
        }

        public String getIsRead() {
            return isRead;
        }

        public void setIsRead(String isRead) {
            this.isRead = isRead;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public Object getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(Object updatedAt) {
            this.updatedAt = updatedAt;
        }

    }
    public class AdditionalData {

        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("body")
        @Expose
        private String body;
        @SerializedName("sound")
        @Expose
        private String sound;
        @SerializedName("type")
        @Expose
        private Integer type;
        @SerializedName("notification_type")
        @Expose
        private String notificationType;
        @SerializedName("from_userid")
        @Expose
        private String fromUserid;
        @SerializedName("to_userid")
        @Expose
        private String toUserid;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getSound() {
            return sound;
        }

        public void setSound(String sound) {
            this.sound = sound;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public String getNotificationType() {
            return notificationType;
        }

        public void setNotificationType(String notificationType) {
            this.notificationType = notificationType;
        }

        public String getFromUserid() {
            return fromUserid;
        }

        public void setFromUserid(String fromUserid) {
            this.fromUserid = fromUserid;
        }

        public String getToUserid() {
            return toUserid;
        }

        public void setToUserid(String toUserid) {
            this.toUserid = toUserid;
        }

    }
}


