package com.fatihhernn.mmvm.di

import android.content.Context
import com.fatihhernn.mmvm.data.local.LocalDataSource
import com.fatihhernn.mmvm.data.local.SharedPrefManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(
    ActivityRetainedComponent::class
)
class DatabaseModule {

    @Provides
    fun sharedPrefManager(@ApplicationContext context: Context):SharedPrefManager{
        return SharedPrefManager(context)
    }

    @Provides
    fun localDataSource(sharedPrefManager :SharedPrefManager):LocalDataSource{
        return LocalDataSource(sharedPrefManager)
    }

}

