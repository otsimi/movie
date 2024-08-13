package com.example.zozamax_app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.zozamax_app.data.Result
import com.example.zozamax_app.repository.NowPlayingMovieRepo
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class NowPlayingViewModel(val repo: NowPlayingMovieRepo) : ViewModel() {
    var nowPlayingMovies = MutableLiveData<List<Result>>(emptyList())

    init {
        getNowPlayingMovies()
    }
    fun getNowPlayingMovies() {
        viewModelScope.launch {
            while (isActive) {
                try {
                    val response = repo.getNowPlayingMovies()
                    if (response.isSuccessful) {
                        if (response.body()!!.results.isNotEmpty()) {
                            nowPlayingMovies.postValue(response.body()!!.results)
                        }

                    }
                } catch (t: Throwable) {
                    println(t)
                }
                delay(10000L)
            }
        }
    }

}

class NowPlayingModelProvider(val repo: NowPlayingMovieRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val model = NowPlayingViewModel(repo)
        return model as T
    }
}