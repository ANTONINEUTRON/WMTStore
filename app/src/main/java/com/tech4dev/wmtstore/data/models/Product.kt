package com.tech4dev.wmtstore.data.models

data class Product(
    val id: String,
    val image: String,
    val name: String,
    val price: Double,
    val seller: String,
    val size: String
)
