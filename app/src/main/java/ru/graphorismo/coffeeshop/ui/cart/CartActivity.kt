package ru.graphorismo.coffeeshop.ui.cart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.graphorismo.coffeeshop.R
import ru.graphorismo.coffeeshop.data.products.Order
import ru.graphorismo.coffeeshop.data.remote.CartResponse
import ru.graphorismo.coffeeshop.ui.products.ProductsActivity

@AndroidEntryPoint
class CartActivity : AppCompatActivity() {

    val cartViewModel: CartViewModel by viewModels()

    lateinit var buttonBack: Button
    lateinit var buttonBuy: Button
    lateinit var textViewSum: TextView

    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: CartRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        buttonBack = findViewById(R.id.cartActivity_button_back)
        buttonBuy = findViewById(R.id.cartActivity_button_buy)
        textViewSum = findViewById(R.id.cartActivity_textView_priceSum)

        recyclerView = findViewById(R.id.cartActivity_recyclerView_orders)
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerAdapter = CartRecyclerAdapter(cartViewModel)
        recyclerView.adapter = recyclerAdapter

        buttonBack.setOnClickListener() { switchToProductsActivity() }

        observeCartItems()
        observeCartResponse()
    }

    private fun switchToProductsActivity() {
        val cartIntent =
            Intent(this@CartActivity, ProductsActivity::class.java)
        startActivity(cartIntent)
    }

    private fun observeCartItems() {
        lifecycleScope.launch() {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                cartViewModel.orders.collect() {
                    recyclerAdapter.items = cartViewModel.orders.value
                    showTotalPriceForOrders(cartViewModel.orders.value)
                    recyclerAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun observeCartResponse() {
        lifecycleScope.launch() {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                cartViewModel.cartResponse.collect() {
                    if(it.status != ""){
                        Toast.makeText(this@CartActivity, it.status, Toast.LENGTH_LONG).show()
                    }
                    cartViewModel.cartResponse.value = CartResponse("")
                }
            }
        }
    }

    private fun showTotalPriceForOrders(orders: MutableList<Order>) {
        var sumPrices = orders.map { it.amount * it.product.price}
        var totalPrice = sumPrices.sum()
        textViewSum.text = "Total price: $totalPrice"
    }
}