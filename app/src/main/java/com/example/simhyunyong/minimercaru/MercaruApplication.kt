package com.example.simhyunyong.minimercaru

import com.example.simhyunyong.minimercaru.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import android.os.StrictMode



class MercaruApplication: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this)
        return appComponent
    }

    override fun onCreate() {
        super.onCreate()
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
    }

}