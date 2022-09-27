package com.tech4dev.wmtstore.data.repository

import androidx.lifecycle.MutableLiveData
import com.tech4dev.wmtstore.data.firebase.ProductDatasource
import com.tech4dev.wmtstore.data.models.Product

class ProductsRepository {

    fun getProducts(): MutableLiveData<List<Product>>{
        val productDatasource = ProductDatasource()
        return productDatasource.getProductsInfo()
    }
}