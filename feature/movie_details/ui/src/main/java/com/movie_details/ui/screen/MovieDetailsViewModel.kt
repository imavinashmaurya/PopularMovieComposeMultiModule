package com.movie_details.ui.screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.UiEvent
import com.movie_details.domain.use_cases.GetMovieDetailsUseCase
import com.movie_details.ui.BuildConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val _movieDetails = mutableStateOf(MovieDetailsStateHolder())
    val movieDetails: State<MovieDetailsStateHolder> get() = _movieDetails


    init {
        savedStateHandle.getLiveData<String>("id").observeForever {
            it?.let {
                Log.d("TAGGG", "${it}")
                getMovieDetails(it, BuildConfig.ApiKey)
            }
        }

    }


    private fun getMovieDetails(id: String, apiKey: String) {
        getMovieDetailsUseCase(id, apiKey).onEach {
            when (it) {
                is UiEvent.Loading -> {
                    _movieDetails.value = MovieDetailsStateHolder(isLoading = true)
                }

                is UiEvent.Error -> {
                    _movieDetails.value = MovieDetailsStateHolder(error = it.message.toString())
                }

                is UiEvent.Success -> {
                    _movieDetails.value = MovieDetailsStateHolder(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }


}