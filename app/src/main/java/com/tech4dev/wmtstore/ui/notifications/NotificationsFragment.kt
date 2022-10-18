package com.tech4dev.wmtstore.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tech4dev.wmtstore.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private lateinit var binding: FragmentNotificationsBinding
    private lateinit var notificationsViewModel: NotificationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        binding = FragmentNotificationsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val listOfNotifications = notificationsViewModel.getAllNotifications()

        binding.listOfNotification.layoutManager = LinearLayoutManager(requireContext())
        binding.listOfNotification.adapter = NotificationsAdapter(requireContext(), listOfNotifications)
    }
}