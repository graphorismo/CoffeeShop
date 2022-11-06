package ru.graphorismo.coffeeshop.ui.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.graphorismo.coffeeshop.data.products.Order
import ru.graphorismo.coffeeshop.data.products.Product
import ru.graphorismo.coffeeshop.data.remote.CartResponse
import ru.graphorismo.coffeeshop.data.repositories.AuthRepository
import ru.graphorismo.coffeeshop.data.repositories.CartRepository
import ru.graphorismo.coffeeshop.data.repositories.ProductsRepository
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val productsRepository: ProductsRepository,
                                            private val cartRepository: CartRepository)
    : ViewModel()
{
    lateinit var coffeeProducts: List<Product>
    lateinit var snacksProducts: List<Product>
    lateinit var milkshakesProducts: List<Product>
    lateinit var alcoholProducts: List<Product>

    var showProducts = MutableStateFlow<List<Product>>(listOf())
    var showState = MutableStateFlow<UiState>(UiState.NONE)

    var cartPutStatus = MutableStateFlow<CartResponse>(CartResponse("none"))

    init {
        viewModelScope.launch(Dispatchers.IO) {
            coffeeProducts = productsRepository.getCoffeeProducts()
            snacksProducts = productsRepository.getSnacksProducts()
            milkshakesProducts = productsRepository.getMilkshakesProducts()
            alcoholProducts = productsRepository.getAlcoholProducts()

            showProducts.value = coffeeProducts
            showState.value = UiState.COFFEE_PRODUCTS
        }

    }

    fun addToCart(product: Product){
        viewModelScope.launch(Dispatchers.IO) {
            cartPutStatus.value = cartRepository.putOrder(Order(amount = 1, product))
        }
    }





}