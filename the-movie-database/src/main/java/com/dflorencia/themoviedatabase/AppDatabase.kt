package com.dflorencia.themoviedatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dflorencia.themoviedatabase.movie.MovieDao
import com.dflorencia.themoviedatabase.movie.MovieEntity
import com.dflorencia.themoviedatabase.tv_show.TvShowDao
import com.dflorencia.themoviedatabase.tv_show.TvShowEntity

@Database(entities = [MovieEntity::class, TvShowEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun tvShowDao(): TvShowDao

    companion object{
        @Volatile
        private lateinit var instance: AppDatabase

        fun getInstance(context: Context): AppDatabase {
            synchronized(AppDatabase::class.java) {
                if (!Companion::instance.isInitialized) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java,
                        "the_movie_db.sqlite").build()
                }
            }
            return instance
        }
    }
}