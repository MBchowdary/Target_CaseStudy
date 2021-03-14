package com.target.targetcasestudy.requets;

import com.target.targetcasestudy.utils.NetworkConstants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class to hold single instance of retrofit anf Api call instance
 */
public class ServiceApi {
    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .baseUrl(NetworkConstants.BASE_URL)
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static DealsApi dealsApi = retrofit.create(DealsApi.class);

    public static DealsApi getDealsApi() {
        return dealsApi;
    }
}
