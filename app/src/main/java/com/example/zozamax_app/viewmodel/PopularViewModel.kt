package com.example.zozamax_app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.zozamax_app.data.Result
import com.example.zozamax_app.repository.PopularMovieRepo
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class PopularViewModel(val repo: PopularMovieRepo) : ViewModel() {
    var popularMovies = MutableLiveData<List<Result>>(emptyList())

    init {
        getPopularMovies()
    }
    fun getPopularMovies() {
        viewModelScope.launch {
            while (isActive) {
                try {
                    val response = repo.getPopularMovies()
                    if (response.isSuccessful) {
                        if (response.body()!!.results.isNotEmpty()) {
                            popularMovies.postValue(response.body()!!.results)
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

class PopularModelProvider(val repo: PopularMovieRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val model = PopularViewModel(repo)
        return model as T
    }
}