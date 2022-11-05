package ru.graphorismo.coffeeshop.ui.products

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.graphorismo.coffeeshop.R
import ru.graphorismo.coffeeshop.data.products.Product

class ProductsRecyclerAdapter(var productsViewModel: ProductsViewModel):
    RecyclerView.Adapter<ProductsRecyclerAdapter.ProductsRecyclerViewHolder>()
{
    var items : List<Product> = listOf()

    inner class ProductsRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var productImage =
            itemView.findViewById<ImageView>(R.id.productsRecyclerItem_imageView_product)
        var productDescription =
            itemView.findViewById<TextView>(R.id.productsRecyclerItem_textView_description)
        var productAddToCartButton =
            itemView.findViewById<ImageButton>(R.id.productsRecyclerItem_imageButton_addToCart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.products_item, parent, false)

        return ProductsRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductsRecyclerViewHolder, position: Int) {
        holder.productDescription.text =
            items[position].name + "\nPrice:" + items[position].price
    }

    override fun getItemCount(): Int {
        return items.size
    }
}