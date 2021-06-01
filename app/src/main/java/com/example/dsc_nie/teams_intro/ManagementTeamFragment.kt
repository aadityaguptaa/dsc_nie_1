package com.example.dsc_nie.teams_intro

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dsc_nie.R
import com.example.dsc_nie.RecyclerAdapter
import com.example.dsc_nie.databinding.FragmentManagementTeamBinding
import com.example.dsc_nie.home.NavigationIconClickListener
import com.google.firebase.auth.FirebaseAuth


class ManagementTeamFragment : Fragment() {

    private var titlesList = mutableListOf<String>()
    private var positionList = mutableListOf<String>()
    private var imagesList = mutableListOf<Int>()
    private var descList = mutableListOf<String>()
    private var instagramList = mutableListOf<String>()
    private var facebookList = mutableListOf<String>()
    private var emailList = mutableListOf<String>()

    private lateinit var recyclerView: RecyclerView
    lateinit var binding: FragmentManagementTeamBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_management_team, container, false)

        setDrawableBackground()


        postToList()
        setRecycler()
        setActionBar()
        setHasOptionsMenu(true)

        return binding.root
    }

    private fun addToList(title: String, position: String, image: Int, description: String, instagram: String, facebook: String, email: String){
        titlesList.add(title)
        positionList.add(position)
        imagesList.add(image)
        descList.add(description)
        instagramList.add(instagram)
        facebookList.add(facebook)
        emailList.add(email)
    }

    private fun postToList(){
        addToList(getString(R.string.aditi), getString(R.string.creativity_lead),  R.drawable.aditi, getString(R.string.adityaDescription), getString(R.string.aditya_instagram), getString(R.string.aditya_linkedin), getString(R.string.aditya_email))
        addToList(getString(R.string.deepthi), getString(R.string.editorial_lead),  R.drawable.deepthi, getString(R.string.adityaDescription), getString(R.string.aditya_instagram), getString(R.string.aditya_linkedin), getString(R.string.aditya_email))
        addToList(getString(R.string.dharitri), getString(R.string.events_lead),  R.drawable.dharitri, getString(R.string.adityaDescription), getString(R.string.aditya_instagram), getString(R.string.aditya_linkedin), getString(R.string.aditya_email))
        addToList(getString(R.string.divya), getString(R.string.outreach_lead),  R.drawable.divya, getString(R.string.adityaDescription), getString(R.string.aditya_instagram), getString(R.string.aditya_linkedin), getString(R.string.aditya_email))
        addToList(getString(R.string.lohith), getString(R.string.events_lead),  R.drawable.lohith, getString(R.string.adityaDescription), getString(R.string.aditya_instagram), getString(R.string.aditya_linkedin), getString(R.string.aditya_email))
        addToList(getString(R.string.prabhajana), getString(R.string.management_lead),  R.drawable.prabhajana, getString(R.string.adityaDescription), getString(R.string.aditya_instagram), getString(R.string.aditya_linkedin), getString(R.string.aditya_email))
        addToList(getString(R.string.pulkit), getString(R.string.editorial_lead),  R.drawable.pulkit, getString(R.string.adityaDescription), getString(R.string.aditya_instagram), getString(R.string.aditya_linkedin), getString(R.string.aditya_email))
        addToList(getString(R.string.raghav), getString(R.string.outreach_lead),  R.drawable.raghav, getString(R.string.adityaDescription), getString(R.string.aditya_instagram), getString(R.string.aditya_linkedin), getString(R.string.aditya_email))
        addToList(getString(R.string.sathwik), getString(R.string.design_lead),  R.drawable.sathwik, getString(R.string.adityaDescription), getString(R.string.aditya_instagram), getString(R.string.aditya_linkedin), getString(R.string.aditya_email))
        addToList(getString(R.string.shriyank), getString(R.string.design_lead),  R.drawable.shriyank, getString(R.string.adityaDescription), getString(R.string.aditya_instagram), getString(R.string.aditya_linkedin), getString(R.string.aditya_email))
        addToList(getString(R.string.tulsi), getString(R.string.marketing_lead),  R.drawable.tulsi, getString(R.string.adityaDescription), getString(R.string.aditya_instagram), getString(R.string.aditya_linkedin), getString(R.string.aditya_email))




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
        recyclerView = binding.managementTeamRecyclerView
        binding.managementTeamRecyclerView.setHasFixedSize(true)
        val gridLayoutManager = GridLayoutManager(context, 2, RecyclerView.HORIZONTAL, false)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position % 3 == 2) 2 else 1
            }
        }
        binding.managementTeamRecyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = RecyclerAdapter(titlesList, positionList, imagesList, descList, instagramList, facebookList, emailList)

        val largePadding = resources.getDimensionPixelSize(R.dimen.shr_product_grid_spacing)
        val smallPadding = resources.getDimensionPixelSize(R.dimen.shr_product_grid_spacing_small)
        binding.managementTeamRecyclerView.addItemDecoration(ProductGridItemDecoration(largePadding, smallPadding))

    }

    fun setActionBar(){
        (activity as AppCompatActivity).setSupportActionBar(binding.managementTeamAppBar)
        binding.managementTeamAppBar.setNavigationOnClickListener(
            NavigationIconClickListener(
                requireActivity(),
                binding.managementTeamRecycler,
                AccelerateDecelerateInterpolator(),
                ContextCompat.getDrawable(requireContext(), R.drawable.dsc_branded_menu), // Menu open icon
                ContextCompat.getDrawable(requireContext(), R.drawable.dsc_close_menu))
        )
    }

    private fun setDrawableBackground(){
        binding.managementTeamRecycler.background = ContextCompat.getDrawable(requireContext(), R.drawable.dsc_product_grid_background_shape)
    }
}