package com.avinash.popularmovies.navigation

import com.feature.movie.ui.navigation.MovieApi
import com.movie_details.ui.navigation.MovieDetailsApi


data class NavigationProvider(
    val movieApi: MovieApi,
    val movieDetailsApi: MovieDetailsApi
)
