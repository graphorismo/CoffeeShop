package ru.graphorismo.coffeeshop.data.remote

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import ru.graphorismo.coffeeshop.data.auth.AuthResponse
import ru.graphorismo.coffeeshop.data.auth.Credentials


interface CoffeeShopApi {

    @POST("/login")
    suspend fun pushLoginPost(@Body credentials: Credentials): Response<AuthResponse>

}