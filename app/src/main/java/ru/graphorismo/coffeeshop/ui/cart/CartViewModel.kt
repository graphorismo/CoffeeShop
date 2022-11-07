package ru.graphorismo.coffeeshop.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.graphorismo.coffeeshop.data.products.Order
import ru.graphorismo.coffeeshop.data.remote.CartResponse
import ru.graphorismo.coffeeshop.data.repositories.CartRepository
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(val cartRepository: CartRepository) : ViewModel()  {

    var orders = MutableStateFlow<MutableList<Order>>(mutableListOf())

    var cartResponse = MutableStateFlow<CartResponse>(CartResponse(""))

    init{
        updateCart()
    }

    fun updateCart(){
        viewModelScope.launch(Dispatchers.IO)  {
            orders.value = cartRepository.getOrders().toMutableList()
        }
    }

    fun removeFromCart(order: Order){
        viewModelScope.launch(Dispatchers.IO)  {
            cartResponse.value = cartRepository.removeOrder(order)
            orders.value = cartRepository.getOrders().toMutableList()
        }
    }
}