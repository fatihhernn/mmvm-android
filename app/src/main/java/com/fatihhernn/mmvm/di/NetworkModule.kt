package com.fatihhernn.mmvm.di

import androidx.viewbinding.BuildConfig
import com.fatihhernn.mmvm.data.remote.NetworkApiService
import com.fatihhernn.mmvm.data.remote.RemoteDataSource
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**dager bazı instanceları dışarı açmak için diğer classları taşınması için oluşturulmuş kavram*/
/**activite yaşadığı süre boyunca bu module yaşayacak*/
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    //private val rickApiEndpoint="https://rickandmortyapi.com/api/"

    private val endPoint="https://dist-learn.herokuapp.com/api/"

    @Provides
    fun provideApiService(retrofit: Retrofit):NetworkApiService{
        return retrofit.create(NetworkApiService::class.java)
    }

    @Provides //aşağıda verilen iki tane parametre objesini bulup pass edecek
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson,endPoint: EndPoint):Retrofit{
        return Retrofit.Builder()
            .baseUrl(endPoint.url)
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
    fun provideEndPoint():EndPoint{
        return EndPoint(endPoint)
    }


    @Provides
    fun provideRemoteDataSource(apiService: NetworkApiService):RemoteDataSource{
        return RemoteDataSource(apiService)
    }

}

data class EndPoint(val url:String)