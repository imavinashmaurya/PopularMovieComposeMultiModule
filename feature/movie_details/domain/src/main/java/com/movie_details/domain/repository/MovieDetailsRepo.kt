package com.movie_details.domain.repository

import com.movie_details.domain.model.MovieDetails


interface MovieDetailsRepo {

    suspend fun getMovieDetails(id:String,apiKey:String) : MovieDetails

}