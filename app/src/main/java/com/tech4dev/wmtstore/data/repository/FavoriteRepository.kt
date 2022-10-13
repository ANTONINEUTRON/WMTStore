package com.tech4dev.wmtstore.data.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.tech4dev.wmtstore.data.favorite_provider.FavoriteProvider
import com.tech4dev.wmtstore.data.favorite_provider.SharedPreferenceFavorite

class FavoriteRepository(context: Context) {
    private val favoriteProvider: FavoriteProvider = SharedPreferenceFavorite(context)

    fun isFavorite(productId: String): Boolean {
        return favoriteProvider.isFavorite(productId)
    }

    fun removeProduct(id: String) {
        favoriteProvider.removeFavorite(id)
    }

    fun addProduct(id: String) {
        favoriteProvider.addFavorite(id)
    }

    fun getAllFavorites(): MutableLiveData<List<String>>{
        return favoriteProvider.getFavoriteItems()
    }
}