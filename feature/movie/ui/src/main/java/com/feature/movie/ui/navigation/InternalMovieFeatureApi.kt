package com.feature.movie.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.core.common.navigation_constants.MovieFeature
import com.core.feature_api.feature_api.FeatureApi
import com.feature.movie.ui.screen.MovieScreen
import com.feature.movie.ui.screen.MovieListingViewModel

internal object InternalMovieFeatureApi : FeatureApi {

    override fun registerGraph(
        navController: androidx.navigation.NavHostController,
        navGraphBuilder: androidx.navigation.NavGraphBuilder
    ) {
        navGraphBuilder.navigation(
            startDestination = MovieFeature.movieScreenRoute,
            route = MovieFeature.nestedRoute
        ) {
            composable(MovieFeature.movieScreenRoute) {
                val viewModel = hiltViewModel<MovieListingViewModel>()
                MovieScreen(viewModel = viewModel,navController)
            }
        }
    }
}