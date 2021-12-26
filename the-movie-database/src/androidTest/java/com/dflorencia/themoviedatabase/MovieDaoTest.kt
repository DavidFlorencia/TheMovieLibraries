package com.dflorencia.themoviedatabase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.dflorencia.themoviedatabase.movie.MovieDao
import com.dflorencia.themoviedatabase.movie.MovieEntity
import com.dflorencia.themoviedatabase.movie.MovieType
import kotlinx.coroutines.runBlocking
import org.junit.*

class MovieDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var movieDao: MovieDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testTopRatedMovies = listOf(
        MovieEntity(id = 111,title = "TopRatedMovie1", type = MovieType.TOP_RATED.value),
        MovieEntity(id = 112,title = "TopRatedMovie2", type = MovieType.TOP_RATED.value),
        MovieEntity(id = 113,title = "TopRatedMovie3", type = MovieType.TOP_RATED.value)
    )

    private val testPopularMovies = listOf(
        MovieEntity(id = 121,title = "PopularMovie1", type = MovieType.POPULAR.value),
        MovieEntity(id = 122,title = "PopularMovie2", type = MovieType.POPULAR.value),
        MovieEntity(id = 123,title = "PopularMovie3", type = MovieType.POPULAR.value),
        MovieEntity(id = 124,title = "PopularMovie4", type = MovieType.POPULAR.value)
    )

    private val testUpcomingMovies = listOf(
        MovieEntity(id = 131,title = "UpcomingMovie1", type = MovieType.UPCOMING.value),
        MovieEntity(id = 132,title = "UpcomingMovie2", type = MovieType.UPCOMING.value),
        MovieEntity(id = 133,title = "UpcomingMovie3", type = MovieType.UPCOMING.value),
        MovieEntity(id = 134,title = "UpcomingMovie4", type = MovieType.UPCOMING.value),
        MovieEntity(id = 135,title = "UpcomingMovie5", type = MovieType.UPCOMING.value),
    )

    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context,AppDatabase::class.java).build()
        movieDao = database.movieDao()
    }

    @Test
    fun forEmptyMoviesTable_insertTopRatedMovies_verifyRecordsInserted() = runBlocking{
        // verificar que no existan registros tipo top_rated
        Assert.assertTrue("Database isn't empty",
            movieDao.getMovies(MovieType.TOP_RATED.value).getOrAwaitValue().isEmpty()
        )

        // inserta lista
        movieDao.insertAll(testTopRatedMovies)

        // obtener lista tipo top_rated
        val insertedMovies = movieDao.getMovies(MovieType.TOP_RATED.value).getOrAwaitValue()

        // validar que se insertaron los registros
        // comparando tamaño y contenido de las listas insertada y recuperada
        Assert.assertTrue("Size of lists wrong",
            insertedMovies.size == testTopRatedMovies.size)
        Assert.assertTrue("Content of lists wrong",
            insertedMovies == testTopRatedMovies)
    }

    @Test
    fun insertTopRatedMovies_deleteMoviesTypeTopRated_verifyRecordsDeleted() = runBlocking{
        // inserta lista preparada
        movieDao.insertAll(testTopRatedMovies)

        // verificar que existen registros tipo top_rated en la tabla
        Assert.assertTrue("Database is empty",
            movieDao.getMovies(MovieType.TOP_RATED.value).getOrAwaitValue().isNotEmpty()
        )

        // eliminar registros tipo top_rated
        movieDao.clear(MovieType.TOP_RATED.value)

        // validar que se eliminaron los registros tipo top_rated
        Assert.assertTrue("Database isn't empty",
            movieDao.getMovies(MovieType.TOP_RATED.value).getOrAwaitValue().isEmpty()
        )
    }

    @Test
    fun forEmptyMoviesTable_insertPopularMovies_verifyRecordsInserted() = runBlocking{
        // verificar que no existan registros tipo popular
        Assert.assertTrue("Database isn't empty",
            movieDao.getMovies(MovieType.POPULAR.value).getOrAwaitValue().isEmpty()
        )

        // inserta lista
        movieDao.insertAll(testPopularMovies)

        // obtener lista tipo popular
        val insertedMovies = movieDao.getMovies(MovieType.POPULAR.value).getOrAwaitValue()

        // validar que se insertaron los registros
        // comparando tamaño y contenido de las listas insertada y recuperada
        Assert.assertTrue("Size of lists wrong",
            insertedMovies.size == testPopularMovies.size)
        Assert.assertTrue("Content of lists wrong",
            insertedMovies == testPopularMovies)
    }

    @Test
    fun insertPopularMovies_deleteMoviesTypePopular_verifyRecordsDeleted() = runBlocking{
        // inserta lista preparada
        movieDao.insertAll(testPopularMovies)

        // verificar que existen registros tipo popular en la tabla
        Assert.assertTrue("Database is empty",
            movieDao.getMovies(MovieType.POPULAR.value).getOrAwaitValue().isNotEmpty()
        )

        // eliminar registros tipo popular
        movieDao.clear(MovieType.POPULAR.value)

        // validar que se eliminaron los registros tipo popular
        Assert.assertTrue("Database isn't empty",
            movieDao.getMovies(MovieType.POPULAR.value).getOrAwaitValue().isEmpty()
        )
    }

    @Test
    fun forEmptyMoviesTable_insertUpcomingMovies_verifyRecordsInserted() = runBlocking{
        // verificar que no existan registros tipo upcoming
        Assert.assertTrue("Database isn't empty",
            movieDao.getMovies(MovieType.UPCOMING.value).getOrAwaitValue().isEmpty()
        )

        // inserta lista
        movieDao.insertAll(testUpcomingMovies)

        // obtener lista tipo upcoming
        val insertedMovies = movieDao.getMovies(MovieType.UPCOMING.value).getOrAwaitValue()

        // validar que se insertaron los registros
        // comparando tamaño y contenido de las listas insertada y recuperada
        Assert.assertTrue("Size of lists wrong",
            insertedMovies.size == testUpcomingMovies.size)
        Assert.assertTrue("Content of lists wrong",
            insertedMovies == testUpcomingMovies)
    }

    @Test
    fun insertUpcomingMovies_deleteMoviesTypeUpcoming_verifyRecordsDeleted() = runBlocking{
        // inserta lista preparada
        movieDao.insertAll(testUpcomingMovies)

        // verificar que existen registros tipo upcoming en la tabla
        Assert.assertTrue("Database is empty",
            movieDao.getMovies(MovieType.UPCOMING.value).getOrAwaitValue().isNotEmpty()
        )

        // eliminar registros tipo upcoming
        movieDao.clear(MovieType.UPCOMING.value)

        // validar que se eliminaron los registros tipo popular
        Assert.assertTrue("Database isn't empty",
            movieDao.getMovies(MovieType.UPCOMING.value).getOrAwaitValue().isEmpty()
        )
    }

    @After
    fun closeDb() {
        database.close()
    }
}