package com.tech4dev.wmtstore.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.tech4dev.wmtstore.databinding.FragmentCartBinding

class CartFragment : Fragment() {
    private lateinit var cartViewModel: CartViewModel
    private lateinit var binding: FragmentCartBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)

        binding = FragmentCartBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var productsInCart = cartViewModel.getProducts()
        Toast.makeText(requireContext(), "There are ${productsInCart.size} in Cart", Toast.LENGTH_LONG).show()
    }
}