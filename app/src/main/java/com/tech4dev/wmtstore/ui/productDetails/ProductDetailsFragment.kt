package com.tech4dev.wmtstore.ui.productDetails

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.tech4dev.wmtstore.R
import com.tech4dev.wmtstore.data.models.Product
import com.tech4dev.wmtstore.databinding.FragmentProductDetailsListDialogBinding

class ProductDetailsFragment(val product: Product) : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentProductDetailsListDialogBinding
    private lateinit var productDetailsViewModel: ProductDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        productDetailsViewModel = ViewModelProvider(this).get(ProductDetailsViewModel::class.java)
        binding = FragmentProductDetailsListDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //Load image
        Glide.with(requireContext())
            .load(product.image)
            .into(binding.productImage)

        //show details
        binding.productName.text = product.name
        binding.price.text = "$${product.price}"
        binding.seller.text = product.seller
        binding.size.text = product.size

        binding.addToCart.setOnClickListener {
            //save the id to sharedpreference
            productDetailsViewModel.saveToCart(product)

            //alert user that it has been added to cart
            Toast.makeText(requireContext(), "Saved To Cart", Toast.LENGTH_LONG).show()
        }
    }
}