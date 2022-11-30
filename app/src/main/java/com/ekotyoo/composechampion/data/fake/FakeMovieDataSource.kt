package com.ekotyoo.composechampion.data.fake

import com.ekotyoo.composechampion.domain.model.Cast
import com.ekotyoo.composechampion.domain.model.MovieDetail
import com.ekotyoo.composechampion.domain.model.MovieListItem

object FakeMovieDataSource {
    fun getMovieDetailData(): List<MovieDetail> = listOf(
        MovieDetail(
            id = "1",
            title = "The Shawshank Redemption",
            year = 1994,
            genre = listOf("Drama"),
            cast = listOf(
                Cast(
                    name = "Tim Robbins",
                    playedAs = "Andy Dufresne",
                    image = ""
                ),
                Cast(
                    name = "Morgan Freeman",
                    playedAs = "Ellis Boyd 'Red' Redding",
                    image = ""
                ),
                Cast(
                    name = "Bob Gunton",
                    playedAs = "Warden Norton",
                    image = ""
                ),
                Cast(
                    name = "William Sadler",
                    playedAs = "Heywood",
                    image = ""
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
        )
    )

    fun getMovieData(): List<MovieListItem> = getMovieDetailData().map {
        MovieListItem(
            id = it.id,
            title = it.title,
            genre = it.genre,
            year = it.year,
            isFavorite = it.isFavorite,
            rating = it.rating,
            image = it.image,
        )
    }
}