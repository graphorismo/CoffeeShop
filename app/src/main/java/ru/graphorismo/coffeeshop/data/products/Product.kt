package ru.graphorismo.coffeeshop.data.products

data class Product(val id: Int, val name: String, val price: Int,
                   val type: String, val pictureUrl : String)
{
}