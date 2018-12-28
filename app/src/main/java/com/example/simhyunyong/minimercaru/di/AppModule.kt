package com.example.simhyunyong.minimercaru.di

import android.app.Application
import android.content.Context
import com.example.simhyunyong.minimercaru.data.ProductRepository
import com.example.simhyunyong.minimercaru.data.WebService
import com.example.simhyunyong.minimercaru.db.AppDatabase
import com.example.simhyunyong.minimercaru.db.dao.ProductDao
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module(includes = [ViewModelModule::class])
class AppModule {
    companion object {
        val serverUrl = "https://jsonplaceholder.typicode.com/"
    }
    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    fun provideAppDataBase(context: Context): AppDatabase = AppDatabase.getDataBase(context)

    @Provides
    @Singleton
    fun provideProductDao(database: AppDatabase) = database.productDao()

    @Provides
    @Singleton
    fun provideWebService(retrofit: Retrofit): WebService = retrofit.create(WebService::class.java)

    @Provides
    @Singleton
    fun provideSellRepository(
        productDao: ProductDao,
        webService: WebService
    ): ProductRepository = ProductRepository(productDao, webService)

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .baseUrl(serverUrl).build()
    }
}