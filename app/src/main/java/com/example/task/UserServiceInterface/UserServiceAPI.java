package com.example.task.UserServiceInterface;

import com.example.task.AccountDetailsFiles.RequestAccountDetails;
import com.example.task.AccountDetailsFiles.ResponseAccountDetails;
import com.example.task.AllDocumentFiles.RequestAllDocument;
import com.example.task.AllDocumentFiles.ResponseAllDocument;
import com.example.task.AllNotificiationFiles.AllNotificationRequest;
import com.example.task.AllNotificiationFiles.AllNotificationResponse;
import com.example.task.CheckRideStatusFiles.CheckRideStatusRequest;
import com.example.task.CheckRideStatusFiles.CheckRideStatusResponse;
import com.example.task.ClientDataFiles.RequestClientData;
import com.example.task.ClientDataFiles.ResponseClientData;
import com.example.task.CountryNameFiles.CountryNameResponse;
import com.example.task.DataSendFiles.RequestDataSend;
import com.example.task.DataSendFiles.ResponseDataSend;
import com.example.task.DocumentDataFiles.RequestDocumentCountrywise;
import com.example.task.DocumentDataFiles.ResponseDocumentCountrywise;
import com.example.task.DocumentUploadFiles.RequestDocument;
import com.example.task.DocumentUploadFiles.ResponseDocumentUpload;
import com.example.task.DocumentsStartFiles.RequestDocumentStartSend;
import com.example.task.DocumentsStartFiles.ResponseDocumentStartSend;
import com.example.task.DriverAcceptRide.AcceptRideRequest;
import com.example.task.DriverAcceptRide.AcceptRideResponse;
import com.example.task.FilesLogin.RequestLogin;
import com.example.task.FilesLogin.ResponseLogin;
import com.example.task.FilesSignUp.RequestSignUp;
import com.example.task.FilesSignUp.ResponseSignUp;
import com.example.task.ForgetPasswordFiles.RequestForgetPassword;
import com.example.task.ForgetPasswordFiles.ResponseForgetPassword;
import com.example.task.InterCityNegotiate.RequestRideNegotiateInterCity;
import com.example.task.InterCityNegotiate.ResponseRideNegotiateInterCity;
import com.example.task.InterCityRequest.InterCityRideRequest;
import com.example.task.InterCityRequest.InterCityRideRequestResponse;
import com.example.task.InterCityRequestAccept.RequestRideAcceptInterCity;
import com.example.task.InterCityRequestAccept.ResponseRideAcceptInterCity;
import com.example.task.LoginValues.RequestLoginValues;
import com.example.task.LoginValues.ResponseLoginValues;
import com.example.task.LogoutStatusFiles.RequestLogoutStatus;
import com.example.task.LogoutStatusFiles.ResponseLogoutStatus;
import com.example.task.Messages.MessageWithChatId.MessageWithChatidRequest;
import com.example.task.Messages.MessageWithChatId.MessagesResponse;
import com.example.task.Messages.MessageWithoutChatId.MessageWithoutChatidRequest;
import com.example.task.NotificationFiles.NotificationRequest;
import com.example.task.NotificationFiles.NotificationResponse;
import com.example.task.PersonalInformationFiles.RequestPersonalInformation;
import com.example.task.PersonalInformationFiles.ResponsePersonalInformation;
import com.example.task.PhoneVerificationFiles.RequestPhoneVerfication;
import com.example.task.PhoneVerificationFiles.ResponsePhoneVerification;
import com.example.task.RangeFiles.RequestRange;
import com.example.task.RangeFiles.ResponseRange;
import com.example.task.ResetPasswordFiles.RequestResetPassword;
import com.example.task.ResetPasswordFiles.ResponseResetPassword;
import com.example.task.RideAcceptFiles.RequestRideAccept;
import com.example.task.RideAcceptFiles.ResponseRideAccept;
import com.example.task.RideAcceptWithPrice.AcceptRideWithPriceRequest;
import com.example.task.RideAcceptWithPrice.AcceptRideWithPriceResponse;
import com.example.task.RideCancel.RideCancelRequest;
import com.example.task.RideCancel.RideCancelResponse;
import com.example.task.RideNegotiate.RequestRideNegotiate;
import com.example.task.RideNegotiate.ResponseRideNegotiate;
import com.example.task.RideRequestFiles.RideRequestResponse;
import com.example.task.RideRequestedHistory.RideRequestHistoryResponse;
import com.example.task.StatusFiles.RequestStatus;
import com.example.task.StatusFiles.ResponseStatus;
import com.example.task.UpdateFiles.RequestUpdate;
import com.example.task.UpdateFiles.ResponseUpdate;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserServiceAPI {

    @POST("driver-sign_up")
    Call<ResponseSignUp> userDriverSignUp(@Body RequestSignUp requestSignUp);

    @POST("driver-login")
    Call<ResponseLogin> userDriverLogin(@Body RequestLogin requestLogin);

    @POST("driver-login_status")
    Call<ResponseLoginValues> userDriverLoginStatus(@Body RequestLoginValues requestLoginValues);

    @POST("driver-status")
    Call<ResponseStatus> userDriverStatus(@Body RequestStatus requestStatus);

    @POST("driver-logout-status")
    Call<ResponseLogoutStatus> userDriverLogoutStatus(@Body RequestLogoutStatus requestLogoutStatus);

    @POST("driver-profile-update")
    Call<ResponseUpdate> userDriverProfileUpdate(@Body RequestUpdate requestUpdate);

    @POST("getDriverRideRequests")
    Call<RideRequestResponse> userGetDriverRideRequest();

    @POST("intercity-ride-history")
    Call<InterCityRideRequestResponse> userInterCityRideHistory(@Body InterCityRideRequest interCityRideRequest);

    @POST("driver-accept-ride")
    Call<ResponseRideAccept> userDriverAcceptRide(@Body RequestRideAccept requestRideAccept);

    @POST("intercity-driver-accepts")
    Call<ResponseRideAcceptInterCity> userInterCityDriverAccepts(@Body RequestRideAcceptInterCity requestRideAcceptInterCity);

    @POST("driver-negotiate-ride")
    Call<ResponseRideNegotiate> userDriverNegotitateRide(@Body RequestRideNegotiate requestRideNegotiate);

    @POST("intercity-driver-negotiate")
    Call<ResponseRideNegotiateInterCity> userInterCityDriverNegotitate(@Body RequestRideNegotiateInterCity requestRideNegotiateInterCity);

    @POST("get-driver-notifications")
    Call<NotificationResponse> userGetDriverNotification(@Body NotificationRequest notificationRequest);

    @POST("get-driver-all-notifications")
    Call<AllNotificationResponse> userGetDriverAllNotifications(@Body AllNotificationRequest allNotificationRequest);

    @POST("getDriverRequestedRideRequests")
    Call<RideRequestHistoryResponse> userGetDriverRequestedRideRequests();

    @POST("driver-cancel-ride")
    Call<RideCancelResponse> userDriverCancelRide(@Body RideCancelRequest rideCancelRequest);

    @POST("get-all-chats")
    Call<MessagesResponse> userGetAllChats(@Body MessageWithChatidRequest messageWithChatidRequest);

    @POST("get-chats")
    Call<MessagesResponse> userGetChats(@Body MessageWithoutChatidRequest messageWithoutChatidRequest);

    @POST("add-cards")
    Call<ResponseDocumentUpload> userDriverAddCards(@Body RequestDocument requestDocument);

    @POST("api-object")
    Call<ResponseDataSend> userDriverApiObject(@Body RequestDataSend requestDataSend);

    @POST("forgot_pw_otp")
    Call<ResponseForgetPassword> userDriverForgetPWOtp(@Body RequestForgetPassword requestForgetPassword);

    @POST("confirm_otp")
    Call<ResponsePhoneVerification> userDriverConfirmOtp(@Body RequestPhoneVerfication requestPhoneVerfication);

    @POST("reset_pw")
    Call<ResponseResetPassword> userDriverResetPw(@Body RequestResetPassword requestResetPassword);

    @POST("client_details_ride")
    Call<ResponseClientData> userClientDetailsRide(@Body RequestClientData requestClientData);

    @POST("range-update")
    Call<ResponseRange> userRangeUpdate(@Body RequestRange requestRange);

    @POST("get-all-documents")
    Call<ResponseAllDocument> userDriverGetAllDocuments(@Body RequestAllDocument requestAllDocument);

    @POST("add_driver_details")
    Call<ResponsePersonalInformation> userAddDriverDetails(@Body RequestPersonalInformation requestPersonalInformation);

    @POST("get_documents")
    Call<ResponseDocumentCountrywise> userDriverGetDocuments(@Body RequestDocumentCountrywise requestDocumentCountrywise);

    @POST("add_driver_all_documents")
    Call<ResponseDocumentStartSend> userDriverAllDocuments(@Body RequestDocumentStartSend requestDocumentStartSend);

    @POST("add-driver-banking-details")
    Call<ResponseAccountDetails> userAddDriverBankingDetails(@Body RequestAccountDetails requestAccountDetails);

    @POST("all-country-codes")
    Call<CountryNameResponse> userAllCountryCodes();

    @POST("add_driver_added_price")
    Call<AcceptRideWithPriceResponse> userAddDriverAddedPrice(@Body AcceptRideWithPriceRequest acceptRideWithPriceRequest);

    @POST("check_ride_status")
    Call<CheckRideStatusResponse> userCheckRideStatus(@Body CheckRideStatusRequest checkRideStatusRequest);

    @POST("driver-accept-ride")
    Call<AcceptRideResponse> userAcceptRide(@Body AcceptRideRequest acceptRideRequest);
}
