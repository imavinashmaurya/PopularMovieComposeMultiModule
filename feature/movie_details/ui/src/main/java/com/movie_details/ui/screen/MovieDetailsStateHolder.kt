package com.movie_details.ui.screen

import com.movie_details.domain.model.MovieDetails


data class MovieDetailsStateHolder(
    val isLoading:Boolean=false,
    val error:String="",
    val data: MovieDetails?=null
)
