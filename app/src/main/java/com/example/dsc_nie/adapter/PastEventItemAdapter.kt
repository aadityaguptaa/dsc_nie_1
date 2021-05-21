package com.example.dsc_nie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.dsc_nie.events.PastEventsFragmentDirections
import com.example.dsc_nie.model.EventItem
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import com.example.dsc_nie.databinding.PastEventItemLayoutBinding

class PastEventItemAdapter(): RecyclerView.Adapter<PastEventItemAdapter.EventsViewHolder>() {

    var list = listOf<EventItem>()
    set(value){
        field = value
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        return EventsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        var item = list[position]
        CoroutineScope(Dispatchers.Main).launch {
            holder.loadImage(item)
        }
    }


    override fun getItemCount() = list.size


    class EventsViewHolder(private val binding:  PastEventItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        suspend fun loadImage(item: EventItem){
            Picasso.get().load(item.imageUrl).into(binding.PastEventImageView, object : Callback {
                override fun onSuccess() {
                    val fileName = getFileName(item)
                    binding.PastEventImageView.setOnClickListener { view: View ->
                        Navigation.findNavController(view).navigate(
                            PastEventsFragmentDirections.actionPastEventsFragmentToEventItemFragment(
                                fileName
                            )
                        )
                    }
                    binding.pastEventItemProgressbar.visibility = View.GONE
                    binding.pastEventRecyclerItem.visibility = View.VISIBLE
                }

                override fun onError(e: Exception?) {
                    TODO("Not yet implemented")
                }

            })
        }

        fun getFileName(item: EventItem): String {
            return item.imageUrl
                .replaceAfterLast('.', "")
                .takeLast(7)
                .removeSuffix(".")
        }

        companion object {
            fun from(parent: ViewGroup): EventsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PastEventItemLayoutBinding.inflate(layoutInflater, parent, false)
                return EventsViewHolder(binding)
            }
        }
    }

}