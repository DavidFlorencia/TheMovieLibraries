package com.dflorencia.themoviedatabase

import android.content.Context
import com.dflorencia.themoviedatabase.movie.MovieDao
import com.dflorencia.themoviedatabase.tv_show.TvShowDao
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
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase = AppDatabase.getInstance(context)

    @Provides
    fun provideMovieDao(appDatabase: AppDatabase): MovieDao = appDatabase.movieDao()

    @Provides
    fun provideTvShowDao(appDatabase: AppDatabase): TvShowDao = appDatabase.tvShowDao()
}