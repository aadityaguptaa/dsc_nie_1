package com.example.dsc_nie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.dsc_nie.HomeFragmentDirections
import com.example.dsc_nie.R
import com.example.dsc_nie.events.PastEventsFragmentDirections
import com.example.dsc_nie.model.EventItem
import com.squareup.picasso.Picasso

class PastEventItemAdapter(private val context: Context, private val Events: List<EventItem>): RecyclerView.Adapter<PastEventItemAdapter.EventsViewHolder>() {

    class EventsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        public var eventImage: ImageView? = null
        init {
            eventImage = itemView.findViewById(R.id.PastEventImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        return PastEventItemAdapter.EventsViewHolder(LayoutInflater.from(context).inflate(R.layout.past_event_item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        Picasso.get().load(Events[position].imageUrl).into(holder.eventImage)
        var fileName = Events[position].imageUrl.replaceAfterLast('.', "").takeLast(7).removeSuffix(".")
        holder.eventImage!!.setOnClickListener{view: View ->
            Navigation.findNavController(view).navigate(PastEventsFragmentDirections.actionPastEventsFragmentToEventItemFragment(fileName))
        }
    }

    override fun getItemCount(): Int {
        return Events.size
    }

}