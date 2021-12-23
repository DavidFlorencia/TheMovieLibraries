package com.dflorencia.themovieapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MovieDao {
    @Query("select * from movies")
    fun getMovies(): LiveData<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(videos: List<MovieEntity>)

    @Query("delete from movies")
    fun clear()
}