package com.tae.customerforheroku.di.modules;

import com.tae.customerforheroku.CustActivity;
import com.tae.customerforheroku.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface ActivityModile {
    @ContributesAndroidInjector
     MainActivity getMainActivity ();
    @ContributesAndroidInjector
     CustActivity getCustActivity ();
}
