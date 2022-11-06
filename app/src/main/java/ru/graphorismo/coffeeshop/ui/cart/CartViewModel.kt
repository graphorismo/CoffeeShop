package ru.graphorismo.coffeeshop.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.graphorismo.coffeeshop.data.products.Order
import ru.graphorismo.coffeeshop.data.repositories.AuthRepository
import ru.graphorismo.coffeeshop.data.repositories.CartRepository
import javax.inject.Inject

class CartViewModel @Inject constructor(val cartRepository: CartRepository) : ViewModel()  {

    var orders = MutableStateFlow<MutableList<Order>>(mutableListOf())
    init{
        updateCartFromServer()
    }

    fun sendCartToServer(){
        viewModelScope.launch(Dispatchers.IO)  {
            //cartRepository.putOrder(orders.value.toList())
        }
    }

    fun updateCartFromServer(){
        viewModelScope.launch(Dispatchers.IO)  {
            orders.value = cartRepository.getOrders().toMutableList()
        }
    }
}