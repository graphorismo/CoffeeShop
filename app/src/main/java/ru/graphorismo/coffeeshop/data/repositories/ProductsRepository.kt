package ru.graphorismo.coffeeshop.data.repositories

import ru.graphorismo.coffeeshop.data.products.Product
import ru.graphorismo.coffeeshop.data.remote.CoffeeShopApi
import javax.inject.Inject

class ProductsRepository @Inject constructor(private var coffeeShopApi: CoffeeShopApi)  {
    suspend fun getAlcoholProducts(): List<Product> {
        return coffeeShopApi.getProductsOfType("alcohol")
    }

    suspend fun getMilkshakesProducts(): List<Product> {
        return coffeeShopApi.getProductsOfType("milkshakes")
    }

    suspend fun getSnacksProducts(): List<Product> {
        return coffeeShopApi.getProductsOfType("snacks")
    }

    suspend fun getCoffeeProducts(): List<Product> {
        return coffeeShopApi.getProductsOfType("coffee")
    }
}