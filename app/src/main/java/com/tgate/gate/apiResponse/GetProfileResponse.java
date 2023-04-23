package com.tgate.gate.apiResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetProfileResponse {
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

        @SerializedName("staffid")
        @Expose
        private String staffid;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("firstname")
        @Expose
        private String firstname;
        @SerializedName("lastname")
        @Expose
        private String lastname;
        @SerializedName("facebook")
        @Expose
        private String facebook;
        @SerializedName("linkedin")
        @Expose
        private String linkedin;
        @SerializedName("phonenumber")
        @Expose
        private String phonenumber;
        @SerializedName("skype")
        @Expose
        private String skype;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("datecreated")
        @Expose
        private String datecreated;
        @SerializedName("profile_image")
        @Expose
        private String profileImage;
        @SerializedName("last_ip")
        @Expose
        private String lastIp;
        @SerializedName("last_login")
        @Expose
        private String lastLogin;
        @SerializedName("last_activity")
        @Expose
        private Object lastActivity;
        @SerializedName("last_password_change")
        @Expose
        private Object lastPasswordChange;
        @SerializedName("new_pass_key")
        @Expose
        private Object newPassKey;
        @SerializedName("new_pass_key_requested")
        @Expose
        private Object newPassKeyRequested;
        @SerializedName("admin")
        @Expose
        private String admin;
        @SerializedName("role")
        @Expose
        private String role;
        @SerializedName("active")
        @Expose
        private String active;
        @SerializedName("default_language")
        @Expose
        private String defaultLanguage;
        @SerializedName("direction")
        @Expose
        private String direction;
        @SerializedName("media_path_slug")
        @Expose
        private String mediaPathSlug;
        @SerializedName("is_not_staff")
        @Expose
        private String isNotStaff;
        @SerializedName("hourly_rate")
        @Expose
        private String hourlyRate;
        @SerializedName("two_factor_auth_enabled")
        @Expose
        private String twoFactorAuthEnabled;
        @SerializedName("two_factor_auth_code")
        @Expose
        private Object twoFactorAuthCode;
        @SerializedName("two_factor_auth_code_requested")
        @Expose
        private Object twoFactorAuthCodeRequested;
        @SerializedName("email_signature")
        @Expose
        private String emailSignature;
        @SerializedName("google_auth_secret")
        @Expose
        private Object googleAuthSecret;
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

        public String getStaffid() {
            return staffid;
        }

        public void setStaffid(String staffid) {
            this.staffid = staffid;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
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

        public String getFacebook() {
            return facebook;
        }

        public void setFacebook(String facebook) {
            this.facebook = facebook;
        }

        public String getLinkedin() {
            return linkedin;
        }

        public void setLinkedin(String linkedin) {
            this.linkedin = linkedin;
        }

        public String getPhonenumber() {
            return phonenumber;
        }

        public void setPhonenumber(String phonenumber) {
            this.phonenumber = phonenumber;
        }

        public String getSkype() {
            return skype;
        }

        public void setSkype(String skype) {
            this.skype = skype;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getDatecreated() {
            return datecreated;
        }

        public void setDatecreated(String datecreated) {
            this.datecreated = datecreated;
        }

        public String getProfileImage() {
            return profileImage;
        }

        public void setProfileImage(String profileImage) {
            this.profileImage = profileImage;
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

        public Object getLastActivity() {
            return lastActivity;
        }

        public void setLastActivity(Object lastActivity) {
            this.lastActivity = lastActivity;
        }

        public Object getLastPasswordChange() {
            return lastPasswordChange;
        }

        public void setLastPasswordChange(Object lastPasswordChange) {
            this.lastPasswordChange = lastPasswordChange;
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

        public String getAdmin() {
            return admin;
        }

        public void setAdmin(String admin) {
            this.admin = admin;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getActive() {
            return active;
        }

        public void setActive(String active) {
            this.active = active;
        }

        public String getDefaultLanguage() {
            return defaultLanguage;
        }

        public void setDefaultLanguage(String defaultLanguage) {
            this.defaultLanguage = defaultLanguage;
        }

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }

        public String getMediaPathSlug() {
            return mediaPathSlug;
        }

        public void setMediaPathSlug(String mediaPathSlug) {
            this.mediaPathSlug = mediaPathSlug;
        }

        public String getIsNotStaff() {
            return isNotStaff;
        }

        public void setIsNotStaff(String isNotStaff) {
            this.isNotStaff = isNotStaff;
        }

        public String getHourlyRate() {
            return hourlyRate;
        }

        public void setHourlyRate(String hourlyRate) {
            this.hourlyRate = hourlyRate;
        }

        public String getTwoFactorAuthEnabled() {
            return twoFactorAuthEnabled;
        }

        public void setTwoFactorAuthEnabled(String twoFactorAuthEnabled) {
            this.twoFactorAuthEnabled = twoFactorAuthEnabled;
        }

        public Object getTwoFactorAuthCode() {
            return twoFactorAuthCode;
        }

        public void setTwoFactorAuthCode(Object twoFactorAuthCode) {
            this.twoFactorAuthCode = twoFactorAuthCode;
        }

        public Object getTwoFactorAuthCodeRequested() {
            return twoFactorAuthCodeRequested;
        }

        public void setTwoFactorAuthCodeRequested(Object twoFactorAuthCodeRequested) {
            this.twoFactorAuthCodeRequested = twoFactorAuthCodeRequested;
        }

        public String getEmailSignature() {
            return emailSignature;
        }

        public void setEmailSignature(String emailSignature) {
            this.emailSignature = emailSignature;
        }

        public Object getGoogleAuthSecret() {
            return googleAuthSecret;
        }

        public void setGoogleAuthSecret(Object googleAuthSecret) {
            this.googleAuthSecret = googleAuthSecret;
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
    }
}
