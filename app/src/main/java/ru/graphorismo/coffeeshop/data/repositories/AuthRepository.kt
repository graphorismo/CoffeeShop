package ru.graphorismo.coffeeshop.data.repositories


import ru.graphorismo.coffeeshop.data.remote.AuthResponse
import ru.graphorismo.coffeeshop.data.auth.Credentials
import ru.graphorismo.coffeeshop.data.remote.RegistrateResponse
import ru.graphorismo.coffeeshop.data.remote.CoffeeShopApi
import javax.inject.Inject
import kotlin.Exception


class AuthRepository @Inject constructor(var coffeeShopApi: CoffeeShopApi) {

    var token: String = ""

    suspend fun getResponseToLogin(credentials: Credentials) : AuthResponse {
        try {
            var response = coffeeShopApi.pushLoginPost(credentials)
            token = response.token
            return response
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