package com.dflorencia.themoviedatabase.movie

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MovieDao {
    @Query("select * from movies where type = :type")
    fun getMovies(type: String): LiveData<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(videos: List<MovieEntity>)

    @Query("delete from movies where type = :type")
    fun clear(type: String)
}