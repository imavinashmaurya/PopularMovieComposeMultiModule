package com.movie_details.data.repo

import com.core.network.dataproviders.MovieDataProviders
import com.movie_details.data.mappers.toDomain
import com.movie_details.domain.model.MovieDetails
import com.movie_details.domain.repository.MovieDetailsRepo

import javax.inject.Inject

class MovieDetailsRepoImpl @Inject constructor(private val movieDataProviders: MovieDataProviders) :
    MovieDetailsRepo {
    override suspend fun getMovieDetails(id: String, apiKey: String): MovieDetails {
       return movieDataProviders.getMovieDetails(id,apiKey).toDomain()
    }
}