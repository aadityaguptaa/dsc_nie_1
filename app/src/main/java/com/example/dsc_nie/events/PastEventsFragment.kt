package com.example.dsc_nie.events

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dsc_nie.R
import com.example.dsc_nie.adapter.PastEventItemAdapter
import com.example.dsc_nie.adapter.UpcomingEventsRecyclerAdapter
import com.example.dsc_nie.databinding.FragmentHomeBinding
import com.example.dsc_nie.databinding.FragmentPastEventsBinding
import com.example.dsc_nie.model.AllCategory
import com.example.dsc_nie.model.CategoryItem
import com.example.dsc_nie.model.EventItem
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ListResult
import com.google.firebase.storage.StorageReference


class PastEventsFragment : Fragment() {

    //Recycler stuff
    private var pastEventsRecycler: RecyclerView? = null
    private var pastEventsRecyclerAdapter: PastEventItemAdapter? = null

    //binding and view model               I know I am bad at writing comments! ;-;
    lateinit var binding: FragmentPastEventsBinding
    lateinit var viewModel: PastEventsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_past_events, container, false
        )

        viewModel = ViewModelProviders.of(this).get(PastEventsViewModel::class.java)
        viewModel.done.observe(viewLifecycleOwner, Observer { newList ->
            setPastEventRecycler(viewModel.PastEventItemList)
            makeProgressbarInvisible()
        })

        return binding.root
    }

    private fun setPastEventRecycler(Events: List<EventItem>){
        pastEventsRecycler = binding.pastEventsRecyclerView
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        pastEventsRecycler!!.layoutManager = layoutManager
        pastEventsRecyclerAdapter = PastEventItemAdapter(requireContext(), Events)
        pastEventsRecycler!!.adapter = pastEventsRecyclerAdapter
    }

    private fun makeProgressbarInvisible(){
        binding.PastEventFragmentProgressBar.visibility = View.GONE
        binding.pastEventsRecyclerView.visibility = View.VISIBLE
    }


}