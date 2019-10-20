package com.galinc.hardtraining2.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static NetworkService mInstance;
    private static final String BASE_URL = "http://172.31.255.165/test/hs/api/";
    private Retrofit mRetrofit;
    public static final String GET_EXERCISES = "getexercises";
    public static final String GET_LISTOFTRAININGS = "getlistoftrainings";
    public static final String GET_TAMPLATE = "gettemplate";

    private NetworkService(){
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new BasicAuthInterceptor("guest", "guest"))
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(10,TimeUnit.SECONDS)
                .build()
                ;

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkService getInstance(){
        if (mInstance == null){
            mInstance = new NetworkService();
        }
        return mInstance;
    }

    public JSONPlaceHolderApi getJSONApi() {
        return mRetrofit.create(JSONPlaceHolderApi.class);
    }

}
