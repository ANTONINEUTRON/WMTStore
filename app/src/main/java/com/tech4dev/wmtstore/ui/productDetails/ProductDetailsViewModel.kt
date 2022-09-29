package com.tech4dev.wmtstore.ui.productDetails

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel

class ProductDetailsViewModel(application: Application) : AndroidViewModel(application){
    private val context: Context = application.applicationContext

    fun saveToCart(id: String){
        //access the sharedpreference
        //get access to the editor
        //save the id
    }
}