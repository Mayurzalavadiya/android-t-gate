package com.tgate.gate.apiResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CompanyDataResponse {

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

        @SerializedName("userid")
        @Expose
        private String userid;
        @SerializedName("company")
        @Expose
        private String company;
        @SerializedName("vat")
        @Expose
        private String vat;
        @SerializedName("phonenumber")
        @Expose
        private String phonenumber;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("zip")
        @Expose
        private String zip;
        @SerializedName("state")
        @Expose
        private String state;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("website")
        @Expose
        private String website;
        @SerializedName("datecreated")
        @Expose
        private String datecreated;
        @SerializedName("active")
        @Expose
        private String active;
        @SerializedName("leadid")
        @Expose
        private Object leadid;
        @SerializedName("billing_street")
        @Expose
        private String billingStreet;
        @SerializedName("billing_city")
        @Expose
        private String billingCity;
        @SerializedName("billing_state")
        @Expose
        private String billingState;
        @SerializedName("billing_zip")
        @Expose
        private String billingZip;
        @SerializedName("billing_country")
        @Expose
        private String billingCountry;
        @SerializedName("shipping_street")
        @Expose
        private String shippingStreet;
        @SerializedName("shipping_city")
        @Expose
        private String shippingCity;
        @SerializedName("shipping_state")
        @Expose
        private String shippingState;
        @SerializedName("shipping_zip")
        @Expose
        private String shippingZip;
        @SerializedName("shipping_country")
        @Expose
        private String shippingCountry;
        @SerializedName("longitude")
        @Expose
        private Object longitude;
        @SerializedName("latitude")
        @Expose
        private Object latitude;
        @SerializedName("default_language")
        @Expose
        private String defaultLanguage;
        @SerializedName("default_currency")
        @Expose
        private String defaultCurrency;
        @SerializedName("show_primary_contact")
        @Expose
        private String showPrimaryContact;
        @SerializedName("stripe_id")
        @Expose
        private Object stripeId;
        @SerializedName("registration_confirmed")
        @Expose
        private String registrationConfirmed;
        @SerializedName("addedfrom")
        @Expose
        private String addedfrom;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getVat() {
            return vat;
        }

        public void setVat(String vat) {
            this.vat = vat;
        }

        public String getPhonenumber() {
            return phonenumber;
        }

        public void setPhonenumber(String phonenumber) {
            this.phonenumber = phonenumber;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getZip() {
            return zip;
        }

        public void setZip(String zip) {
            this.zip = zip;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public String getDatecreated() {
            return datecreated;
        }

        public void setDatecreated(String datecreated) {
            this.datecreated = datecreated;
        }

        public String getActive() {
            return active;
        }

        public void setActive(String active) {
            this.active = active;
        }

        public Object getLeadid() {
            return leadid;
        }

        public void setLeadid(Object leadid) {
            this.leadid = leadid;
        }

        public String getBillingStreet() {
            return billingStreet;
        }

        public void setBillingStreet(String billingStreet) {
            this.billingStreet = billingStreet;
        }

        public String getBillingCity() {
            return billingCity;
        }

        public void setBillingCity(String billingCity) {
            this.billingCity = billingCity;
        }

        public String getBillingState() {
            return billingState;
        }

        public void setBillingState(String billingState) {
            this.billingState = billingState;
        }

        public String getBillingZip() {
            return billingZip;
        }

        public void setBillingZip(String billingZip) {
            this.billingZip = billingZip;
        }

        public String getBillingCountry() {
            return billingCountry;
        }

        public void setBillingCountry(String billingCountry) {
            this.billingCountry = billingCountry;
        }

        public String getShippingStreet() {
            return shippingStreet;
        }

        public void setShippingStreet(String shippingStreet) {
            this.shippingStreet = shippingStreet;
        }

        public String getShippingCity() {
            return shippingCity;
        }

        public void setShippingCity(String shippingCity) {
            this.shippingCity = shippingCity;
        }

        public String getShippingState() {
            return shippingState;
        }

        public void setShippingState(String shippingState) {
            this.shippingState = shippingState;
        }

        public String getShippingZip() {
            return shippingZip;
        }

        public void setShippingZip(String shippingZip) {
            this.shippingZip = shippingZip;
        }

        public String getShippingCountry() {
            return shippingCountry;
        }

        public void setShippingCountry(String shippingCountry) {
            this.shippingCountry = shippingCountry;
        }

        public Object getLongitude() {
            return longitude;
        }

        public void setLongitude(Object longitude) {
            this.longitude = longitude;
        }

        public Object getLatitude() {
            return latitude;
        }

        public void setLatitude(Object latitude) {
            this.latitude = latitude;
        }

        public String getDefaultLanguage() {
            return defaultLanguage;
        }

        public void setDefaultLanguage(String defaultLanguage) {
            this.defaultLanguage = defaultLanguage;
        }

        public String getDefaultCurrency() {
            return defaultCurrency;
        }

        public void setDefaultCurrency(String defaultCurrency) {
            this.defaultCurrency = defaultCurrency;
        }

        public String getShowPrimaryContact() {
            return showPrimaryContact;
        }

        public void setShowPrimaryContact(String showPrimaryContact) {
            this.showPrimaryContact = showPrimaryContact;
        }

        public Object getStripeId() {
            return stripeId;
        }

        public void setStripeId(Object stripeId) {
            this.stripeId = stripeId;
        }

        public String getRegistrationConfirmed() {
            return registrationConfirmed;
        }

        public void setRegistrationConfirmed(String registrationConfirmed) {
            this.registrationConfirmed = registrationConfirmed;
        }

        public String getAddedfrom() {
            return addedfrom;
        }

        public void setAddedfrom(String addedfrom) {
            this.addedfrom = addedfrom;
        }

    }
}