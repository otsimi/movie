package com.example.zozamax_app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.zozamax_app.data.Result
import com.example.zozamax_app.repository.UpcomingMovieRepo
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class UpcomingViewModel(val repo: UpcomingMovieRepo) : ViewModel() {
    var upcomingMovies = MutableLiveData<List<Result>>(emptyList())

    init {
        getUpcomingMovies()
    }
    fun getUpcomingMovies() {
        viewModelScope.launch {
            while (isActive) {
                try {
                    val response = repo.getUpcomingMovies()
                    if (response.isSuccessful) {
                        if (response.body()!!.results.isNotEmpty()) {
                            upcomingMovies.postValue(response.body()!!.results)
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

class UpcomingModelProvider(val repo: UpcomingMovieRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val model = UpcomingViewModel(repo)
        return model as T
    }
}