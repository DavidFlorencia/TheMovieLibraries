package com.dflorencia.themoviedatabase.tv_show

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TvShowDao {
    @Query("select * from tv_shows where type = :type")
    fun getTvShows(type: String): LiveData<List<TvShowEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(videos: List<TvShowEntity>)

    @Query("delete from tv_shows where type = :type")
    suspend fun clear(type: String)
}