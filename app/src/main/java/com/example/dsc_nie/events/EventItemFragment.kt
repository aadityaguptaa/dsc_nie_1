package com.example.dsc_nie.events

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.dsc_nie.R
import com.example.dsc_nie.databinding.FragmentEventItemBinding


class EventItemFragment : Fragment() {

    //For reference to layout binding
    lateinit var binding: FragmentEventItemBinding

    lateinit var viewModel: EventItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_event_item, container, false
        )

        viewModel = ViewModelProviders.of(this).get(EventItemViewModel::class.java)
        val filename = EventItemFragmentArgs.fromBundle(requireArguments()).imageName
        Log.i("Category", filename)

        updateUI(container)
        viewModel.getChild(filename)

        return binding.root
    }



     fun makeProgressbarInvisible() {
        binding.EventFragmentProgressBar.visibility = View.GONE
        binding.EventItemCoordinatorLayout.visibility = View.VISIBLE
    }

     fun updateUI(container: ViewGroup?){

         viewModel.eventTitle.observe(viewLifecycleOwner, Observer{ newTitle->
             container!!.findViewById<TextView>(R.id.EventTitleTextView).text = newTitle
         })

         viewModel.eventDate.observe(viewLifecycleOwner, Observer{ newDate->
             container!!.findViewById<TextView>(R.id.EventDateTextView).text = newDate
         })

         viewModel.eventLocation.observe(viewLifecycleOwner, Observer{ newLocation->
             container!!.findViewById<TextView>(R.id.EventLocationTextView).text = newLocation
         })

         viewModel.eventDetails.observe(viewLifecycleOwner, Observer{ newDetails->
             container!!.findViewById<TextView>(R.id.EventDetailsTextView).text = newDetails
         })

         viewModel.eventImage.observe(viewLifecycleOwner, Observer { newImage ->
             binding.EventItemImageView.setImageBitmap(newImage)
             makeProgressbarInvisible()
         })
     }



}