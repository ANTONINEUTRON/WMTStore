package com.tech4dev.wmtstore.ui.cart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.tech4dev.wmtstore.R
import com.tech4dev.wmtstore.databinding.ActivityCheckoutBinding

class CheckoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheckoutBinding
    private lateinit var cartViewModel: CartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //initialize viewmodel
        cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)

        //calculate subtotal, shipping cost and total
        val subtotal: Double = cartViewModel.getPrice()
        val shippingCost: Double = 0.01*subtotal
        val total = subtotal + shippingCost

        binding.subtotal.text = "$$subtotal"
        binding.shippingCost.text = "$$shippingCost"
        binding.total.text = "$$total"

        //setup spinner showing card providers
    }
}