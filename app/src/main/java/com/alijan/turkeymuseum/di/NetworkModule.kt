package com.alijan.turkeymuseum.di

import com.alijan.turkeymuseum.data.api.APIServices
import com.alijan.turkeymuseum.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL + Constant.API_PREFIX)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideAPIServices(retrofit: Retrofit): APIServices {
        return retrofit.create(APIServices::class.java)
    }

}