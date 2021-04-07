package com.example.dsc_nie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dsc_nie.R
import com.example.dsc_nie.model.AllCategory
import com.example.dsc_nie.model.CategoryItem

class UpcomingEventsRecyclerAdapter(private val context: Context?, private val allCategory: List<AllCategory>):
    RecyclerView.Adapter<UpcomingEventsRecyclerAdapter.EventsViewHolder>() {

    class EventsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        public var eventTitle: TextView? = null
        var itemRecycler:RecyclerView? = null

        init {
            eventTitle = itemView.findViewById(R.id.EventTitleView)
            itemRecycler = itemView.findViewById(R.id.cat_item_recycler)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        return EventsViewHolder(LayoutInflater.from(context).inflate(R.layout.events_recycler_row_item, parent, false))
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        if(allCategory[position].categoryTitle.length==0 || allCategory[position].categoryTitle=="") holder.eventTitle!!.setVisibility(View.GONE) else holder.eventTitle!!.setVisibility(View.VISIBLE)
        holder.itemRecycler!!.setOnClickListener { view: View ->
        }
        holder.eventTitle!!.text = allCategory[position].categoryTitle
        setCatItemRecycler(holder.itemRecycler!!, allCategory[position].categoryItem)

    }

    private fun setCatItemRecycler(recyclerView: RecyclerView, categoryItem: List<CategoryItem>){
        val itemRecyclerAdapter = CategoryItemAdapter(context, categoryItem)
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        recyclerView.adapter = itemRecyclerAdapter
    }


    override fun getItemCount(): Int {
        return allCategory.size
    }
}