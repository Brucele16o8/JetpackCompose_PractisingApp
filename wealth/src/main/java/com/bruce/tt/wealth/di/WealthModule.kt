package com.bruce.tt.wealth.di

import com.bruce.tt.datasource.local.LocalDataSource
import com.bruce.tt.utilities.contsants.AppConstants
import com.bruce.tt.wealth.data.remote.CoinApi
import com.bruce.tt.wealth.data.repository.CoinRepositoryImple
import com.bruce.tt.wealth.domain.repository.ICoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class WealthModule {

//    @Provides
//    @Singleton
//    fun provideRetrofit(): Retrofit {
//        val httpLoggingInterceptor = HttpLoggingInterceptor()
//        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
//
//        val httpClient = OkHttpClient().newBuilder()
//        httpClient.addInterceptor(httpLoggingInterceptor)
//
//        val interceptor = Interceptor { chain ->
//            val request = chain.request().newBuilder().build()
//            chain.proceed(request)
//        }
//
//        httpClient.addInterceptor(interceptor)
//
//        return Retrofit.Builder()
//            .baseUrl(AppConstants.BASE_URL_COIN_PAPRIKA)
//            .client(httpClient.build())
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }

//    @Provides
//    @Singleton
//    fun provideCoinPaprikaAPI(
//        retrofit: Retrofit
//    ): CoinApi {
//        return retrofit.create(CoinApi::class.java)
//    }


    @Provides
    @Singleton
    fun provideCoinPaprikaAPI(): CoinApi {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC

        val httpClient = OkHttpClient().newBuilder()
        httpClient.addInterceptor(httpLoggingInterceptor)

        val interceptor = Interceptor { chain ->
            val request = chain.request().newBuilder().build()
            chain.proceed(request)
        }

        httpClient.addInterceptor(interceptor)
        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL_COIN_PAPRIKA)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinApi::class.java)
    }


    @Provides
    @Singleton
    fun providesCoinRepository(
        api: CoinApi,
        localDataSource: LocalDataSource
    ): ICoinRepository {
        return CoinRepositoryImple(
            api = api,
            localDataSource = localDataSource
        )
    }
}