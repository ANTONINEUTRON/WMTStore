package com.tech4dev.wmtstore.ui.account

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.google.firebase.auth.FirebaseAuth
import com.tech4dev.wmtstore.R

class AccountFragment : Fragment() {
    private lateinit var viewModel: AccountViewModel
    private val siginLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { result ->
        if (result != null) {
            if (result.resultCode == Activity.RESULT_OK) {
                //The user successfully signed in
                showProfile()
            } else {
                Toast.makeText(requireContext(), "An error ocurred, Try again", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showProfile() {
        childFragmentManager.beginTransaction()
            .replace(R.id.container, ProfileFragment())
            .commitNow()

        container.visibility = View.GONE
    }

    private lateinit var container: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        container = view.findViewById(R.id.initial_layout)

        var user = FirebaseAuth.getInstance().currentUser
        if(user != null){
            showProfile()
        }

        val signingButton: Button = view.findViewById(R.id.signin_btn)
        signingButton.setOnClickListener{
            val providers = listOf(
                AuthUI.IdpConfig.EmailBuilder().build(),
                AuthUI.IdpConfig.GoogleBuilder().build()
            )
            val intent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build()

            //start the sutrhentication flow
            siginLauncher.launch(intent)
        }

    }
}