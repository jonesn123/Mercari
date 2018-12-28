package com.example.simhyunyong.minimercaru.di

import com.example.simhyunyong.minimercaru.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [FragmentBuilder::class])
    abstract fun bindMainActivity(): MainActivity
}