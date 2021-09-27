package com.example.task.API;

import com.example.task.AllDocumentFiles.UserServiceAllDocument;
import com.example.task.AllNotificiationFiles.UserServiceAllNotification;
import com.example.task.ClientDataFiles.UserServiceClientData;
import com.example.task.DataSendFiles.UserServiceDataSend;
import com.example.task.DocumentDataFiles.UserServiceDocumentCountrywise;
import com.example.task.DocumentUploadFiles.RequestDocument;
import com.example.task.DocumentUploadFiles.UserServiceDocumentUpload;
import com.example.task.FilesLogin.UserServiceSiginIn;
import com.example.task.FilesSignUp.UserServiceSignUp;
import com.example.task.ForgetPasswordFiles.UserServiceForgetPassword;
import com.example.task.InterCityNegotiate.UserServiceRideNegotiateInterCity;
import com.example.task.InterCityRequest.UserServiceInterCityRideRequest;
import com.example.task.InterCityRequestAccept.UserServiceRideAcceptInterCity;
import com.example.task.LoginValues.UserServiceLoginValues;
import com.example.task.LogoutStatusFiles.UserServiceLogoutStatus;
import com.example.task.Messages.MessageWithChatId.UserServiceMessagesWithChatId;
import com.example.task.Messages.MessageWithoutChatId.UserServiceMessagesWithoutChatId;
import com.example.task.NotificationFiles.UserServiceNotification;
import com.example.task.PersonalInformationFiles.UserServicePersonalInformation;
import com.example.task.PhoneVerificationFiles.UserServicePhoneVerification;
import com.example.task.RangeFiles.UserServiceRange;
import com.example.task.ResetPasswordFiles.UserServiceResetPassword;
import com.example.task.RideAcceptFiles.UserServiceRideAccept;
import com.example.task.RideCancel.UserServiceRideCancel;
import com.example.task.RideNegotiate.UserServiceRideNegotiate;
import com.example.task.RideRequestFiles.UserServiceRideRequest;
import com.example.task.RideRequestedHistory.UserServiceRideHistoryRequest;
import com.example.task.StatusFiles.UserServiceStatus;
import com.example.task.UpdateFiles.UserServiceUpdate;


