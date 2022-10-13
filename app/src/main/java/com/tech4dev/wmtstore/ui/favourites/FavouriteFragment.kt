package com.tech4dev.wmtstore.ui.favourites

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tech4dev.wmtstore.R
import com.tech4dev.wmtstore.databinding.FragmentFavouriteBinding

class FavouriteFragment : Fragment() {
    private lateinit var fragmentFavouriteBinding: FragmentFavouriteBinding
    private lateinit var viewModel: FavouriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(FavouriteViewModel::class.java)

        fragmentFavouriteBinding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return fragmentFavouriteBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getAllFavouriteProducts().observe(viewLifecycleOwner){listOfIds ->
//            fragmentFavouriteBinding.listOfFavourites
        }
    }
}