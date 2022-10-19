package com.tech4dev.wmtstore.data.notification_provider

import android.content.Context
import android.content.SharedPreferences
import com.tech4dev.wmtstore.data.models.Notification

class NotificationFromSharedpreference(context: Context) {
    private val sharedPreference = context.getSharedPreferences("NOTIFICATIONS", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreference.edit()

    fun getAllNotifications(): MutableList<Notification>{
        //declare list that will be returned
        val listOfNotification = mutableListOf<Notification>()

        //get list of keys
        val listOfKeys: List<String> = sharedPreference.all.keys.toList()

        //for each key create a corresponding notification object
        for(key in listOfKeys){
            val time: Long = key.toLong()
            val message: String = sharedPreference.getString(key, "") ?: ""
            val notification = Notification(time, message)

            //add to the list that will be returned
            listOfNotification.add(notification)
        }

        //return the list to the user
        return listOfNotification
    }

    fun deleteNotification(notification: Notification){
        //call notification editor
        //remove from list according to key

        editor.remove(notification.time.toString())
        editor.commit()
    }

    fun saveNotification(notification: Notification) {
        editor.putString(notification.time.toString(), notification.message)
        editor.commit()
    }
}