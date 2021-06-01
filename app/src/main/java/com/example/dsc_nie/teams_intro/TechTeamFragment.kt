package com.example.dsc_nie.teams_intro

import android.os.Bundle
import android.view.*
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dsc_nie.R
import com.example.dsc_nie.RecyclerAdapter
import com.example.dsc_nie.databinding.FragmentTechTeamBinding
import com.example.dsc_nie.home.NavigationIconClickListener
import com.google.firebase.auth.FirebaseAuth


class TechTeamFragment : Fragment() {


    private var titlesList = mutableListOf<String>()
    private var positionList = mutableListOf<String>()
    private var imagesList = mutableListOf<Int>()
    private var descList = mutableListOf<String>()
    private var instagramList = mutableListOf<String>()
    private var facebookList = mutableListOf<String>()
    private var emailList = mutableListOf<String>()

    lateinit var binding: FragmentTechTeamBinding
    private lateinit var recyclerView: RecyclerView


    //the main function
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_tech_team, container, false)

        setDrawableBackground()


        postToList()
        setRecycler()
        setActionBar()
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.dsc_toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, menuInflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var navController = Navigation.findNavController(requireView())
        FirebaseAuth.getInstance().signOut()
        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item)
    }


    private fun setRecycler() {
        recyclerView = binding.techTeamRecyclerView
        binding.techTeamRecyclerView.setHasFixedSize(true)
        val gridLayoutManager = GridLayoutManager(context, 2, RecyclerView.HORIZONTAL, false)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position % 3 == 2) 2 else 1
            }
        }
        binding.techTeamRecyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = RecyclerAdapter(titlesList, positionList, imagesList, descList, instagramList, facebookList, emailList)

        val largePadding = resources.getDimensionPixelSize(R.dimen.shr_product_grid_spacing)
        val smallPadding = resources.getDimensionPixelSize(R.dimen.shr_product_grid_spacing_small)
        binding.techTeamRecyclerView.addItemDecoration(ProductGridItemDecoration(largePadding, smallPadding))

    }


    private fun addToList(title: String, position: String, image: Int, description: String, instagram: String, facebook: String, email: String) {
        titlesList.add(title)
        positionList.add(position)
        imagesList.add(image)
        descList.add(description)
        instagramList.add(instagram)
        facebookList.add(facebook)
        emailList.add(email)
    }

    private fun postToList() {
        addToList(getString(R.string.pratyusha), getString(R.string.dsc_lead), R.drawable.pratyushaone, getString(R.string.adityaDescription), getString(R.string.aditya_instagram), getString(R.string.aditya_linkedin), getString(R.string.aditya_email))
        addToList(getString(R.string.aditya), getString(R.string.android_lead), R.drawable.adityaone, getString(R.string.adityaDescription), getString(R.string.aditya_instagram), getString(R.string.aditya_linkedin), getString(R.string.aditya_email))
        addToList(getString(R.string.iresh), getString(R.string.web_dev_lead_lead), R.drawable.iresh, getString(R.string.adityaDescription), getString(R.string.aditya_instagram), getString(R.string.aditya_linkedin), getString(R.string.aditya_email))
        addToList(getString(R.string.rishabh), getString(R.string.web_dev_lead_lead), R.drawable.rishabh, getString(R.string.adityaDescription), getString(R.string.aditya_instagram), getString(R.string.aditya_linkedin), getString(R.string.aditya_email))
        addToList(getString(R.string.sanskar), getString(R.string.technical_lead), R.drawable.sanskar, getString(R.string.adityaDescription), getString(R.string.aditya_instagram), getString(R.string.aditya_linkedin), getString(R.string.aditya_email))
        addToList(getString(R.string.shujan), getString(R.string.microcontrollers_lead), R.drawable.shujan, getString(R.string.adityaDescription), getString(R.string.aditya_instagram), getString(R.string.aditya_linkedin), getString(R.string.aditya_email))
        addToList(getString(R.string.harsh), getString(R.string.ml_lead), R.drawable.harsh, getString(R.string.adityaDescription), getString(R.string.aditya_instagram), getString(R.string.aditya_linkedin), getString(R.string.aditya_email))

    }

    fun setActionBar(){
        (activity as AppCompatActivity).setSupportActionBar(binding.techTeamAppBar)
        binding.techTeamAppBar.setNavigationOnClickListener(
            NavigationIconClickListener(
                requireActivity(),
                binding.techTeamRecycler,
                AccelerateDecelerateInterpolator(),
                ContextCompat.getDrawable(requireContext(), R.drawable.dsc_branded_menu), // Menu open icon
                ContextCompat.getDrawable(requireContext(), R.drawable.dsc_close_menu))
        )
    }

    private fun setDrawableBackground(){
        binding.techTeamRecycler.background = ContextCompat.getDrawable(requireContext(), R.drawable.dsc_product_grid_background_shape)
    }

}