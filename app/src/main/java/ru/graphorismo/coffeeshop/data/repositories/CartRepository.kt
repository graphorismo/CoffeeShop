package ru.graphorismo.coffeeshop.data.repositories

import ru.graphorismo.coffeeshop.data.products.Order
import ru.graphorismo.coffeeshop.data.remote.CartResponse
import ru.graphorismo.coffeeshop.data.remote.CoffeeShopApi
import javax.inject.Inject

class CartRepository @Inject constructor(private var authRepository: AuthRepository,
                                         private var coffeeShopApi: CoffeeShopApi)  {

    suspend fun putOrder(order: Order): CartResponse{
        try{
            return coffeeShopApi.pushCartPost(order, authRepository.token)
        }catch (ex: Exception){
            return CartResponse("net_error")
        }

    }

    suspend fun getOrders(): List<Order>{
        try{
            return coffeeShopApi.getCart(authRepository.token)
        }catch (ex: Exception){
            return listOf()
        }
    }
}