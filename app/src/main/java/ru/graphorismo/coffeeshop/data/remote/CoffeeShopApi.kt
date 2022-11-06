package ru.graphorismo.coffeeshop.data.remote

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import ru.graphorismo.coffeeshop.data.auth.Credentials
import ru.graphorismo.coffeeshop.data.products.Order
import ru.graphorismo.coffeeshop.data.products.Product


interface CoffeeShopApi {

    @GET("/products")
    suspend fun getProductsOfType(@Query("token") token: String,
                                  @Query("type") type: String): List<Product>

    @POST("/login")
    suspend fun pushLoginPost(@Body credentials: Credentials): AuthResponse

    @POST("/registrate")
    suspend fun pushRegistratePost(@Body credentials: Credentials): RegistrateResponse

    @POST("/cart")
    suspend fun pushCartPost(@Body order: Order,
                             @Query("token") token: String): CartResponse

    @GET("/cart")
    suspend fun getCart(@Query("token") token: String): List<Order>


}