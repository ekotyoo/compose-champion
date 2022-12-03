package com.ekotyoo.composechampion.data.fake

import com.ekotyoo.composechampion.domain.model.Cast
import com.ekotyoo.composechampion.domain.model.MovieDetail
import com.ekotyoo.composechampion.domain.model.MovieListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map

class FakeMovieDataSource {

    private val _movieFlow: MutableSharedFlow<MutableList<MovieListItem>> = MutableSharedFlow(
        replay = 1,
    )

    private var _movieDetail: MutableList<MovieDetail>
    private var _movieList: MutableList<MovieListItem>

    init {
        _movieDetail = mutableListOf(
            MovieDetail(
                id = "1",
                title = "The Shawshank Redemption",
                year = 1994,
                genre = listOf("Drama"),
                cast = listOf(
                    Cast(
                        name = "Tim Robbins",
                        playedAs = "Andy Dufresne",
                        image = "https://m.media-amazon.com/images/M/MV5BMTI1OTYxNzAxOF5BMl5BanBnXkFtZTYwNTE5ODI4._V1_.jpg"
                    ),
                    Cast(
                        name = "Morgan Freeman",
                        playedAs = "Ellis Boyd 'Red' Redding",
                        image = "https://cdn.britannica.com/40/144440-050-DA828627/Morgan-Freeman.jpg"
                    ),
                    Cast(
                        name = "Bob Gunton",
                        playedAs = "Warden Norton",
                        image = "data:image/jpeg;base64,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"
                    ),
                    Cast(
                        name = "William Sadler",
                        playedAs = "Heywood",
                        image = "https://m.media-amazon.com/images/M/MV5BMTA1NjU3NDg1MTheQTJeQWpwZ15BbWU2MDI4OTcxMw@@._V1_.jpg"
                    ),
                ),
                isFavorite = false,
                image = "https://upload.wikimedia.org/wikipedia/id/8/81/ShawshankRedemptionMoviePoster.jpg",
                overview = "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
                rating = 9.3f
            ),
            MovieDetail(
                id = "2",
                title = "The Godfather",
                year = 1972,
                genre = listOf("Crime", "Drama"),
                cast = listOf(
                    Cast(
                        name = "Marlon Brando",
                        playedAs = "Don Vito Corleone",
                        image = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/53/Marlon_Brando_publicity_for_One-Eyed_Jacks.png/800px-Marlon_Brando_publicity_for_One-Eyed_Jacks.png"
                    ),
                    Cast(
                        name = "Al Pacino",
                        playedAs = "Michael Corleone",
                        image = "https://m.media-amazon.com/images/M/MV5BMTQzMzg1ODAyNl5BMl5BanBnXkFtZTYwMjAxODQ1._V1_UY1200_CR84,0,630,1200_AL_.jpg"
                    ),
                    Cast(
                        name = "James Caan",
                        playedAs = "Sonny Corleone",
                        image = "https://m.media-amazon.com/images/M/MV5BMTI5NjkyNDQ3NV5BMl5BanBnXkFtZTcwNjY5NTQ0Mw@@._V1_.jpg"
                    ),
                    Cast(
                        name = "Diane Keaton",
                        playedAs = "Kay Adams",
                        image = "https://m.media-amazon.com/images/M/MV5BMTY5NDI5OTEyOF5BMl5BanBnXkFtZTgwMzU4NDI1NzM@._V1_UY1200_CR108,0,630,1200_AL_.jpg"
                    ),
                ),
                isFavorite = false,
                image = "https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_.jpg",
                overview = "The aging patriarch of an organized crime dynasty in postwar New York City transfers control of his clandestine empire to his reluctant youngest son.",
                rating = 9.2f
            ),
            MovieDetail(
                id = "3",
                title = "The Dark Knight",
                year = 2008,
                genre = listOf("Action", "Crime", "Drama"),
                cast = listOf(
                    Cast(
                        name = "Christian Bale",
                        playedAs = "Bruce Wayne",
                        image = "https://upload.wikimedia.org/wikipedia/commons/7/73/Christian_Bale_2014_%28cropped%29.jpg"
                    ),
                    Cast(
                        name = "Heath Ledger",
                        playedAs = "Joker",
                        image = "https://m.media-amazon.com/images/M/MV5BMTI2NTY0NzA4MF5BMl5BanBnXkFtZTYwMjE1MDE0._V1_.jpg"
                    ),
                    Cast(
                        name = "Aaron Eckhart",
                        playedAs = "Harvey Dent",
                        image = "https://m.media-amazon.com/images/M/MV5BMTc4MTAyNzMzNF5BMl5BanBnXkFtZTcwMzQ5MzQzMg@@._V1_.jpg"
                    ),
                    Cast(
                        name = "Michael Caine",
                        playedAs = "Harvey Dent",
                        image = "https://metro.co.uk/wp-content/uploads/2018/03/michael-caine-025-michael-caine-c2ae-raymi-hero-productions-2017-photo-by-jeff-spicer-e1564489766704.jpg?quality=90&strip=all"
                    ),
                ),
                isFavorite = false,
                image = "https://upload.wikimedia.org/wikipedia/id/8/8a/Dark_Knight.jpg",
                overview = "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.",
                rating = 9.0f
            ),
            MovieDetail(
                id = "4",
                title = "The Godfather: Part II",
                year = 1974,
                genre = listOf("Crime", "Drama"),
                cast = listOf(
                    Cast(
                        name = "Robert De Niro",
                        playedAs = "Vito Corleone",
                        image = "https://cdn.britannica.com/00/213300-050-ADF31CD9/American-actor-Robert-De-Niro-2019.jpg?w=400&h=300&c=crop"
                    ),
                    Cast(
                        name = "Marlon Brando",
                        playedAs = "Don Vito Corleone",
                        image = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/53/Marlon_Brando_publicity_for_One-Eyed_Jacks.png/800px-Marlon_Brando_publicity_for_One-Eyed_Jacks.png"
                    ),
                    Cast(
                        name = "Al Pacino",
                        playedAs = "Michael Corleone",
                        image = "https://m.media-amazon.com/images/M/MV5BMTQzMzg1ODAyNl5BMl5BanBnXkFtZTYwMjAxODQ1._V1_UY1200_CR84,0,630,1200_AL_.jpg"
                    ),
                    Cast(
                        name = "James Caan",
                        playedAs = "Sonny Corleone",
                        image = "https://m.media-amazon.com/images/M/MV5BMTI5NjkyNDQ3NV5BMl5BanBnXkFtZTcwNjY5NTQ0Mw@@._V1_.jpg"
                    ),
                    Cast(
                        name = "Diane Keaton",
                        playedAs = "Kay Adams",
                        image = "https://m.media-amazon.com/images/M/MV5BMTY5NDI5OTEyOF5BMl5BanBnXkFtZTgwMzU4NDI1NzM@._V1_UY1200_CR108,0,630,1200_AL_.jpg"
                    ),
                ),
                isFavorite = false,
                image = "https://m.media-amazon.com/images/M/MV5BMWMwMGQzZTItY2JlNC00OWZiLWIyMDctNDk2ZDQ2YjRjMWQ0XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_.jpg",
                overview = "The early life and career of Vito Corleone in 1920s New York City is portrayed, while his son, Michael, expands and tightens his grip on the family crime syndicate.",
                rating = 9.0f
            ),
            MovieDetail(
                id = "5",
                title = "12 Angry Men",
                year = 1957,
                genre = listOf("Crime", "Drama"),
                cast = listOf(
                    Cast(
                        name = "Henry Fonda",
                        playedAs = "Juror 8",
                        image = "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0c/Henry_Fonda_in_Warlock.jpg/1200px-Henry_Fonda_in_Warlock.jpg"
                    ),
                    Cast(
                        name = "Lee J. Cobb",
                        playedAs = "Juror 3",
                        image = "https://upload.wikimedia.org/wikipedia/commons/b/ba/Lee_J._Cobb_1960s.JPG",
                    ),
                ),
                isFavorite = false,
                image = "https://upload.wikimedia.org/wikipedia/commons/b/b5/12_Angry_Men_%281957_film_poster%29.jpg",
                overview = "The jury in a New York City murder trial is frustrated by a single member whose skeptical caution forces them to more carefully consider the evidence before jumping to a hasty verdict.",
                rating = 9.0f
            ),
            MovieDetail(
                id = "6",
                title = "Schindler\'s List",
                year = 1993,
                genre = listOf("Biography", "Drama", "History"),
                cast = listOf(
                    Cast(
                        name = "Liam Neeson",
                        playedAs = "Oskar Schindler",
                        image = "https://upload.wikimedia.org/wikipedia/commons/b/b9/Liam_Neeson_Deauville_2012.jpg"
                    ),
                    Cast(
                        name = "Ralph Fiennes",
                        playedAs = "Amon Goeth",
                        image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS8R_RC_r6JQdW9yv_5rjfFL9EBeF0YDARoogi0LUtCAkK7P6u7o4huShPA8cUvf-VFQpg&usqp=CAU"
                    ),
                    Cast(
                        name = "Ben Kingsley",
                        playedAs = "Itzhak Stern",
                        image = "https://m.media-amazon.com/images/M/MV5BOTU2Njg2NzM4M15BMl5BanBnXkFtZTgwNjYwNjQwMTI@._V1_.jpg"
                    ),
                    Cast(
                        name = "Caroline Goodall",
                        playedAs = "Emilie Schindler",
                        image = "https://pbs.twimg.com/profile_images/1331352846827196418/KekeRl9G_400x400.jpg"
                    ),
                ),
                isFavorite = false,
                image = "https://m.media-amazon.com/images/M/MV5BNDE4OTMxMTctNmRhYy00NWE2LTg3YzItYTk3M2UwOTU5Njg4XkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_.jpg",
                overview = "In German-occupied Poland during World War II, industrialist Oskar Schindler gradually becomes concerned for his Jewish workforce after witnessing their persecution by the Nazis.",
                rating = 9.0f
            ),
            MovieDetail(
                id = "7",
                title = "The Lord of The Rings: The Return of the King",
                year = 2003,
                genre = listOf("Action", "Adventure", "Drama"),
                cast = listOf(
                    Cast(
                        name = "Elijah Wood",
                        playedAs = "Frodo",
                        image = "https://flxt.tmsimg.com/assets/19046_v9_bb.jpg"
                    ),
                    Cast(
                        name = "Viggo Mortensen",
                        playedAs = "Aragorn",
                        image = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/64/Viggo_Mortensen_B_%282020%29.jpg/640px-Viggo_Mortensen_B_%282020%29.jpg"
                    ),
                ),
                isFavorite = false,
                image = "https://m.media-amazon.com/images/M/MV5BNzA5ZDNlZWMtM2NhNS00NDJjLTk4NDItYTRmY2EwMWZlMTY3XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_.jpg",
                overview = "Gandalf and Aragorn lead the World of Men against Sauron's army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring.",
                rating = 9.0f
            ),
            MovieDetail(
                id = "8",
                title = "Pulp Fiction",
                year = 1994,
                genre = listOf("Crime", "Drama"),
                cast = listOf(
                    Cast(
                        name = "John Travolta",
                        playedAs = "Vincent Vega",
                        image = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/da/John_Travolta%2C_London%2C_2013_%28derivate%29.jpg/640px-John_Travolta%2C_London%2C_2013_%28derivate%29.jpg"
                    ),
                    Cast(
                        name = "Uma Thurman",
                        playedAs = "Mia Wallace",
                        image = "https://upload.wikimedia.org/wikipedia/commons/4/42/Uma_Thurman_Cannes_2017_%28cropped%29.jpg"
                    ),
                ),
                isFavorite = false,
                image = "https://m.media-amazon.com/images/M/MV5BNGNhMDIzZTUtNTBlZi00MTRlLWFjM2ItYzViMjE3YzI5MjljXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_.jpg",
                overview = "The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.",
                rating = 8.9f
            ),
            MovieDetail(
                id = "9",
                title = "The Lord of the Rings: The Fellowship of the Ring",
                year = 2001,
                genre = listOf("Action", "Adventure", "Drama"),
                cast = listOf(
                    Cast(
                        name = "Elijah Wood",
                        playedAs = "Frodo",
                        image = "https://flxt.tmsimg.com/assets/19046_v9_bb.jpg"
                    ),
                    Cast(
                        name = "Viggo Mortensen",
                        playedAs = "Aragorn",
                        image = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/64/Viggo_Mortensen_B_%282020%29.jpg/640px-Viggo_Mortensen_B_%282020%29.jpg"
                    ),
                ),
                isFavorite = false,
                image = "https://images.moviesanywhere.com/198e228b071c60f5ad57e5f62fe60029/ff22cad6-2218-414d-b853-3f95d76905c7.jpg",
                overview = "The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.",
                rating = 8.9f
            ),
            MovieDetail(
                id = "10",
                title = "The Good, the Bad and the Ugly",
                year = 1966,
                genre = listOf("Adventure", "Western"),
                cast = listOf(
                    Cast(
                        name = "Client Eastwood",
                        playedAs = "Blondie",
                        image = "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7e/Clint_Eastwood_at_2010_New_York_Film_Festival.jpg/640px-Clint_Eastwood_at_2010_New_York_Film_Festival.jpg"
                    ),
                    Cast(
                        name = "Eli Wallach",
                        playedAs = "Tuco",
                        image = "https://static01.nyt.com/images/2014/06/26/arts/wallach-obit-slide-H7CY/wallach-obit-slide-H7CY-superJumbo.jpg"
                    ),
                ),
                isFavorite = false,
                image = "https://upload.wikimedia.org/wikipedia/en/thumb/4/45/Good_the_bad_and_the_ugly_poster.jpg/220px-Good_the_bad_and_the_ugly_poster.jpg",
                overview = "A bounty hunting scam joins two men in an uneasy alliance against a third in a race to find a fortune in gold buried in a remote cemetery.",
                rating = 8.8f
            ),
        )
        _movieList = _movieDetail.map {
            MovieListItem(
                id = it.id,
                title = it.title,
                genre = it.genre,
                year = it.year,
                isFavorite = it.isFavorite,
                rating = it.rating,
                image = it.image,
            )
        }.toMutableList()
        _movieFlow.tryEmit(_movieList)
    }

    fun getMovies(query: String = ""): Flow<List<MovieListItem>> = _movieFlow.map {
        it.filter { d -> d.title.contains(query, ignoreCase = true) }
    }

    fun getMovieDetail(movieId: String): Flow<MovieDetail?> = _movieFlow.map { movieList ->
        _movieDetail.firstOrNull { it.id == movieId }?.copy(
            isFavorite = movieList.firstOrNull { it.id == movieId }?.isFavorite ?: false
        )
    }

    fun favoriteMovie(movieId: String, isFavorite: Boolean): Boolean {
        val index = _movieList.indexOfFirst { it.id == movieId }
        if (index >= 0) {
            _movieList[index] = _movieList[index].copy(isFavorite = isFavorite)
            _movieFlow.tryEmit(_movieList)
            return true
        }
        return false
    }
}