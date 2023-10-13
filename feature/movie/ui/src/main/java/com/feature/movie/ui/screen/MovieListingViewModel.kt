package com.feature.movie.ui.screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.UiEvent
import com.feature.movie.domain.use_cases.GetMovieListUseCase
import com.feature.movie.ui.BuildConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListingViewModel @Inject constructor(private val getMovieListUseCase: GetMovieListUseCase) :
    ViewModel() {

    private val _movieList = mutableStateOf(MovieListingStateHolder())
    val movieList: State<MovieListingStateHolder> get() = _movieList


    init {
        viewModelScope.launch {
            getMovieList(BuildConfig.ApiKey)
        }
    }

    private fun getMovieList(apiKey: String) = viewModelScope.launch {

        getMovieListUseCase(apiKey).onEach {
            when (it) {
                is UiEvent.Loading -> {
                    _movieList.value = MovieListingStateHolder(isLoading = true)
                }

                is UiEvent.Error -> {
                    _movieList.value = MovieListingStateHolder(error = it.message.toString())
                }

                is UiEvent.Success -> {
                    _movieList.value = MovieListingStateHolder(data = it.data)
                }
            }
        }.launchIn(viewModelScope)

    }


}