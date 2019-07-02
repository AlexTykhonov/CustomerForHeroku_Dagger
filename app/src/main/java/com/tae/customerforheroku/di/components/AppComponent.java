package com.tae.customerforheroku.di.components;

import android.app.Application;

import com.tae.customerforheroku.App;
import com.tae.customerforheroku.di.modules.ActivityModile;
import com.tae.customerforheroku.di.modules.RetrofitModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.Module;
import dagger.android.support.AndroidSupportInjectionModule;
@Singleton
@Component (modules = {AndroidSupportInjectionModule.class, ActivityModile.class, RetrofitModule.class})

public interface AppComponent {

    @Component.Builder
interface Builder {
    @BindsInstance
    Builder application(Application application);
    AppComponent build();
}
    //важнейший метод - инджект
    void inject(App app);
}
