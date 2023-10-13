package com.avinash.popularmovies.di

import com.avinash.popularmovies.navigation.NavigationProvider
import com.feature.movie.ui.navigation.MovieApi
import com.movie_details.ui.navigation.MovieDetailsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideNavigationProvider(
        movieApi: MovieApi,
        movieDetailsApi: MovieDetailsApi
    ): NavigationProvider {
        return NavigationProvider(movieApi, movieDetailsApi)
    }

}