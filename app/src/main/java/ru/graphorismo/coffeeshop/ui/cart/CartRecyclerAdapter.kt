package ru.graphorismo.coffeeshop.ui.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.graphorismo.coffeeshop.R
import ru.graphorismo.coffeeshop.data.products.Product
import ru.graphorismo.coffeeshop.ui.products.ProductsViewModel

class CartRecyclerAdapter(cartViewModel: CartViewModel):
    RecyclerView.Adapter<CartRecyclerAdapter.CartRecyclerViewHolder>()
{
    var items : List<Product> = listOf()

    inner class CartRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var productImage : ImageView =
            itemView.findViewById<ImageView>(R.id.cartRecyclerItem_imageView_product)
        var productDescription =
            itemView.findViewById<TextView>(R.id.cartRecyclerItem_textView_description)
        var productRemoveFromCartButton =
            itemView.findViewById<ImageButton>(R.id.cartRecyclerItem_imageButton_removeFromCart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.products_item, parent, false)

        return CartRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartRecyclerViewHolder, position: Int) {
        holder.productDescription.text =
            items[position].name + "\nPrice:" + items[position].price
        holder.productImage.load(items[position].pictureUrl)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}