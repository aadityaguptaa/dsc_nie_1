package com.example.dsc_nie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.dsc_nie.R
import com.example.dsc_nie.model.CategoryItem

class CategoryItemAdapter(private val context: Context?, private val categoryItem: List<CategoryItem>): RecyclerView.Adapter<CategoryItemAdapter.CategoryItemViewHolder>() {

    public var parentPosition: Int = 0
    class CategoryItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var itemImage: ImageView

        init {
            itemImage = itemView.findViewById(R.id.item_image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        return CategoryItemViewHolder(LayoutInflater.from(context).inflate(R.layout.cat_row_items, parent, false))
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        holder.itemImage.setImageResource(categoryItem[position].imageUrl)
        if( categoryItem[position].itemId == 6){
            holder.itemImage.setOnClickListener{view: View ->
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_aboutUs)
            }
        }else if(categoryItem[position].itemId == 8){
            holder.itemImage.setOnClickListener{view: View ->
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_techTeamFragment)
            }
        }else if(categoryItem[position].itemId == 9){
            holder.itemImage.setOnClickListener{view: View ->
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_managementTeamFragment)
            }
        }
    }

    override fun getItemCount(): Int {
        return categoryItem.size
    }

}