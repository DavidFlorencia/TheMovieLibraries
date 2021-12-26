# The Movie Libraries

## [The Movie Api](/the-movie-api/src/main/java/com/dflorencia/themovieapi)

Esta una libreria de Android que se encarga de consumir los servicios de la Api Rest de https://www.themoviedb.org/documentation/api.
Contiene los modelos de datos [MoviePage](/the-movie-api/src/main/java/com/dflorencia/themovieapi/movie/MoviePage.kt) y [TvShowPage](/the-movie-api/src/main/java/com/dflorencia/themovieapi/tv_show/TvShowPage.kt) los cuales almacenan la respuesta del api según la consulta que se haya realizado. 
Dentro de los modelos Page podemos encontrar una lista de películas o series de televisión cuyo modelo de datos individual son [Movie](/the-movie-api/src/main/java/com/dflorencia/themovieapi/movie/Movie.kt) y [TvShow](/the-movie-api/src/main/java/com/dflorencia/themovieapi/tv_show/TvShow.kt) respectivamente.

Para acceder a los métodos de la librería es necesario crear un cliente rest con retrofit de la interface [TmdbApi](/the-movie-api/src/main/java/com/dflorencia/themovieapi/TmdbApi.kt).
Para simplificar dicho proceso se ha configurado la clase [ApiModulo](/the-movie-api/src/main/java/com/dflorencia/themovieapi/ApiModule.kt) para implementar inyección de dependecias utilizando Hilt.

Los métodos provistos por esta libreria son los siguientes:
* getTopRatedMovies(apiKey: String): Requiere un apiKey como parámetro. Devuelve un MoviePage que contiene una lista con las películas mejor valoradas.
* getPopularMovies(apiKey: String): Requiere un apiKey como parámetro. Devuelve un MoviePage que contiene una lista con las películas más populares.
* getUpcomingMovies(apiKey: String): Requiere un apiKey como parámetro. Devuelve un MoviePage que contiene una lista con los próximos estrenos.
* getTopRatedTvShows(apiKey: String): Requiere un apiKey como parámetro. Devuelve un TvShowPage que contiene una lista con las series mejor valoradas.
* getPopularTvShows(apiKey: String): Requiere un apiKey como parámetro. Devuelve un TvShowPage que contiene una lista con las series más populares.
* getAiringTodayTvShows(apiKey: String): Requiere un apiKey como parámetro. Devuelve un TvShowPage que contiene una lista con las series que se transmiten el día de la consulta.

## [The Movie Database](/the-movie-database/src/main/java/com/dflorencia/themoviedatabase)

Esta una libreria de Android para la persistencia de datos de la información obtenida por las consultas de la librería **The Movie Api**. 
Sin embargo, ambas librerías pueden trabajar de manera totalmente independiente.

Para su implementación se utilizó la estructura propuesta por Google para Room. La base de datos SQlite contiene dos tablas *movies* y *tv_shows* 
cuyos modelos de datos son [MovieEntity](/the-movie-database/src/main/java/com/dflorencia/themoviedatabase/movie/MovieEntity.kt) 
y [TvShowEntity](/the-movie-database/src/main/java/com/dflorencia/themoviedatabase/tv_show/TvShowEntity.kt). 
Se agregaron las clases  y [TvShowType](/the-movie-database/src/main/java/com/dflorencia/themoviedatabase/tv_show/TvShowType.kt) 
de tipo enum para facilitar la clasificación de tipos según la fuente de datos.

Para acceder a los métodos de la librería es necesario crear una instancia de la clase [AppDatabase](/the-movie-database/src/main/java/com/dflorencia/themoviedatabase/AppDatabase.kt).
Para simplificar dicho proceso se ha configurado la clase [DatabaseModule](/the-movie-database/src/main/java/com/dflorencia/themoviedatabase/DatabaseModule.kt) para implementar inyección de dependecias utilizando Hilt.

