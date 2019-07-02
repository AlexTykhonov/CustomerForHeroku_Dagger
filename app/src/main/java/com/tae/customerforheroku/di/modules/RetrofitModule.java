package com.tae.customerforheroku.di.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tae.customerforheroku.CustInterface;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {

    @Singleton
    @Provides
    public HttpLoggingInterceptor httpLoginPeovider() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Singleton
    @Provides
    public Gson gsonProvider() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return gson;
    }

    @Singleton
    @Provides
    public Retrofit retrofitCreator(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://fluxcustomer.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }

    @Singleton
    @Provides
    public CustInterface custInterfaceProvider(Retrofit retrofit) {
        CustInterface custInterface = retrofit.create(CustInterface.class);
        return custInterface;
}
}
