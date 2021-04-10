package com.example.dsc_nie.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.dsc_nie.R
import com.example.dsc_nie.model.CategoryItem
import com.squareup.picasso.Picasso
import kotlinx.coroutines.delay

class CategoryItemAdapter(private val context: Context?, private val categoryItem: List<CategoryItem>): RecyclerView.Adapter<CategoryItemAdapter.CategoryItemViewHolder>() {

    public var parentPosition: Int = 0
    class CategoryItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var itemImage: ImageView
        var progressBar: ProgressBar

        init {
            itemImage = itemView.findViewById(R.id.item_image)
            progressBar = itemView.findViewById(R.id.categoryRowProgressBar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        return CategoryItemViewHolder(LayoutInflater.from(context).inflate(R.layout.cat_row_items, parent, false))
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        Log.i("position", "$position" + categoryItem[position].imageURL)
        if(categoryItem[position].imageID == -1){
            Picasso.get().load(categoryItem[position].imageURL).into(holder.itemImage)
            Log.i("position", "executed")
        }else {
            holder.itemImage.setImageResource(categoryItem[position].imageID)
        }
        holder.progressBar.visibility = View.INVISIBLE
        if( categoryItem[position].itemId == R.string.aboutFragmentId){
            holder.itemImage.setOnClickListener{view: View ->
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_aboutUs)
            }
        }else if(categoryItem[position].itemId == R.string.techTeamId){
            holder.itemImage.setOnClickListener{view: View ->
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_techTeamFragment)
            }
        }else if(categoryItem[position].itemId == R.string.managementTeamId){
            holder.itemImage.setOnClickListener{view: View ->
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_managementTeamFragment)
            }
        }
    }

    override fun getItemCount(): Int {
        return categoryItem.size
    }

}