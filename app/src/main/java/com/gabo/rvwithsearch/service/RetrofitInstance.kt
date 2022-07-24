package com.gabo.rvwithsearch.service

import com.gabo.rvwithsearch.constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    val retrofitService: CharacterService? = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CharacterService::class.java)
}