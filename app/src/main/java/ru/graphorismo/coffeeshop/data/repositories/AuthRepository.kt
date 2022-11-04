package ru.graphorismo.coffeeshop.data.repositories

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.graphorismo.coffeeshop.data.auth.AuthResponse
import ru.graphorismo.coffeeshop.data.auth.Credentials
import ru.graphorismo.coffeeshop.data.auth.RegistrateResponse
import ru.graphorismo.coffeeshop.data.remote.CoffeeShopApi
import java.net.ConnectException
import javax.inject.Inject
import kotlin.Exception


class AuthRepository @Inject constructor(var coffeeShopApi: CoffeeShopApi) {

    suspend fun getResponseToLogin(credentials: Credentials) : AuthResponse {
        try {
            return coffeeShopApi.pushLoginPost(credentials)
        }catch (ex: Exception){
            return AuthResponse("error", "")
        }
    }

    suspend fun getResponseToRegistration(credentials: Credentials): RegistrateResponse {
        try{
            return coffeeShopApi.pushRegistratePost(credentials)
        }catch (ex: Exception){
            return RegistrateResponse(status = "error")
        }
    }
}