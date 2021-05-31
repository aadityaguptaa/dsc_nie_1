package com.example.dsc_nie.home

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dsc_nie.R
import com.example.dsc_nie.adapter.UpcomingEventsRecyclerAdapter
import com.example.dsc_nie.databinding.FragmentHomeBinding
import com.example.dsc_nie.model.AllCategory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.squareup.picasso.Picasso


class HomeFragment : Fragment() {

    private var mainCategoryRecycler: RecyclerView? = null
    private var mainRecyclerAdapter: UpcomingEventsRecyclerAdapter? = null
    lateinit var binding: FragmentHomeBinding
    lateinit var navController: NavController
    lateinit var viewModel: HomeFragmentViewModel
    lateinit var firebaseUser: FirebaseUser
    lateinit var firebaseAuth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )

        viewModel = ViewModelProviders.of(this).get(HomeFragmentViewModel::class.java)

        firebaseAuth = FirebaseAuth.getInstance()
        @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        firebaseUser = firebaseAuth.currentUser

        //to set user profile image and name
        setProfileImageAndName()

        //to set Action Bar
        setActionBar()

        addObserverToViewModel()

        setDrawableBackground()

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.dsc_toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, menuInflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        navController = Navigation.findNavController(requireView())
        firebaseAuth.signOut()
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

    @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    private fun setProfileImageAndName(){
        if(firebaseUser.photoUrl != null){
            Picasso.get().load(firebaseUser.photoUrl.toString()).into(binding.UserProfileImage)
        }else{
            binding.UserProfileImage.setImageResource(R.drawable.profile_male)
        }
        if(firebaseUser.displayName != null){
            binding.UsernameTextView.text = firebaseUser.displayName
        }
    }

    private fun setActionBar(){
        (activity as AppCompatActivity).setSupportActionBar(binding.appBar)
        binding.appBar.setNavigationOnClickListener(
            NavigationIconClickListener(
                requireActivity(),
                binding.homeFragmentNestedScrollView,
                AccelerateDecelerateInterpolator(),
                ContextCompat.getDrawable(requireContext(), R.drawable.dsc_branded_menu), // Menu open icon
                ContextCompat.getDrawable(requireContext(), R.drawable.dsc_close_menu))
        )
    }

    private fun addObserverToViewModel(){
        viewModel.done.observe(viewLifecycleOwner, Observer { done->
            setMainCategoryRecycler(viewModel.allCategory)
            binding.homeFragmentProgressBar.visibility = View.GONE
            binding.homeFragmentBackdrop.visibility = View.VISIBLE
            binding.homeFragmentNestedScrollView.visibility = View.VISIBLE
        })
    }

    private fun setDrawableBackground(){
        binding.homeFragmentNestedScrollView.background = ContextCompat.getDrawable(requireContext(), R.drawable.dsc_product_grid_background_shape)
    }

}