package com.tech4dev.wmtstore.data.repository

import android.content.Context
import com.tech4dev.wmtstore.data.models.Notification
import com.tech4dev.wmtstore.data.notification_provider.NotificationFromSharedpreference

class NotificationRepository(context: Context) {
    private val notificationProvider = NotificationFromSharedpreference(context)

    fun getAllNotifications(): MutableList<Notification>{
        return notificationProvider.getAllNotifications()
    }

    fun deleteNotifiction(notification: Notification){
        notificationProvider.deleteNotification(notification)
    }
}