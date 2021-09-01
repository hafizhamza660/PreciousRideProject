package com.example.task.API;

import com.example.task.AllNotificiationFiles.UserServiceAllNotification;
import com.example.task.FilesLogin.UserServiceSiginIn;
import com.example.task.FilesSignUp.UserServiceSignUp;
import com.example.task.InterCityNegotiate.UserServiceRideNegotiateInterCity;
import com.example.task.InterCityRequest.UserServiceInterCityRideRequest;
import com.example.task.InterCityRequestAccept.UserServiceRideAcceptInterCity;
import com.example.task.LoginValues.UserServiceLoginValues;
import com.example.task.LogoutStatusFiles.UserServiceLogoutStatus;
import com.example.task.NotificationFiles.UserServiceNotification;
import com.example.task.RideAcceptFiles.UserServiceRideAccept;
import com.example.task.RideNegotiate.UserServiceRideNegotiate;
import com.example.task.RideRequestFiles.UserServiceRideRequest;
import com.example.task.StatusFiles.UserServiceStatus;
import com.example.task.UpdateFiles.UserServiceUpdate;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClass {
    private static Retrofit getRetrofit(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

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

}
