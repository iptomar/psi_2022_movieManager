package com.example.retrofitmoshi


import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// ========== For Retrofit ==========
interface API {

    @GET("3/movie/popular")
    fun getData(@Query("api_key")apiKey:String ="b8814faf945926a08a13e82c21a9e0cb"):Call<Search>

    companion object{
        fun create(): API {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl("https://api.themoviedb.org/")
                .build()

            return retrofit.create(API::class.java)
        }
    }
}