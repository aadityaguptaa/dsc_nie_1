package com.example.dsc_nie

import android.os.Build
import android.os.Bundle
import android.view.*
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dsc_nie.adapter.UpcomingEventsRecyclerAdapter
import com.example.dsc_nie.databinding.FragmentHomeBinding
import com.example.dsc_nie.model.AllCategory


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

        //to set Action Bar
        (activity as AppCompatActivity).setSupportActionBar(binding.appBar)
        binding.appBar.setNavigationOnClickListener(
            NavigationIconClickListener(
                requireActivity(),
                binding.homeFragmentNestedScrollView,
                AccelerateDecelerateInterpolator(),
                ContextCompat.getDrawable(requireContext(), R.drawable.shr_branded_menu), // Menu open icon
                ContextCompat.getDrawable(requireContext(), R.drawable.shr_close_menu)))


        viewModel = ViewModelProviders.of(this).get(HomeFragmentViewModel::class.java)

        //navController = Navigation.findNavController(requireView())

        viewModel.done.observe(viewLifecycleOwner, Observer { done->
            setMainCategoryRecycler(viewModel.allCategory)
            binding.homeFragmentProgressBar.visibility = View.GONE
            binding.homeFragmentBackdrop.visibility = View.VISIBLE
            binding.homeFragmentNestedScrollView.visibility = View.VISIBLE
        })


        setHasOptionsMenu(true)
        //requireActivity().actionBar!!.isHideOnContentScrollEnabled = true

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.homeFragmentNestedScrollView.background = context?.getDrawable(R.drawable.shr_product_grid_background_shape)
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.dsc_toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, menuInflater)
    }


    /*override fun onOptionsItemSelected(item: MenuItem): Boolean {
        navController = Navigation.findNavController(requireView())
        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item)
    }*/
    private fun setMainCategoryRecycler(allCategory: List<AllCategory>){
        mainCategoryRecycler = binding.EventsRecyclerView
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        mainCategoryRecycler!!.layoutManager = layoutManager
        mainRecyclerAdapter = UpcomingEventsRecyclerAdapter(context, allCategory)
        mainCategoryRecycler!!.adapter = mainRecyclerAdapter
    }



}