package ru.graphorismo.coffeeshop.data.repositories

import ru.graphorismo.coffeeshop.data.products.Product
import ru.graphorismo.coffeeshop.data.remote.CoffeeShopApi
import javax.inject.Inject

class ProductsRepository @Inject constructor(private var authRepository: AuthRepository,
                                             private var coffeeShopApi: CoffeeShopApi)  {
    suspend fun getAlcoholProducts(): List<Product> {
        try{
            return coffeeShopApi.getProductsOfType(authRepository.token,"alcohol")
        }catch (ex: Exception){
            return listOf()
        }
    }

    suspend fun getMilkshakesProducts(): List<Product> {
        try{
            return coffeeShopApi.getProductsOfType(authRepository.token,"milkshakes")
        }catch (ex: Exception){
            return listOf()
        }
    }

    suspend fun getSnacksProducts(): List<Product> {
        try{
            return coffeeShopApi.getProductsOfType(authRepository.token,"snacks")
        }catch (ex: Exception){
            return listOf()
        }
    }

    suspend fun getCoffeeProducts(): List<Product> {
        try{
            return coffeeShopApi.getProductsOfType(authRepository.token,"coffee")
        }catch (ex: Exception){
            return listOf()
        }
    }
}