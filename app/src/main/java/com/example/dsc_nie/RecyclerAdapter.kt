package com.example.dsc_nie

import android.os.Bundle
import android.text.Html
import android.text.Layout
import android.text.method.LinkMovementMethod
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import androidx.navigation.fragment.FragmentNavigatorExtras
import android.view.View
import androidx.fragment.app.Fragment
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.dsc_nie.teams_intro.TechTeamFragmentDirections

class RecyclerAdapter( private var titles: List<String>, private var details: List<String>, private var images: List<Int>, private var description: List<String>, private var instagramLink: List<String>, private var linkedinLink: List<String>, private var emailLink: List<String>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val itemTitle: TextView = itemView.findViewById(R.id.CoreName)
/*
        val itemDetails: TextView = itemView.findViewById(R.id.CorePosition)
*/
        val itemPicture: ImageView = itemView.findViewById(R.id.CoreImage)
/*
        val itemDescription: TextView = itemView.findViewById(R.id.CoreDescription)
*/
        val cardView: CardView = itemView.findViewById(R.id.pastEventRecyclerCardView)


/*
        val expandableLayout: LinearLayout = itemView.findViewById(R.id.expandableLayout)
*/
        /*val coreInstagram: TextView = itemView.findViewById(R.id.CoreInstagramLink)
        val coreLinkedin: TextView = itemView.findViewById(R.id.CoreLinkedinLink)
        val coreEmail: TextView = itemView.findViewById(R.id.CoreEmailLink)*/


        /*init {

            itemView.setOnClickListener {
                if (expandableLayout.visibility == View.GONE) {
                    TransitionManager.beginDelayedTransition(cardView, AutoTransition())
                    expandableLayout.visibility = View.VISIBLE
                } else {
                    TransitionManager.beginDelayedTransition(cardView, AutoTransition())
                    expandableLayout.visibility = View.GONE
                }
            }
        }*/
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var layoutId = R.layout.team_item_layout_one
        if(viewType == 1){
            layoutId = R.layout.team_item_layout_two
        }else if(viewType == 2){
            layoutId = R.layout.team_item_layout_three
        }
        val v: View = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle.text = titles[position]
/*
        holder.itemDetails.text = details[position]
*/
        holder.itemPicture.setImageResource(images[position])
        holder.itemPicture.transitionName = position.toString()

/*
        holder.itemDescription.text = description[position]
*/

        //this shit is to turn TextViews into hyperlinks... idk why it's this complex ;-;
        /*holder.coreEmail.movementMethod = LinkMovementMethod.getInstance()
        holder.coreInstagram.movementMethod = LinkMovementMethod.getInstance()
        holder.coreLinkedin.movementMethod = LinkMovementMethod.getInstance()
        holder.coreInstagram.text = Html.fromHtml("<a href=\"" + instagramLink[position] + "\">" + "Instagram" + "</a>", Html.FROM_HTML_MODE_COMPACT)
        holder.coreLinkedin.text = Html.fromHtml("<a href=\"" + linkedinLink[position] + "\">" + "Linkedin" + "</a>", Html.FROM_HTML_MODE_COMPACT)
        holder.coreEmail.text = Html.fromHtml("<a href=\"" + emailLink[position] + "\">" + "Email" + "</a>", Html.FROM_HTML_MODE_COMPACT)
*/
        holder.cardView.setOnClickListener { view: View ->
            val extras = FragmentNavigatorExtras(
                view.findViewById<ImageView>(R.id.CoreImage) to "ababab"
            )
            view.findNavController().navigate(TechTeamFragmentDirections.actionTechTeamFragmentToTeamMemberDetailsFragment(images[position]),  extras)
        }
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    override fun getItemViewType(position: Int): Int {
        return position % 3
    }

}