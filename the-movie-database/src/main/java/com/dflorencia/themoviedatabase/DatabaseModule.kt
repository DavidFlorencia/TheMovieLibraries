package com.dflorencia.themoviedatabase

import android.content.Context
import com.dflorencia.themoviedatabase.movie.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): com.dflorencia.themoviedatabase.AppDatabase =
        com.dflorencia.themoviedatabase.AppDatabase.getInstance(context)

    @Provides
    fun provideMovieDao(appDatabase: com.dflorencia.themoviedatabase.AppDatabase): MovieDao =
        appDatabase.movieDao()
}