package com.tech4dev.wmtstore.data.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import com.tech4dev.wmtstore.data.models.Product

class ProductDatasource {
    //Get reference to firestore database
    private val db = Firebase.firestore


    //Create function that will fetch the producta from the database
    fun getProductsInfo(): MutableLiveData<List<Product>>{
        val productLivedata = MutableLiveData<List<Product>>()

        db.collection("products")
            .get()
            .addOnSuccessListener { documents ->
                val listOfProducts: List<Product> = documents.toObjects(Product::class.java)
                productLivedata.value = listOfProducts
            }
            .addOnFailureListener{ error ->
                Log.e("Firebase Error", error.message.toString())
            }

        return productLivedata
    }

    //This will take a list of ids
    //and return a livedata of products which can be observed
    fun getProductFromIds(productIds: List<String>): MutableLiveData<List<Product>>{
        val productLivedata = MutableLiveData<List<Product>>(listOf())
        if(productIds.isEmpty()) return productLivedata

        db.collection("products")
            .whereIn("id", productIds)
            .get()
            .addOnSuccessListener { documents ->
                val listOfProducts: List<Product> = documents.toObjects(Product::class.java)
                productLivedata.value = listOfProducts
            }
            .addOnFailureListener{ error ->
                Log.e("Firebase Error", error.message.toString())
            }

        return productLivedata
    }
}