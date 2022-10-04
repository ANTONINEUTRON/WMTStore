package com.tech4dev.wmtstore.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tech4dev.wmtstore.data.models.Product
import com.tech4dev.wmtstore.data.repository.CartRepository

class CartViewModel : ViewModel() {

    fun getProducts(): List<Product>{
        return CartRepository.getSelectedProducts().keys.toList()
    }

    fun increaseQuaantity(){

    }

    fun decreaseQuantity(){

    }
}