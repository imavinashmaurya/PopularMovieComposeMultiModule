package com.movie_details.data.di

import com.core.network.dataproviders.MovieDataProviders
import com.movie_details.data.repo.MovieDetailsRepoImpl
import com.movie_details.domain.repository.MovieDetailsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DataModule {


    @Provides
    fun provideMovieDetailsRepo(movieDataProviders: MovieDataProviders): MovieDetailsRepo {
        return MovieDetailsRepoImpl(movieDataProviders)
    }


}