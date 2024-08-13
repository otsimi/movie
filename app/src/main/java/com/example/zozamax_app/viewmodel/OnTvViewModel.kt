package com.example.zozamax_app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.zozamax_app.repository.OnTvMovieRepo
import com.example.zozamax_app.data.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch


class OnTvViewModel (val repo: OnTvMovieRepo): ViewModel(){
    var onTvMovies = MutableLiveData<List<Result>>(emptyList())
    init {
        getOnTvMovies()
    }

     fun getOnTvMovies() {
        viewModelScope.launch {
            while (isActive){
                try {
                    val response = repo.getOnTvMovies()
                    if (response.isSuccessful){
                        if (response.body()!!.results.isNotEmpty()){
                            onTvMovies.postValue(response.body()!!.results)
                        }
                    }
                }
                catch(t: Throwable) {
                    println(t)
                }
                delay(10000L)
            }
        }
    }

    class OnTvModelProvider(val repo: OnTvMovieRepo) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            val model = OnTvViewModel(repo)
            return model as T
        }
    }
}
