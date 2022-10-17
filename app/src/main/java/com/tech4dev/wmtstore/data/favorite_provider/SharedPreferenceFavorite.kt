package com.tech4dev.wmtstore.data.favorite_provider

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData

class SharedPreferenceFavorite(context: Context) : FavoriteProvider {
    //Created our sharedpreference file
    private val favoriteStorage: SharedPreferences = context.getSharedPreferences("FAVORITES", Context.MODE_PRIVATE)
    //Create an editor instance
    private val editor: SharedPreferences.Editor = favoriteStorage.edit()
    //Create an instance of a live data
    private val livedata = MutableLiveData<List<String>>()

    override fun addFavorite(productId: String) {
        editor.putString(productId,productId)
        editor.commit()

        notifyObservers()
    }

    override fun removeFavorite(productId: String) {
        editor.remove(productId)
        editor.commit()

        notifyObservers()
    }

    override fun isFavorite(productId: String): Boolean {
        val item: String? = favoriteStorage.getString(productId, "")
        if(item.isNullOrEmpty()){
            return false
        }
        return true
    }

    override fun getFavoriteItems(): MutableLiveData<List<String>> {
        notifyObservers()
        return livedata
    }

    private fun notifyObservers(){
        livedata.value = favoriteStorage.all.keys.toList()
    }
}