package com.tgate.gate.api;


import com.google.gson.JsonElement;
import com.tgate.gate.apiResponse.CheckInResponse;
import com.tgate.gate.apiResponse.CheckOutResponse;
import com.tgate.gate.apiResponse.CompanyDataResponse;
import com.tgate.gate.apiResponse.ContactlistGuardResponse;
import com.tgate.gate.apiResponse.ContactlistResponse;
import com.tgate.gate.apiResponse.ForgotPasswordResponse;
import com.tgate.gate.apiResponse.GetCheckOutResponse;
import com.tgate.gate.apiResponse.GetOwnerPRofileResponse;
import com.tgate.gate.apiResponse.GetProfileResponse;
import com.tgate.gate.apiResponse.GuardNotifications;
import com.tgate.gate.apiResponse.LoginGuardResponse;
import com.tgate.gate.apiResponse.LoginOwnerResponse;
import com.tgate.gate.apiResponse.SignUpResponse;
import com.tgate.gate.apiResponse.TokenupdateCopyOwnerResponse;
import com.tgate.gate.apiResponse.TokenupdateGuardResponse;
import com.tgate.gate.apiResponse.TokenupdateOwnerResponse;
import com.tgate.gate.apiResponse.VisitorDataGuardresponse;
import com.tgate.gate.apiResponse.VisitorDetailsResponse;
import com.tgate.gate.apiResponse.VisitorPurposeGuardResponse;
import com.tgate.gate.apiResponse.VisitorStatusResponse;
import com.tgate.gate.apiResponse.VisitordataResponse;
import com.tgate.gate.apiResponse.VisitorpurposeResponse;
import com.tgate.gate.apiResponse.getNotificationResponse;
import com.tgate.gate.apiResponse.getGuardNotificationResponse;

import java.util.HashMap;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;


public interface ApiInterface {

    @GET
    Call<JsonElement> getWebserviceCall(@Url String url);

    @GET
    Call<JsonElement> getWebserviceCall(@Url String url, @Header("Authorization") String authorization);

    @GET
    Call<JsonElement> getWebserviceCall(@Url String url, @QueryMap HashMap<String, Object> body);

    @POST
    Call<JsonElement> postWebserviceCall(@Url String url, @Body HashMap<String, Object> body);


   /* //Get Api Demo
    @GET("category/getCategory")
    Call<CommonResponse<List<GetCategorydata>>> getCategory();

    //PostApiDemo
    @POST("user/registration")
    Call<SignUpResponse> registration(@Body RegistrationRequest registrationRequest);


    //MultiPart Demo
    /*@Multipart
    @POST("schedule/saveBroadcastAndScheduledDetails")
    Call<AddBroadcastResponse<AddBroadcastScheduleResponse>> saveBroadcastAndScheduledDetails(@Part List<MultipartBody.Part> build, @Part MultipartBody.Part profileImage);*/


/////   /*    OWNER    */   /////

    //Login Owner
    @FormUrlEncoded
    @POST("login_auth")
    Call<LoginOwnerResponse> loginownerresponse(@Field("email") String email, @Field("password") String password, @Field("is_login") String is_login, @Field("fcm_token") String fcm_token,
                                                @Field("device_model") String device_model, @Field("device_osversion") int device_osversion, @Field("app_version") String app_version, @Field("device_type") String device_type);

    //Token Update Copy Owner
    @FormUrlEncoded
    @POST("login_auth/token_update")
    Call<TokenupdateCopyOwnerResponse> token_update_copy_owner_response(@Field("id") String id, @Field("fcm_token") String fcm_token, @Field("is_login") String is_login, @Field("device_type") String device_type);

    //Token Update Owner
    @FormUrlEncoded
    @POST("login_auth/token_update")
    Call<TokenupdateOwnerResponse> token_upte_owner_response(@Field("id") String id, @Field("fcm_token") String fcm_token, @Field("is_login") String is_login);

    //Signup
    @FormUrlEncoded
    @POST("signup_auth")
    Call<SignUpResponse> signupresponse(@Field("firstname") String firstname, @Field("lastname") String lastname, @Field("email") String email, @Field("company") String company);

    //Forgot password
    @FormUrlEncoded
    @POST("login_auth/forgotpassword")
    Call<ForgotPasswordResponse> forgotpasswordresponse(@Field("email") String email, @Field("is_login") String is_login);

