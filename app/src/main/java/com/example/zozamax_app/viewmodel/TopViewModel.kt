package com.example.zozamax_app.viewmodel



import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.zozamax_app.data.Result
import com.example.zozamax_app.repository.TopMovieRepo
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class TopViewModel(val repo: TopMovieRepo) : ViewModel() {
    var topMovies = MutableLiveData<List<Result>>(emptyList())

    init {
        getTopMovies()
    }
    fun getTopMovies() {
        viewModelScope.launch {
            while (isActive) {
                try {
                    val response = repo.getTopMovies()
                    if (response.isSuccessful) {
                        if (response.body()!!.results.isNotEmpty()) {
                            topMovies.postValue(response.body()!!.results)
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

class TopModelProvider(val repo: TopMovieRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val model = TopViewModel(repo)
        return model as T
    }
}