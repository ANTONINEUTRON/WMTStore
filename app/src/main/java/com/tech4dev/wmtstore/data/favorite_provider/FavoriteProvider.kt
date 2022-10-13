package com.tech4dev.wmtstore.data.favorite_provider

import androidx.lifecycle.MutableLiveData

interface FavoriteProvider {
    fun addFavorite(productId: String)

    fun removeFavorite(productId: String)

    fun isFavorite(productId: String): Boolean

    fun getFavoriteItems(): MutableLiveData<List<String>>
}