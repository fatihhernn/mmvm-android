package com.fatihhernn.mmvm.di

import androidx.viewbinding.BuildConfig
import com.fatihhernn.mmvm.data.remote.NetworkApiService
import com.fatihhernn.mmvm.data.remote.RemoteDataSource
import com.fatihhernn.mmvm.data.remote.RickApiService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier

/**dager bazı instanceları dışarı açmak için diğer classları taşınması için oluşturulmuş kavram*/
/**activite yaşadığı süre boyunca bu module yaşayacak*/
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    //private val rickApiEndpoint="https://rickandmortyapi.com/api/"

    //private val endPoint="https://dist-learn.herokuapp.com/api/"

    @Provides
    fun provideApiService(@ApiRetrofit retrofit: Retrofit):NetworkApiService{
        return retrofit.create(NetworkApiService::class.java)
    }
    @Provides
    fun provideRickService(@RickAndMortyRetrofit retrofit: Retrofit):RickApiService{
        return retrofit.create(RickApiService::class.java)
    }

    @Provides //aşağıda verilen iki tane parametre objesini bulup pass edecek
    @ApiRetrofit
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson,@ApiEndPoint endPoint: String):Retrofit{
        return Retrofit.Builder()
            .baseUrl(endPoint)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }
    @Provides //aşağıda verilen iki tane parametre objesini bulup pass edecek
    @RickAndMortyRetrofit
    fun provideRickRetrofit(okHttpClient: OkHttpClient, gson: Gson,@RickAndMortyEndPoint endPoint: String):Retrofit{
        return Retrofit.Builder()
            .baseUrl(endPoint)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideGson():Gson{
        return Gson()
    }

    @Provides
    fun provideOkHttpClient():OkHttpClient{
        val builder=OkHttpClient.Builder()
        builder.interceptors().add(HttpLoggingInterceptor().apply {
            level=if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        })
        return builder.build()

    }

    @Provides
    fun provideRemoteDataSource(apiService: NetworkApiService,rickApiService:RickApiService):RemoteDataSource{
        return RemoteDataSource(apiService,rickApiService)
    }

    @Provides
    @ApiEndPoint
    fun provideApiString():String{
        return "https://dist-learn.herokuapp.com/api/"
    }
    @Provides
    @RickAndMortyEndPoint
    fun provideRickString():String{
        return "https://rickandmortyapi.com/api/"
    }

}

data class EndPoint(val url:String)

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RickAndMortyEndPoint


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiEndPoint

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RickAndMortyRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiRetrofit