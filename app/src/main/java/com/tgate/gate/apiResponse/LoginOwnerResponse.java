package com.tgate.gate.apiResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginOwnerResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("userid")
        @Expose
        private String userid;
        @SerializedName("is_primary")
        @Expose
        private String isPrimary;
        @SerializedName("firstname")
        @Expose
        private String firstname;
        @SerializedName("lastname")
        @Expose
        private String lastname;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("phonenumber")
        @Expose
        private String phonenumber;
        @SerializedName("title")
        @Expose
        private Object title;
        @SerializedName("datecreated")
        @Expose
        private String datecreated;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("new_pass_key")
        @Expose
        private Object newPassKey;
        @SerializedName("new_pass_key_requested")
        @Expose
        private Object newPassKeyRequested;
        @SerializedName("email_verified_at")
        @Expose
        private String emailVerifiedAt;
        @SerializedName("email_verification_key")
        @Expose
        private Object emailVerificationKey;
        @SerializedName("email_verification_sent_at")
        @Expose
        private Object emailVerificationSentAt;
        @SerializedName("last_ip")
        @Expose
        private String lastIp;
        @SerializedName("last_login")
        @Expose
        private String lastLogin;
        @SerializedName("last_password_change")
        @Expose
        private String lastPasswordChange;
        @SerializedName("active")
        @Expose
        private String active;
        @SerializedName("profile_image")
        @Expose
        private Object profileImage;
        @SerializedName("direction")
        @Expose
        private Object direction;
        @SerializedName("invoice_emails")
        @Expose
        private String invoiceEmails;
        @SerializedName("estimate_emails")
        @Expose
        private String estimateEmails;
        @SerializedName("credit_note_emails")
        @Expose
        private String creditNoteEmails;
        @SerializedName("contract_emails")
        @Expose
        private String contractEmails;
        @SerializedName("task_emails")
        @Expose
        private String taskEmails;
        @SerializedName("project_emails")
        @Expose
        private String projectEmails;
        @SerializedName("ticket_emails")
        @Expose
        private String ticketEmails;
        @SerializedName("fcm_token")
        @Expose
        private String fcmToken;
        @SerializedName("device_model")
        @Expose
        private String deviceModel;
        @SerializedName("device_osversion")
        @Expose
        private String deviceOsversion;
        @SerializedName("app_version")
        @Expose
        private String appVersion;
        @SerializedName("device_type")
        @Expose
        private String deviceType;
        @SerializedName("customfields")
        @Expose
        private List<Object> customfields = null;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getIsPrimary() {
            return isPrimary;
        }

        public void setIsPrimary(String isPrimary) {
            this.isPrimary = isPrimary;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhonenumber() {
            return phonenumber;
        }

        public void setPhonenumber(String phonenumber) {
            this.phonenumber = phonenumber;
        }

        public Object getTitle() {
            return title;
        }

        public void setTitle(Object title) {
            this.title = title;
        }

        public String getDatecreated() {
            return datecreated;
        }

        public void setDatecreated(String datecreated) {
            this.datecreated = datecreated;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Object getNewPassKey() {
            return newPassKey;
        }

        public void setNewPassKey(Object newPassKey) {
            this.newPassKey = newPassKey;
        }

        public Object getNewPassKeyRequested() {
            return newPassKeyRequested;
        }

        public void setNewPassKeyRequested(Object newPassKeyRequested) {
            this.newPassKeyRequested = newPassKeyRequested;
        }

        public String getEmailVerifiedAt() {
            return emailVerifiedAt;
        }

        public void setEmailVerifiedAt(String emailVerifiedAt) {
            this.emailVerifiedAt = emailVerifiedAt;
        }

        public Object getEmailVerificationKey() {
            return emailVerificationKey;
        }

        public void setEmailVerificationKey(Object emailVerificationKey) {
            this.emailVerificationKey = emailVerificationKey;
        }

        public Object getEmailVerificationSentAt() {
            return emailVerificationSentAt;
        }

        public void setEmailVerificationSentAt(Object emailVerificationSentAt) {
            this.emailVerificationSentAt = emailVerificationSentAt;
        }

        public String getLastIp() {
            return lastIp;
        }

        public void setLastIp(String lastIp) {
            this.lastIp = lastIp;
        }

        public String getLastLogin() {
            return lastLogin;
        }

        public void setLastLogin(String lastLogin) {
            this.lastLogin = lastLogin;
        }

        public String getLastPasswordChange() {
            return lastPasswordChange;
        }

        public void setLastPasswordChange(String lastPasswordChange) {
            this.lastPasswordChange = lastPasswordChange;
        }

        public String getActive() {
            return active;
        }

        public void setActive(String active) {
            this.active = active;
        }

        public Object getProfileImage() {
            return profileImage;
        }

        public void setProfileImage(Object profileImage) {
            this.profileImage = profileImage;
        }

        public Object getDirection() {
            return direction;
        }

        public void setDirection(Object direction) {
            this.direction = direction;
        }

        public String getInvoiceEmails() {
            return invoiceEmails;
        }

        public void setInvoiceEmails(String invoiceEmails) {
            this.invoiceEmails = invoiceEmails;
        }

        public String getEstimateEmails() {
            return estimateEmails;
        }

        public void setEstimateEmails(String estimateEmails) {
            this.estimateEmails = estimateEmails;
        }

        public String getCreditNoteEmails() {
            return creditNoteEmails;
        }

        public void setCreditNoteEmails(String creditNoteEmails) {
            this.creditNoteEmails = creditNoteEmails;
        }

        public String getContractEmails() {
            return contractEmails;
        }

        public void setContractEmails(String contractEmails) {
            this.contractEmails = contractEmails;
        }

        public String getTaskEmails() {
            return taskEmails;
        }

        public void setTaskEmails(String taskEmails) {
            this.taskEmails = taskEmails;
        }

        public String getProjectEmails() {
            return projectEmails;
        }

        public void setProjectEmails(String projectEmails) {
            this.projectEmails = projectEmails;
        }

        public String getTicketEmails() {
            return ticketEmails;
        }

        public void setTicketEmails(String ticketEmails) {
            this.ticketEmails = ticketEmails;
        }

        public String getFcmToken() {
            return fcmToken;
        }

        public void setFcmToken(String fcmToken) {
            this.fcmToken = fcmToken;
        }

        public String getDeviceModel() {
            return deviceModel;
        }

        public void setDeviceModel(String deviceModel) {
            this.deviceModel = deviceModel;
        }

        public String getDeviceOsversion() {
            return deviceOsversion;
        }

        public void setDeviceOsversion(String deviceOsversion) {
            this.deviceOsversion = deviceOsversion;
        }

        public String getAppVersion() {
            return appVersion;
        }

        public void setAppVersion(String appVersion) {
            this.appVersion = appVersion;
        }

        public String getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }

        public List<Object> getCustomfields() {
            return customfields;
        }

        public void setCustomfields(List<Object> customfields) {
            this.customfields = customfields;
        }

    }
}