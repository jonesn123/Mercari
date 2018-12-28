package com.example.simhyunyong.minimercaru.di

import com.example.simhyunyong.minimercaru.view.ProductListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {
    @ContributesAndroidInjector
    abstract fun contributeBaseProductFragment(): ProductListFragment
}