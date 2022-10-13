package com.tech4dev.wmtstore.ui.productDetails

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.tech4dev.wmtstore.R
import com.tech4dev.wmtstore.data.models.Product
import com.tech4dev.wmtstore.databinding.FragmentProductDetailsListDialogBinding
import com.tech4dev.wmtstore.ui.favourites.FavouriteViewModel

class ProductDetailsFragment(val product: Product) : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentProductDetailsListDialogBinding
    private lateinit var productDetailsViewModel: ProductDetailsViewModel
    private lateinit var favoriteViewModel: FavouriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        productDetailsViewModel = ViewModelProvider(this).get(ProductDetailsViewModel::class.java)
        favoriteViewModel = ViewModelProvider(this).get(FavouriteViewModel::class.java)

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

        //set listener to add to cart button
        binding.addToCart.setOnClickListener {
            //save the id to sharedpreference
            productDetailsViewModel.saveToCart(product)

            //alert user that it has been added to cart
            Toast.makeText(requireContext(), "Saved To Cart", Toast.LENGTH_LONG).show()

            //Close Bottom Sheet
            this.dismiss()
        }

        //set listener to favourite button
        binding.selectAsFavourite.setOnClickListener{
            toggleFavoriteIcon()
        }

        //show appropriete favorite icon
        if(favoriteViewModel.isFavorite(product.id!!)){
            binding.selectAsFavourite.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
        }
    }

    private fun toggleFavoriteIcon() {
        //Check if product exists in favorite data store / provider
        if (favoriteViewModel.isFavorite(product.id!!)) {
            //remove item from storage
            favoriteViewModel.removeFromFavourite(product.id!!)
            //show icon as not selected
            binding.selectAsFavourite.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)
        } else {
            //add item to storage
            favoriteViewModel.addToFavourite(product.id!!)
            //show icon as selected
            binding.selectAsFavourite.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
        }
    }
}