package com.jaspreetsingh.CovidTracker.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class apiutilities {

    private static  Retrofit retrofit = null;
    public   static ApiInterface getapiInterface(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiInterface.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit.create(ApiInterface.class);
    }
}
