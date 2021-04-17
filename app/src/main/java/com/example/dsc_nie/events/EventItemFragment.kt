package com.example.dsc_nie.events

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.dsc_nie.R
import com.example.dsc_nie.databinding.EventItemBottomSheetBinding
import com.example.dsc_nie.databinding.FragmentEventItemBinding
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import java.lang.Exception


class EventItemFragment : Fragment() {
    lateinit var binding: FragmentEventItemBinding

    lateinit var EventImage: ImageView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_event_item, container, false
        )

        val filename = EventItemFragmentArgs.fromBundle(requireArguments()).imageName
        Log.i("Category", filename)
        EventImage = binding.EventItemImageView

        try {
            var firebaseDatabase = FirebaseDatabase.getInstance().reference.child(filename).get().addOnCompleteListener {it ->
                val Title = it.result!!.child("Title").getValue() as String
                val Date = it.result!!.child("Date").getValue() as String
                val Details = it.result!!.child("Details").getValue() as String
                val Location = it.result!!.child("Location").getValue() as String
                val ImageURL = it.result!!.child("ImageURL").getValue() as String
                Picasso.get().load(ImageURL).into(EventImage)
                binding.EventFragmentProgressBar.visibility = View.GONE
                binding.EventItemCoordinatorLayout.visibility = View.VISIBLE
                container!!.findViewById<TextView>(R.id.EventLocationTextView).text = Location
                container.findViewById<TextView>(R.id.EventDateTextView).text = Date
                container.findViewById<TextView>(R.id.EventDetailsTextView).text = Details
                container.findViewById<TextView>(R.id.EventTitleTextView).text = Title

            }

        }catch (e: Exception){
            Toast.makeText(
                context,
                "Something went wrong, please try again/n Exception: "+ e.cause,
                Toast.LENGTH_SHORT
            ).show()
        }


        return binding.root
    }


}