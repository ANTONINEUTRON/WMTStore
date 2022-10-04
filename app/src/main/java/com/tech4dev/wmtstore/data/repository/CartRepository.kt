package com.tech4dev.wmtstore.data.repository

import com.tech4dev.wmtstore.data.models.Product

object CartRepository {
    private val selectedProducts = mutableMapOf<Product, Int>()

    fun addToCart(product: Product){
        selectedProducts.put(product, 1)
    }

    fun removeFromCart(product: Product){
        selectedProducts.remove(product)
    }

    fun reduceQuantity(product: Product){
        //get quantity
        var quantity:Int = selectedProducts[product]!!
        quantity--
        selectedProducts[product] = quantity
    }

    fun increaseQuantity(product: Product){
        var quantity: Int = selectedProducts[product]!!
        quantity++
        selectedProducts[product] = quantity
    }

    fun getPrice(): Double{
        var price: Double = 0.0
        for(items in selectedProducts.keys){
            price += items.price
        }
        return price
    }

    fun getSelectedProducts(): Map<Product, Int>{
        return selectedProducts.toMap()
    }
}