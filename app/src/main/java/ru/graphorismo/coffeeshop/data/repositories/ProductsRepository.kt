package ru.graphorismo.coffeeshop.data.repositories

import ru.graphorismo.coffeeshop.data.products.Product
import ru.graphorismo.coffeeshop.data.remote.CoffeeShopApi
import javax.inject.Inject

class ProductsRepository @Inject constructor(private var coffeeShopApi: CoffeeShopApi)  {
    suspend fun getAlcoholProducts(token: String): List<Product> {
        try{
            return coffeeShopApi.getProductsOfType(token,"alcohol")
        }catch (ex: Exception){
            return listOf()
        }
    }

    suspend fun getMilkshakesProducts(token: String): List<Product> {
        try{
            return coffeeShopApi.getProductsOfType(token,"milkshakes")
        }catch (ex: Exception){
            return listOf()
        }
    }

    suspend fun getSnacksProducts(token: String): List<Product> {
        try{
            return coffeeShopApi.getProductsOfType(token,"snacks")
        }catch (ex: Exception){
            return listOf()
        }
    }

    suspend fun getCoffeeProducts(token: String): List<Product> {
        try{
            return coffeeShopApi.getProductsOfType(token,"coffee")
        }catch (ex: Exception){
            return listOf()
        }
    }
}