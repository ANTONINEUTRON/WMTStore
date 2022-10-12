package com.tech4dev.wmtstore.ui.cart

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.tech4dev.wmtstore.R
import com.tech4dev.wmtstore.databinding.ActivityCheckoutBinding
import java.util.*

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
        showOrderInfo()

        //setup spinner showing card providers
        setupSpinner()

        binding.makePayment.setOnClickListener {
            if(inputIsValid()) {
                showConfirmationDialog()
                cartViewModel.clearCart()
            }
        }
    }

    private fun inputIsValid(): Boolean {
        val cardNumber = binding.cardNumber.text.toString()
        val cvv = binding.cvv.text.toString()

        if(cardNumber.length < 13){
            Toast.makeText(this, "Invalid Card Number", Toast.LENGTH_LONG).show()
            return false
        }else if(cardNumber.length > 15){
            Toast.makeText(this, "Invalid Card Number", Toast.LENGTH_LONG).show()
            return false
        }else if(cvv.length != 3){
            Toast.makeText(this, "Invalid CVV", Toast.LENGTH_LONG).show()
            return false
        }

        return true
    }

    private fun showConfirmationDialog() {
        AlertDialog.Builder(this)
            .setView(R.layout.layout_payment_successful)
            .setNegativeButton("OK", {dialog, which ->
                this@CheckoutActivity.finish()
            })
            .show()
    }

    private fun setupSpinner() {
        val listOfCardProviders = listOf<String>("Visa", "MasterCard", "Verve")
        binding.cardName.adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            listOfCardProviders
        )
    }

    private fun showOrderInfo() {
        val subtotal: Double = cartViewModel.getPrice()
        val shippingCost: Double = 0.01 * subtotal
        val total = subtotal + shippingCost

        binding.subtotal.text = "$$subtotal"
        binding.shippingCost.text = "$$shippingCost"
        binding.total.text = "$$total"
    }
}