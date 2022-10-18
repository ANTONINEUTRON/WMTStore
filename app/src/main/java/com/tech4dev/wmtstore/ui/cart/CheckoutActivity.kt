package com.tech4dev.wmtstore.ui.cart

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.DialogInterface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModelProvider
import com.tech4dev.wmtstore.R
import com.tech4dev.wmtstore.data.models.Product
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
                showNotification(
                    message= "You just paid for some items worth $${cartViewModel.getPrice()}"
                )
                cartViewModel.clearCart()
            }
        }
    }

    //Show user payment notification
    private fun showNotification(message: String) {
        val id = "payment_successful"

        //Create notification channel
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //Declare variables
            val channelName = "Payment Confirmation"
            val description = "User just made a successful payment"
            val importance: Int = NotificationManager.IMPORTANCE_DEFAULT

            //created the notification channel
            val notificationChannel = NotificationChannel(id, channelName, importance)
            notificationChannel.description = description

            //Assign the channel to the device notification manager
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }

        //Create the notification builder
        val builder = NotificationCompat.Builder(this, id)
        builder.setSmallIcon(R.mipmap.ic_launcher)
        builder.setContentTitle("Payment Made")
        builder.setContentText(message)

        val notificationManagerCompat = NotificationManagerCompat.from(this)
        notificationManagerCompat.notify(12333, builder.build())

        saveNotification(message)
    }

    private fun saveNotification(message: String) {

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
            .setPositiveButton("OK", {dialog, which ->
                this@CheckoutActivity.finish()
            })
            .setCancelable(false)
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