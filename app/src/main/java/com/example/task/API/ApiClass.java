package com.example.task.API;

import com.example.task.FilesLogin.UserServiceSiginIn;
import com.example.task.FilesSignUp.UserServiceSignUp;
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

    public static UserServiceStatus getUserServiceStatus(){
        UserServiceStatus userServiceStatus =getRetrofit().create(UserServiceStatus.class);
        return userServiceStatus;
    }

    public static UserServiceUpdate getUserServiceUpdate(){
        UserServiceUpdate userServiceStatus =getRetrofit().create(UserServiceUpdate.class);
        return userServiceStatus;
    }

    public static UserServiceRideRequest getUserServiceRideRequest(){
        UserServiceRideRequest userServiceStatus =getRetrofit().create(UserServiceRideRequest.class);
        return userServiceStatus;
    }

    public static UserServiceRideAccept getUserServiceRideAccept(){
        UserServiceRideAccept userServiceRideAccept =getRetrofit().create(UserServiceRideAccept.class);
        return userServiceRideAccept;
    }

    public static UserServiceRideNegotiate getUserServiceRideNegotiate(){
        UserServiceRideNegotiate userServiceRideNegotiate =getRetrofit().create(UserServiceRideNegotiate.class);
        return userServiceRideNegotiate;
    }

}
