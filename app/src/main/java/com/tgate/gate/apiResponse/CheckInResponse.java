package com.tgate.gate.apiResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CheckInResponse {

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
        @SerializedName("visitorspurpose_id")
        @Expose
        private String visitorspurposeId;
        @SerializedName("staff_id")
        @Expose
        private String staffId;
        @SerializedName("clients_id")
        @Expose
        private String clientsId;
        @SerializedName("contacts_id")
        @Expose
        private String contactsId;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("profile")
        @Expose
        private String profile;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("number_of_persons")
        @Expose
        private String numberOfPersons;
        @SerializedName("remark")
        @Expose
        private String remark;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("reason")
        @Expose
        private Object reason;
        @SerializedName("checkin_time")
        @Expose
        private String checkinTime;
        @SerializedName("checkout_time")
        @Expose
        private Object checkoutTime;
        @SerializedName("checkout_remark")
        @Expose
        private Object checkoutRemark;
        @SerializedName("is_active")
        @Expose
        private String isActive;
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

        public String getVisitorspurposeId() {
            return visitorspurposeId;
        }

        public void setVisitorspurposeId(String visitorspurposeId) {
            this.visitorspurposeId = visitorspurposeId;
        }

        public String getStaffId() {
            return staffId;
        }

        public void setStaffId(String staffId) {
            this.staffId = staffId;
        }

        public String getClientsId() {
            return clientsId;
        }

        public void setClientsId(String clientsId) {
            this.clientsId = clientsId;
        }

        public String getContactsId() {
            return contactsId;
        }

        public void setContactsId(String contactsId) {
            this.contactsId = contactsId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getProfile() {
            return profile;
        }

        public void setProfile(String profile) {
            this.profile = profile;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getNumberOfPersons() {
            return numberOfPersons;
        }

        public void setNumberOfPersons(String numberOfPersons) {
            this.numberOfPersons = numberOfPersons;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getReason() {
            return reason;
        }

        public void setReason(Object reason) {
            this.reason = reason;
        }

        public String getCheckinTime() {
            return checkinTime;
        }

        public void setCheckinTime(String checkinTime) {
            this.checkinTime = checkinTime;
        }

        public Object getCheckoutTime() {
            return checkoutTime;
        }

        public void setCheckoutTime(Object checkoutTime) {
            this.checkoutTime = checkoutTime;
        }

        public Object getCheckoutRemark() {
            return checkoutRemark;
        }

        public void setCheckoutRemark(Object checkoutRemark) {
            this.checkoutRemark = checkoutRemark;
        }

        public String getIsActive() {
            return isActive;
        }

        public void setIsActive(String isActive) {
            this.isActive = isActive;
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
}