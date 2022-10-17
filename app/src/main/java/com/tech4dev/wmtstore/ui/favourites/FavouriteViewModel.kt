package com.tech4dev.wmtstore.ui.favourites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tech4dev.wmtstore.data.models.Product
import com.tech4dev.wmtstore.data.repository.FavoriteRepository

class FavouriteViewModel(application: Application) : AndroidViewModel(application) {
    private val favoriteRepository = FavoriteRepository(application)

    fun isFavorite(productId: String): Boolean{
        return favoriteRepository.isFavorite(productId)
    }

    fun removeFromFavourite(id: String) {
        favoriteRepository.removeProduct(id)
    }

    fun addToFavourite(id: String) {
        favoriteRepository.addProduct(id)
    }

    fun getAllFavouriteProducts(): MutableLiveData<List<String>>{
        return favoriteRepository.getAllFavorites()
    }

    fun getProductFromIds(listOfIds: List<String>): MutableLiveData<List<Product>> {
        return favoriteRepository.getProductFromIds(listOfIds)
    }
}