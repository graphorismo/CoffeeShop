package ru.graphorismo.coffeeshop.data.repositories

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.graphorismo.coffeeshop.data.auth.AuthResponse
import ru.graphorismo.coffeeshop.data.auth.Credentials
import ru.graphorismo.coffeeshop.data.remote.CoffeeShopApi
import javax.inject.Inject


class AuthRepository @Inject constructor(var coffeeShopApi: CoffeeShopApi) {

    suspend fun tryToLogin(credentials: Credentials) : Response<AuthResponse> {
        return coffeeShopApi.pushLoginPost(credentials)
    }
}