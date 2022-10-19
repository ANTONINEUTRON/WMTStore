package com.tech4dev.wmtstore.ui.notifications

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tech4dev.wmtstore.data.models.Notification
import com.tech4dev.wmtstore.data.repository.NotificationRepository

class NotificationsViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = NotificationRepository(application)

    fun getAllNotifications(): MutableList<Notification> {
        return repo.getAllNotifications()
    }

    fun deleteNotification(notification: Notification){
        repo.deleteNotifiction(notification)
    }
}