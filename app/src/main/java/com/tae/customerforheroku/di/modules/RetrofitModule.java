package com.tae.customerforheroku.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
public class RetrofitModule {

    @Singleton
    @Provides
        public HttpLoggingInterceptor httpLoginPeovider () {
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    return interceptor;
}

}
