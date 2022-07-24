package com.gabo.rvwithsearch.service

import com.gabo.rvwithsearch.constants.END_POINT
import com.gabo.rvwithsearch.model.CharacterModel
import retrofit2.Response
import retrofit2.http.GET

interface CharacterService {
    @GET(END_POINT)
    suspend fun getCharacters(): Response<List<CharacterModel>>
}