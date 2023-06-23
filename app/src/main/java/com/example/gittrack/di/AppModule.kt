package com.example.gittrack.di

import android.content.Context
import androidx.room.Room
import com.example.gittrack.data.remote.RepoService
import com.example.gittrack.data.local.RepoDao
import com.example.gittrack.data.local.RepoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit():Retrofit{
        return Retrofit
            .Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideSerService(retrofit: Retrofit): RepoService {
        return retrofit.create(RepoService::class.java)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): RepoDatabase {
        return Room.databaseBuilder(context, RepoDatabase::class.java,"repoDB").fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideDao(repoDatabse: RepoDatabase): RepoDao {
        return repoDatabse.repoDao()
    }


}