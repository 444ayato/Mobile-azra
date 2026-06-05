package com.example.azraapps.data.api

import com.example.azraapps.data.model.CatFactModel
import retrofit2.http.GET

    interface CatFactApiService {
        @GET("fact")
        suspend fun getCatFact(): CatFactModel
    }