    //Visitor Status
    @FormUrlEncoded
    @POST("visitors/visitors_status")
    Call<VisitorStatusResponse> visitor_status_response(@Field("id") String id, @Field("rejected_reason") String rejected_reason, @Field("status") String status);

    //Visitors
    @Multipart
    @POST("visitors/")
    Call<VisitorDetailsResponse> visitordetailsresponse(@Part("visitorspurpose_id") String visitorspurpose_id, @Part("staff_id") String staff_id,
                                                        @Part("clients_id") String clients_id, @Part("contacts_id") String contacts_id, @Part("name") String name,
                                                        @Part("email") String email, @Part("phone") String phone, @Part("title") String title, @Part("number_of_persons") String number_of_persons,
                                                        @Part("remark") String remark, @Part("city") String city, @Part ("checkin_date") String checkin_date, @Part("checkin_time") String checkin_time,
                                                        @Part MultipartBody.Part profile, @Part MultipartBody.Part document[]);

    // Notification
    @FormUrlEncoded
    @POST("notifications")
    Call<getNotificationResponse> getnotification_response(@Field("id") String id, @Field("is_login") String is_login);

    //Get VisitorData Owner
    @FormUrlEncoded
    @POST("visitors/get_visitors")
    Call<VisitordataResponse> visitor_data_response(@Field("clients_id") String clients_id, @Field("status") String status, @Field("offset") int offset, @Field("is_login") String is_login);

    //visitors purpose
    @GET("visitors_purpose")
    Call<VisitorpurposeResponse> visitorpurposeresponse();


    //contactlist
    @GET("contacts/get_contactlist/1")
    Call<ContactlistResponse> contactlistresponse();

    //Profile
    @GET("contacts/get_ownerview_profile/{id}")
    Call<GetOwnerPRofileResponse> getownerprofileresponse(@Path("id") String id);

/////   /*    GUARD    */   /////


    //Login Guard
    @FormUrlEncoded
    @POST("login_auth")
    Call<LoginGuardResponse> loginguardresponse(@Field("email") String email, @Field("password") String password, @Field("is_login") String is_login, @Field("fcm_token") String fcm_token,
                                                @Field("device_model") String device_model, @Field("device_osversion") int device_osversion, @Field("app_version") String app_version, @Field("device_type") String device_type);

    //Token Update
    @FormUrlEncoded
    @POST("login_auth/token_update")
    Call<TokenupdateGuardResponse> token_update_guard_response(@Field("id") String id, @Field("fcm_token") String fcm_token, @Field("is_login") String is_login, @Field("device_type") String device_type);

    //Visitor Purpose Update
    @GET("visitors_purpose")
    Call<VisitorPurposeGuardResponse> visitorpurpose_guard_response();

    //Customer && Company
    @GET("customers/get_company/{id}")
    Call<CompanyDataResponse> company_data_response(@Path("id") String id);

    //ContactList
    @GET("contacts/get_contactlist/{id}")
    Call<ContactlistGuardResponse> contactlist_guard_response(@Path("id") String id);

    //Visitor Check in
    @GET("visitors/get_visitors_checkin/{id}")
    Call<CheckInResponse> checkin_response(@Path("id") String id);

    //Visitor getCheck Out
    @GET("visitors/get_visitors_checkout/1/1")
    Call<GetCheckOutResponse> getcheckout_response();

    //Visitor profile
    @GET("staffs/get_view_profile/{id}")
    Call<GetProfileResponse> getprofile_response(@Path("id") String id);

    //Notification
    @FormUrlEncoded
    @POST("notifications")
    Call<getGuardNotificationResponse> getguardnotification_response(@Field("id") String id, @Field("is_login") String is_login, @Field("offset") int offset);

    //CheckOut
    @FormUrlEncoded
    @POST("visitors/visitors_checkout")
    Call<CheckOutResponse> checkout_response(@Field("id") String id, @Field("checkout_remark") String checkout_remark);

    //VisitorData
    @FormUrlEncoded
    @POST("visitors/get_visitors")
    Call<VisitorDataGuardresponse> visitordata_Guard_response(@Field("status") String status, @Field("offset") int offset, @Field("guard_id") String guard_id, @Field("is_login") String is_login);

    //Notifications
    @FormUrlEncoded
    @POST("notifications")
    Call<GuardNotifications> guardnotification(@Field("id") String id, @Field("is_login") String is_login, @Field("offset") String offset);

}