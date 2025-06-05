package com.bruce.tt.weather.di

import android.content.Context
import com.bruce.tt.utilities.contsants.AppConstants
import com.bruce.tt.weather.data.remote.WeatherApi
import com.bruce.tt.weather.data.repository.AddressRepositoryImpl
import com.bruce.tt.weather.data.repository.WeatherRepositoryImpl
import com.bruce.tt.weather.domain.repository.IAddressRepository
import com.bruce.tt.weather.domain.repository.IWeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
class WeatherModule {

    @Provides
    @Singleton
    fun providesAddressRepository(@ApplicationContext context: Context): IAddressRepository {
        return AddressRepositoryImpl(context = context)
    }

//    @Provides
//    @Singleton
//    fun providesRetrofit(): Retrofit {
//        val httpLoggingInterceptor = HttpLoggingInterceptor()
//        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//
//        val httpClient = OkHttpClient().newBuilder()
//        httpClient.addInterceptor(httpLoggingInterceptor)
//
//        val interceptor = Interceptor { chain ->
//            val request = chain.request()
//                .newBuilder().build()
//            chain.proceed(request)
//        }
//        httpClient.addInterceptor(interceptor)
//
//        return Retrofit.Builder()
//            .baseUrl(AppConstants.BASE_URL_WEATHER_STACK)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(httpClient.build())
//            .build()
//    }

//    @Provides
//    @Singleton
//    fun providesWeatherApi(retrofit: Retrofit): WeatherApi {
//        return retrofit.create(WeatherApi::class.java)
//    }

    @Provides
    @Singleton
    fun providesWeatherApi(): WeatherApi {
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
            .baseUrl(AppConstants.BASE_URL_WEATHER_STACK)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun providesWeatherRepository(api: WeatherApi): IWeatherRepository {
        return WeatherRepositoryImpl(api = api)
    }
}