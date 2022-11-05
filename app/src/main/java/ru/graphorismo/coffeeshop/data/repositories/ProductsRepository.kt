package ru.graphorismo.coffeeshop.data.repositories

import ru.graphorismo.coffeeshop.data.products.Product
import ru.graphorismo.coffeeshop.data.remote.CoffeeShopApi
import javax.inject.Inject

class ProductsRepository @Inject constructor(private var coffeeShopApi: CoffeeShopApi)  {
    suspend fun getAlcoholProducts(): List<Product> {
        try{
            return coffeeShopApi.getProductsOfType("alcohol")
        }catch (ex: Exception){
            return listOf()
        }
    }

    suspend fun getMilkshakesProducts(): List<Product> {
        try{
            return coffeeShopApi.getProductsOfType("milkshakes")
        }catch (ex: Exception){
            return listOf()
        }
    }

    suspend fun getSnacksProducts(): List<Product> {
        try{
            return coffeeShopApi.getProductsOfType("snacks")
        }catch (ex: Exception){
            return listOf()
        }
    }

    suspend fun getCoffeeProducts(): List<Product> {
        try{
            return coffeeShopApi.getProductsOfType("coffee")
        }catch (ex: Exception){
            return listOf()
        }
    }
}