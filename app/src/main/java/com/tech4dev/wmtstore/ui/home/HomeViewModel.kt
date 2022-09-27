package com.tech4dev.wmtstore.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tech4dev.wmtstore.data.models.Product
import com.tech4dev.wmtstore.data.repository.ProductsRepository

class HomeViewModel : ViewModel() {
    private val productsRepository = ProductsRepository()
    private val products: MutableLiveData<List<Product>> = productsRepository.getProducts()

    fun getAllProducts(): MutableLiveData<List<Product>> {
        return products
    }
}