import java.util.concurrent.TimeUnit;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClass {
    private static Retrofit getRetrofit(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100,TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit= new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://precious-ride.ragzon.com/api/")
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    public static UserServiceSignUp getUserServiceSignUp(){
        UserServiceSignUp userServiceSignUp =getRetrofit().create(UserServiceSignUp.class);
        return userServiceSignUp;
    }

    public static UserServiceSiginIn getUserServiceLogin(){
        UserServiceSiginIn userServiceSiginIn =getRetrofit().create(UserServiceSiginIn.class);
        return userServiceSiginIn;
    }
    public static UserServiceLoginValues getUserServiceLoginValues(){
        UserServiceLoginValues userServiceLoginValues =getRetrofit().create(UserServiceLoginValues.class);
        return userServiceLoginValues;
    }

    public static UserServiceStatus getUserServiceStatus(){
        UserServiceStatus userServiceStatus =getRetrofit().create(UserServiceStatus.class);
        return userServiceStatus;
    }
    public static UserServiceLogoutStatus getUserServiceLogoutStatus(){
        UserServiceLogoutStatus userServiceLogoutStatus =getRetrofit().create(UserServiceLogoutStatus.class);
        return userServiceLogoutStatus;
    }

    public static UserServiceUpdate getUserServiceUpdate(){
        UserServiceUpdate userServiceStatus =getRetrofit().create(UserServiceUpdate.class);
        return userServiceStatus;
    }

    public static UserServiceRideRequest getUserServiceRideRequest(){
        UserServiceRideRequest userServiceStatus =getRetrofit().create(UserServiceRideRequest.class);
        return userServiceStatus;
    }
    public static UserServiceInterCityRideRequest getUserServiceInterCityRideRequest(){
        UserServiceInterCityRideRequest userServiceInterCityRideRequest =getRetrofit().create(UserServiceInterCityRideRequest.class);
        return userServiceInterCityRideRequest;
    }

    public static UserServiceRideAccept getUserServiceRideAccept(){
        UserServiceRideAccept userServiceRideAccept =getRetrofit().create(UserServiceRideAccept.class);
        return userServiceRideAccept;
    }
    public static UserServiceRideAcceptInterCity getUserServiceRideAcceptInterCity(){
        UserServiceRideAcceptInterCity userServiceRideAcceptInterCity =getRetrofit().create(UserServiceRideAcceptInterCity.class);
        return userServiceRideAcceptInterCity;
    }

    public static UserServiceRideNegotiate getUserServiceRideNegotiate(){
        UserServiceRideNegotiate userServiceRideNegotiate =getRetrofit().create(UserServiceRideNegotiate.class);
        return userServiceRideNegotiate;
    }

    public static UserServiceRideNegotiateInterCity getUserServiceRideNegotiateInterCity(){
        UserServiceRideNegotiateInterCity userServiceRideNegotiateInterCity =getRetrofit().create(UserServiceRideNegotiateInterCity.class);
        return userServiceRideNegotiateInterCity;
    }

    public static UserServiceNotification getUserServiceNotification(){
        UserServiceNotification userServiceNotification =getRetrofit().create(UserServiceNotification.class);
        return userServiceNotification;
    }

    public static UserServiceAllNotification getUserServiceAllNotification(){
        UserServiceAllNotification userServiceAllNotification =getRetrofit().create(UserServiceAllNotification.class);
        return userServiceAllNotification;
    }
    public static UserServiceRideHistoryRequest getUserServiceRideHistoryRequest(){
        UserServiceRideHistoryRequest userServiceRideHistoryRequest =getRetrofit().create(UserServiceRideHistoryRequest.class);
        return userServiceRideHistoryRequest;
    }
    public static UserServiceRideCancel getUserServiceRideCancel(){
        UserServiceRideCancel userServiceRideCancel =getRetrofit().create(UserServiceRideCancel.class);
        return userServiceRideCancel;
    }

    public static UserServiceMessagesWithChatId getUserServiceMessagesWithChatId(){
        UserServiceMessagesWithChatId userServiceMessagesWithChatId =getRetrofit().create(UserServiceMessagesWithChatId.class);
        return userServiceMessagesWithChatId;
    }

    public static UserServiceMessagesWithoutChatId getUserServiceMessagesWithoutChatId(){
        UserServiceMessagesWithoutChatId userServiceMessagesWithoutChatId =getRetrofit().create(UserServiceMessagesWithoutChatId.class);
        return userServiceMessagesWithoutChatId;
    }
    public static UserServiceDocumentUpload getUserServiceDocumentUpload(){
        UserServiceDocumentUpload userServiceDocumentUpload =getRetrofit().create(UserServiceDocumentUpload.class);
        return userServiceDocumentUpload;
    }
    public static UserServiceDataSend getUserServiceDataSend(){
        UserServiceDataSend userServiceDataSend =getRetrofit().create(UserServiceDataSend.class);
        return userServiceDataSend;
    }

    public static UserServiceForgetPassword getUserServiceForgetPassword(){
        UserServiceForgetPassword userServiceForgetPassword =getRetrofit().create(UserServiceForgetPassword.class);
        return userServiceForgetPassword;
    }

    public static UserServicePhoneVerification getUserServicePhoneVerification(){
        UserServicePhoneVerification userServicePhoneVerification =getRetrofit().create(UserServicePhoneVerification.class);
        return userServicePhoneVerification;
    }

    public static UserServiceResetPassword getUserServiceResetPassword(){
        UserServiceResetPassword userServiceResetPassword =getRetrofit().create(UserServiceResetPassword.class);
        return userServiceResetPassword;
    }

    public static UserServiceClientData getUserServiceClientData(){
        UserServiceClientData userServiceClientData =getRetrofit().create(UserServiceClientData.class);
        return userServiceClientData;
    }
    public static UserServiceRange getUserServiceRange(){
        UserServiceRange userServiceRange =getRetrofit().create(UserServiceRange.class);
        return userServiceRange;
    }

    public static UserServiceAllDocument getUserServiceAllDocument(){
        UserServiceAllDocument userServiceAllDocument =getRetrofit().create(UserServiceAllDocument.class);
        return userServiceAllDocument;
    }

    public static UserServicePersonalInformation getUserServicePersonalInformation(){
        UserServicePersonalInformation userServicePersonalInformation =getRetrofit().create(UserServicePersonalInformation.class);
        return userServicePersonalInformation;
    }

    public static UserServiceDocumentCountrywise getUserServiceDocumentCountrywise(){
        UserServiceDocumentCountrywise userServiceDocumentCountrywise =getRetrofit().create(UserServiceDocumentCountrywise.class);
        return userServiceDocumentCountrywise;
    }

}
