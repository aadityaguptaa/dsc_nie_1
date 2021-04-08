package com.example.dsc_nie
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter (private var titles: List<String>, private var details: List<String>, private var images:List<Int>, private var description: List<String>, private var instagramLink: List<String>, private var facebookLink: List<String>, private var emailLink: List<String>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){


        val itemTitle: TextView = itemView.findViewById(R.id.CoreName)
        val itemDetails: TextView = itemView.findViewById(R.id.CorePosition)
        val itemPicture: ImageView = itemView.findViewById(R.id.CoreImage)
        val itemDescription: TextView = itemView.findViewById(R.id.CoreDescription)
        val cardView: CardView = itemView.findViewById(R.id.cardView)
        val expandableLayout: LinearLayout = itemView.findViewById(R.id.expandableLayout)
        val coreInstagram: TextView = itemView.findViewById(R.id.CoreInstagramLink)
        val coreFacebook: TextView = itemView.findViewById(R.id.CoreFacebookLink)
        val coreEmail: TextView = itemView.findViewById(R.id.CoreEmailLink)



        init{

            itemView.setOnClickListener {
                if (expandableLayout.visibility == View.GONE) {
                    TransitionManager.beginDelayedTransition(cardView, AutoTransition())
                    expandableLayout.visibility = View.VISIBLE
                } else {
                    TransitionManager.beginDelayedTransition(cardView, AutoTransition())
                    expandableLayout.visibility = View.GONE
                }
            }
            /*itemView.setOnClickListener { v: View ->
                val position: Int = adapterPosition
                Toast.makeText(itemView.context, "You clicked on item # ${position + 1}", Toast.LENGTH_SHORT).show()
            }*/
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.team_item_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle.text = titles[position]
        holder.itemDetails.text = details[position]
        holder.itemPicture.setImageResource(images[position])
        holder.itemDescription.text = description[position]
        holder.coreInstagram.text = instagramLink[position]
        holder.coreFacebook.text = facebookLink[position]
        holder.coreEmail.text = emailLink[position]

    }

    override fun getItemCount(): Int {
        return titles.size
    }

}