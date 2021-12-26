package com.dflorencia.themoviedatabase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.dflorencia.themoviedatabase.tv_show.TvShowDao
import com.dflorencia.themoviedatabase.tv_show.TvShowEntity
import com.dflorencia.themoviedatabase.tv_show.TvShowType
import kotlinx.coroutines.runBlocking
import org.junit.*

class TvShowDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var tvShowDao: TvShowDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testTopRatedTvShows = listOf(
        TvShowEntity(id = 111, name = "TopRatedTvShow1", type = TvShowType.TOP_RATED.value),
        TvShowEntity(id = 112, name = "TopRatedTvShow2", type = TvShowType.TOP_RATED.value),
        TvShowEntity(id = 113, name = "TopRatedTvShow3", type = TvShowType.TOP_RATED.value)
    )

    private val testPopularTvShows = listOf(
        TvShowEntity(id = 121, name = "PopularTvShow1", type = TvShowType.POPULAR.value),
        TvShowEntity(id = 122, name = "PopularTvShow2", type = TvShowType.POPULAR.value),
        TvShowEntity(id = 123, name = "PopularTvShow3", type = TvShowType.POPULAR.value),
        TvShowEntity(id = 124, name = "PopularTvShow4", type = TvShowType.POPULAR.value)
    )

    private val testAiringTodayTvShows = listOf(
        TvShowEntity(id = 131, name = "AiringTodayTvShow1", type = TvShowType.AIRING_TODAY.value),
        TvShowEntity(id = 132, name = "AiringTodayTvShow2", type = TvShowType.AIRING_TODAY.value),
        TvShowEntity(id = 133, name = "AiringTodayTvShow3", type = TvShowType.AIRING_TODAY.value),
        TvShowEntity(id = 134, name = "AiringTodayTvShow4", type = TvShowType.AIRING_TODAY.value),
        TvShowEntity(id = 135, name = "AiringTodayTvShow5", type = TvShowType.AIRING_TODAY.value),
    )

    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context,AppDatabase::class.java).build()
        tvShowDao = database.tvShowDao()
    }

    @Test
    fun forEmptyTvShowsTable_insertTopRatedTvShows_verifyRecordsInserted() = runBlocking{
        // verificar que no existan registros tipo top_rated
        Assert.assertTrue("Database isn't empty",
            tvShowDao.getTvShows(TvShowType.TOP_RATED.value).getOrAwaitValue().isEmpty()
        )

        // inserta lista
        tvShowDao.insertAll(testTopRatedTvShows)

        // obtener lista tipo top_rated
        val insertedMovies = tvShowDao.getTvShows(TvShowType.TOP_RATED.value).getOrAwaitValue()

        // validar que se insertaron los registros
        // comparando tamaño y contenido de las listas insertada y recuperada
        Assert.assertTrue("Size of lists wrong",
            insertedMovies.size == testTopRatedTvShows.size)
        Assert.assertTrue("Content of lists wrong",
            insertedMovies == testTopRatedTvShows)
    }

    @Test
    fun insertTopRatedTvShows_deleteTvShowsTypeTopRated_verifyRecordsDeleted() = runBlocking{
        // inserta lista preparada
        tvShowDao.insertAll(testTopRatedTvShows)

        // verificar que existen registros tipo top_rated en la tabla
        Assert.assertTrue("Database is empty",
            tvShowDao.getTvShows(TvShowType.TOP_RATED.value).getOrAwaitValue().isNotEmpty()
        )

        // eliminar registros tipo top_rated
        tvShowDao.clear(TvShowType.TOP_RATED.value)

        // validar que se eliminaron los registros tipo top_rated
        Assert.assertTrue("Database isn't empty",
            tvShowDao.getTvShows(TvShowType.TOP_RATED.value).getOrAwaitValue().isEmpty()
        )
    }

    @Test
    fun forEmptyTvShowsTable_insertPopularTvShows_verifyRecordsInserted() = runBlocking{
        // verificar que no existan registros tipo popular
        Assert.assertTrue("Database isn't empty",
            tvShowDao.getTvShows(TvShowType.POPULAR.value).getOrAwaitValue().isEmpty()
        )

        // inserta lista
        tvShowDao.insertAll(testPopularTvShows)

        // obtener lista tipo popular
        val insertedMovies = tvShowDao.getTvShows(TvShowType.POPULAR.value).getOrAwaitValue()

        // validar que se insertaron los registros
        // comparando tamaño y contenido de las listas insertada y recuperada
        Assert.assertTrue("Size of lists wrong",
            insertedMovies.size == testPopularTvShows.size)
        Assert.assertTrue("Content of lists wrong",
            insertedMovies == testPopularTvShows)
    }

    @Test
    fun insertPopularTvShows_deleteTvShowsTypePopular_verifyRecordsDeleted() = runBlocking{
        // inserta lista preparada
        tvShowDao.insertAll(testPopularTvShows)

        // verificar que existen registros tipo popular en la tabla
        Assert.assertTrue("Database is empty",
            tvShowDao.getTvShows(TvShowType.POPULAR.value).getOrAwaitValue().isNotEmpty()
        )

        // eliminar registros tipo popular
        tvShowDao.clear(TvShowType.POPULAR.value)

        // validar que se eliminaron los registros tipo popular
        Assert.assertTrue("Database isn't empty",
            tvShowDao.getTvShows(TvShowType.POPULAR.value).getOrAwaitValue().isEmpty()
        )
    }

    @Test
    fun forEmptyTvShowsTable_insertAiringTodayTvShows_verifyRecordsInserted() = runBlocking{
        // verificar que no existan registros tipo airing today
        Assert.assertTrue("Database isn't empty",
            tvShowDao.getTvShows(TvShowType.AIRING_TODAY.value).getOrAwaitValue().isEmpty()
        )

        // inserta lista
        tvShowDao.insertAll(testAiringTodayTvShows)

        // obtener lista tipo airing today
        val insertedMovies = tvShowDao.getTvShows(TvShowType.AIRING_TODAY.value).getOrAwaitValue()

        // validar que se insertaron los registros
        // comparando tamaño y contenido de las listas insertada y recuperada
        Assert.assertTrue("Size of lists wrong",
            insertedMovies.size == testAiringTodayTvShows.size)
        Assert.assertTrue("Content of lists wrong",
            insertedMovies == testAiringTodayTvShows)
    }

    @Test
    fun insertAiringTodayTvShows_deleteTvShowsTypeAiringToday_verifyRecordsDeleted() = runBlocking{
        // inserta lista preparada
        tvShowDao.insertAll(testAiringTodayTvShows)

        // verificar que existen registros tipo airing today en la tabla
        Assert.assertTrue("Database is empty",
            tvShowDao.getTvShows(TvShowType.AIRING_TODAY.value).getOrAwaitValue().isNotEmpty()
        )

        // eliminar registros tipo airing today
        tvShowDao.clear(TvShowType.AIRING_TODAY.value)

        // validar que se eliminaron los registros tipo airing today
        Assert.assertTrue("Database isn't empty",
            tvShowDao.getTvShows(TvShowType.AIRING_TODAY.value).getOrAwaitValue().isEmpty()
        )
    }

    @After
    fun closeDb() {
        database.close()
    }
}