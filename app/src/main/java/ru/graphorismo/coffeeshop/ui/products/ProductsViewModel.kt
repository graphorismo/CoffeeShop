package ru.graphorismo.coffeeshop.ui.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.graphorismo.coffeeshop.data.products.Product
import ru.graphorismo.coffeeshop.data.repositories.AuthRepository
import ru.graphorismo.coffeeshop.data.repositories.ProductsRepository
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productsRepository: ProductsRepository,
    private val authRepository: AuthRepository)
    : ViewModel()
{
    lateinit var coffeeProducts: List<Product>
    lateinit var snacksProducts: List<Product>
    lateinit var milkshakesProducts: List<Product>
    lateinit var alcoholProducts: List<Product>

    var showProducts = MutableStateFlow<List<Product>>(listOf())
    var showState = MutableStateFlow<UiState>(UiState.NONE)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            coffeeProducts = productsRepository.getCoffeeProducts(authRepository.token)
            snacksProducts = productsRepository.getSnacksProducts(authRepository.token)
            milkshakesProducts = productsRepository.getMilkshakesProducts(authRepository.token)
            alcoholProducts = productsRepository.getAlcoholProducts(authRepository.token)

            showProducts.value = coffeeProducts
            showState.value = UiState.COFFEE_PRODUCTS
        }

    }



}