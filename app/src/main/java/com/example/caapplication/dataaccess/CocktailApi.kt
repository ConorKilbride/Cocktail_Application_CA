package com.example.caapplication.dataaccess

import com.example.caapplication.data.CocktailEntity
import com.example.caapplication.data.CocktailResponse
import retrofit2.Response
import retrofit2.http.GET

interface CocktailApi {
    @GET("search.php?f=b")
    suspend fun getCocktails() : CocktailResponse
}