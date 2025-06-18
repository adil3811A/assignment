package com.inovantsolutions.assignment.domain.di

import com.inovantsolutions.assignment.data.ApiService
import com.inovantsolutions.assignment.data.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Di {
    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return  Retrofit.Builder()
            .baseUrl("https://klinq.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
    @Provides
    @Singleton
    fun getRepository(apiService: ApiService):Repository{
        return Repository(apiService)
    }
}