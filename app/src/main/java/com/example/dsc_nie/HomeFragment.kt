package com.example.dsc_nie

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.View.INVISIBLE
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dsc_nie.adapter.UpcomingEventsRecyclerAdapter
import com.example.dsc_nie.databinding.FragmentHomeBinding
import com.example.dsc_nie.model.AllCategory
import com.example.dsc_nie.model.CategoryItem
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ListResult
import com.google.firebase.storage.StorageReference


class HomeFragment : Fragment() {

    private var mainCategoryRecycler: RecyclerView? = null
    private var mainRecyclerAdapter: UpcomingEventsRecyclerAdapter? = null
    lateinit var binding: FragmentHomeBinding
    lateinit var navController: NavController
    lateinit var viewModel: HomeFragmentViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )
        viewModel = ViewModelProviders.of(this).get(HomeFragmentViewModel::class.java)

        //navController = Navigation.findNavController(requireView())

        viewModel.done.observe(viewLifecycleOwner, Observer { done->
            setMainCategoryRecycler(viewModel.allCategory)
            binding.homeFragmentProgressBar.visibility = View.INVISIBLE
        })


        setHasOptionsMenu(true)
        //requireActivity().actionBar!!.isHideOnContentScrollEnabled = true

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.top_app_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        navController = Navigation.findNavController(requireView())
        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item)
    }
    private fun setMainCategoryRecycler(allCategory: List<AllCategory>){
        mainCategoryRecycler = binding.EventsRecyclerView
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        mainCategoryRecycler!!.layoutManager = layoutManager
        mainRecyclerAdapter = UpcomingEventsRecyclerAdapter(context, allCategory)
        mainCategoryRecycler!!.adapter = mainRecyclerAdapter
    }



}