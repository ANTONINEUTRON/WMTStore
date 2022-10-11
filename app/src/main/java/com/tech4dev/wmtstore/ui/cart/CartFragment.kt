package com.tech4dev.wmtstore.ui.cart

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        cartViewModel.getCartLiveData().observe(viewLifecycleOwner){
            initializeRecyclerView()
            showPriceOnCheckoutButton()
        }

        //set listener to checkout button
        binding.checkout.setOnClickListener{
             val i = Intent(requireActivity(), CheckoutActivity::class.java)
            requireActivity().startActivity(i);
        }
    }

    private fun showPriceOnCheckoutButton() {
        var price = cartViewModel.getPrice()
        binding.checkout.text = "Checkout ($$price)"
    }

    private fun initializeRecyclerView(){
        binding.itemsInCart.layoutManager = LinearLayoutManager(requireContext())
        binding.itemsInCart.adapter = CartAdapter(requireContext(), cartViewModel)
        //Add line separator
        val dividerItemDecoration = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        binding.itemsInCart.addItemDecoration(dividerItemDecoration)
    }
}