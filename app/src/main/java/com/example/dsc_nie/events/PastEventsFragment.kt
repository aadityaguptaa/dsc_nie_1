package com.example.dsc_nie.events

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.dsc_nie.R
import com.example.dsc_nie.adapter.PastEventItemAdapter
import com.example.dsc_nie.databinding.FragmentPastEventsBinding


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

        val adapter = PastEventItemAdapter()
        binding.pastEventsRecyclerView.adapter = adapter

        viewModel.PastEventItemList.observe(viewLifecycleOwner, Observer {
            it?.let{
                adapter.list = it
            }
            makeProgressbarInvisible()
        })

        return binding.root
    }

    private fun makeProgressbarInvisible(){
        binding.PastEventFragmentProgressBar.visibility = View.GONE
        binding.pastEventsRecyclerView.visibility = View.VISIBLE
    }


}