Los métodos provistos por esta libreria están definidos en los objetos de acceso de datos [MovieDao](/the-movie-database/src/main/java/com/dflorencia/themoviedatabase/movie/MovieDao.kt) y [TvShowDao](/the-movie-database/src/main/java/com/dflorencia/themoviedatabase/tv_show/TvShowDao.kt) y son los siguientes:
* movieDao.getMovies(type: String): Devuelve una lista de películas del tipo solicitado, encapsulada en un objeto LiveData (pensado para utilizarse con la arquitectura MVVM).
* movieDao.insertAll(videos: List<MovieEntity>): Inserta todas las películas de la lista que recibe como parámetro. 
En caso de encontrar conflictos con un elemento, lo ignora y continua con el siguiente. 
* movieDao.clear(type: String): Elimina todas las películas del tipo recibido como parámetro.
* Los posibles tipos de película están definidos en [MovieType](/the-movie-database/src/main/java/com/dflorencia/themoviedatabase/movie/MovieType.kt) y son: "top_rated", "popular" y "upcoming".
* tvShowDao.getTvShows(type: String): Devuelve una lista de series del tipo solicitado, encapsulada en un objeto LiveData (pensado para utilizarse con la arquitectura MVVM).
* tvShowDao.insertAll(videos: List<TvShowEntity>): Inserta todas las series de la lista que recibe como parámetro. 
En caso de encontrar conflictos con un elemento, lo ignora y continua con el siguiente. 
* tvShowDao.clear(type: String): Elimina todas las series del tipo recibido como parámetro.
* Los posibles tipos de serie están definidos en [TvShowType](/the-movie-database/src/main/java/com/dflorencia/themoviedatabase/tv_show/TvShowType.kt) y son: "top_rated", "popular" y "airing_today".
  
Las pruebas implementadas para esta libreria pueden ser consultadas en el directorio [androidTest](/the-movie-database/src/androidTest/java/com/dflorencia/themoviedatabase/).
  
## [The Movie Repository](/the-movie-repository/src/main/java/com/dflorencia/themovierepository/)
  
Esta es una libreria orquestadora entre **The Movie Api** y **The Movie Database**, es decir tiene dependencia explícita de las mismas.
  
Los métodos provistos están definidos dentro de [AppRepository](/the-movie-repository/src/main/java/com/dflorencia/themovierepository/AppRepository.kt) y son los siguientes:
* refreshData(): Llama a los 6 métodos de la librería **The Movie Api** y los almacena en la base de datos utilizando la librería **The Movie Database**.
* refreshMovies(type: MovieType): Llama al método de la librería **The Movie Api** correspondiente a las películas del tipo solicitado y lo almacena en base de datos.
Se implemento para reducir la cantidad de código repetido dentro de refreshData().
* refreshMovies(type: MovieType): Llama al método de la librería **The Movie Api** correspondiente a las series del tipo solicitado y lo almacena en base de datos.
Se implemento para reducir la cantidad de código repetido dentro de refreshData().
* moviesTopRated: Devuelve una lista de películas del tipo "top_rated", encapsulada en un objeto LiveData.
* moviesPopular: Devuelve una lista de películas del tipo "popular", encapsulada en un objeto LiveData.
* moviesUpcoming: Devuelve una lista de películas del tipo "upcoming", encapsulada en un objeto LiveData.
* tvShowsTopRated: Devuelve una lista de series del tipo "top_rated", encapsulada en un objeto LiveData.
* tvShowsPopular: Devuelve una lista de series del tipo "popular", encapsulada en un objeto LiveData.
* tvShowsAiringToday: Devuelve una lista de series del tipo "airing_today", encapsulada en un objeto LiveData.
  
Se extrajo la interface [TheMovieRepository](/the-movie-repository/src/main/java/com/dflorencia/themovierepository/TheMovieRepository.kt) 
a partir de **AppRepository** con el fin de facilitar la implementación de clases orquestadoras personalizadas en caso de ser necesario.
  
En la clase [Extensions](/the-movie-repository/src/main/java/com/dflorencia/themovierepository/Extensions.kt) se puede consultar la forma en que se realizan las transformaciones entre los modelos de datos del api y de la base de datos.

En el directorio [test](/the-movie-repository/src/test/java/com/dflorencia/themovierepository/) se pueden consultar las pruebas implementadas.
