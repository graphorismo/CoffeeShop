package ru.graphorismo.coffeeshop.ui.products

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.graphorismo.coffeeshop.R

@AndroidEntryPoint
class ProductsActivity : AppCompatActivity() {

    val productsViewModel: ProductsViewModel by viewModels()

    lateinit var coffeeButton: ImageButton
    lateinit var snacksButton: ImageButton
    lateinit var milkshakesButton: ImageButton
    lateinit var alcoholButton: ImageButton

    lateinit var productsRecycler: RecyclerView
    lateinit var productsRecyclerAdapter: ProductsRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        coffeeButton = findViewById(R.id.productsActivity_button_coffee)
        snacksButton = findViewById(R.id.productsActivity_button_snacks)
        milkshakesButton = findViewById(R.id.productsActivity_button_milkshakes)
        alcoholButton  = findViewById(R.id.productsActivity_button_alcohol)

        productsRecycler = findViewById(R.id.productsActivity_recyclerView_products)

        coffeeButton.setOnClickListener() {showCoffeeProducts()}
        snacksButton.setOnClickListener() {showSnacksProducts()}
        milkshakesButton.setOnClickListener() {showMilkshakesProducts()}
        alcoholButton.setOnClickListener() {showAlcoholProducts()}

        productsRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        productsRecyclerAdapter = ProductsRecyclerAdapter(productsViewModel)
        productsRecycler.adapter = productsRecyclerAdapter

        observeShowProducts()
        observeShowState()

    }

    private fun observeShowProducts(){
        lifecycleScope.launch() {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                productsViewModel.showProducts.collect() {
                    productsRecyclerAdapter.items = it
                    productsRecyclerAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun observeShowState(){
        lifecycleScope.launch() {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                productsViewModel.showState.collect() {
                    var backgroundColor =
                        ContextCompat.getColor(this@ProductsActivity, R.color.light_yellow)
                    setButtonsBackgroundDefault()
                    when (it){
                        UiState.COFFEE_PRODUCTS -> coffeeButton.setBackgroundColor(backgroundColor)
                        UiState.SNACKS_PRODUCTS -> snacksButton.setBackgroundColor(backgroundColor)
                        UiState.MILKSHAKES_PRODUCTS -> milkshakesButton.setBackgroundColor(backgroundColor)
                        UiState.ALCOHOL_PRODUCTS -> alcoholButton.setBackgroundColor(backgroundColor)
                    }

                }
            }
        }
    }

    private fun setButtonsBackgroundDefault(){
        var backgroundColor =
            ContextCompat.getColor(this@ProductsActivity, R.color.orange)
        coffeeButton.setBackgroundColor(backgroundColor)
        snacksButton.setBackgroundColor(backgroundColor)
        milkshakesButton.setBackgroundColor(backgroundColor)
        alcoholButton.setBackgroundColor(backgroundColor)
    }

    private fun showAlcoholProducts() {
        productsViewModel.showProducts.value = productsViewModel.alcoholProducts
        productsViewModel.showState.value = UiState.ALCOHOL_PRODUCTS
    }

    private fun showMilkshakesProducts() {
        productsViewModel.showProducts.value = productsViewModel.milkshakesProducts
        productsViewModel.showState.value = UiState.COFFEE_PRODUCTS
    }

    private fun showSnacksProducts() {
        productsViewModel.showProducts.value = productsViewModel.snacksProducts
        productsViewModel.showState.value = UiState.SNACKS_PRODUCTS
    }

    private fun showCoffeeProducts() {
        productsViewModel.showProducts.value = productsViewModel.coffeeProducts
        productsViewModel.showState.value = UiState.COFFEE_PRODUCTS
    }
}