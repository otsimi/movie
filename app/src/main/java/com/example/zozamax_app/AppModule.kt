package com.example.zozamax_app

import com.example.zozamax_app.api_services.ApiService
import com.example.zozamax_app.util.API_KEY
import com.example.zozamax_app.util.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppModule {

    fun getRetrofitInstance(): ApiService {
        val tokenIntercepter = TokenIntercepter(API_KEY)
        val client = OkHttpClient.Builder().addInterceptor(tokenIntercepter).build()


        val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        val api: ApiService by lazy {
            retrofit.create(ApiService::class.java)
        }
        return api
    }
}

class TokenIntercepter(val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request =
            chain.request().newBuilder().addHeader("Authorization", "Bearer $apiKey").build()
        return chain.proceed(request)
    }

}