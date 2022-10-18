package com.tech4dev.wmtstore.ui.notifications

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tech4dev.wmtstore.R
import com.tech4dev.wmtstore.data.models.Notification
import java.util.*

class NotificationsAdapter(val context: Context, val listOfNotifications: MutableList<Notification>)
    : RecyclerView.Adapter<NotificationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.layout_notifications, parent, false)
        return NotificationViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val notification = listOfNotifications[position]

        //show date
        val date = Date(notification.time)
        holder.date.text = date.toString()

        //show message
        holder.message.text = notification.message
    }

    override fun getItemCount(): Int = listOfNotifications.size
}

class NotificationViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val date: TextView = itemView.findViewById(R.id.date)
    val message: TextView = itemView.findViewById(R.id.message)
}