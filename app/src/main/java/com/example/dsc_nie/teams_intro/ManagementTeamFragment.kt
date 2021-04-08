package com.example.dsc_nie.teams_intro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dsc_nie.R
import com.example.dsc_nie.RecyclerAdapter
import com.example.dsc_nie.databinding.FragmentManagementTeamBinding


class ManagementTeamFragment : Fragment() {

    private var titlesList = mutableListOf<String>()
    private var positionList = mutableListOf<String>()
    private var imagesList = mutableListOf<Int>()
    private var descList = mutableListOf<String>()
    private var instagramList = mutableListOf<String>()
    private var facebookList = mutableListOf<String>()
    private var emailList = mutableListOf<String>()

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentManagementTeamBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_management_team, container, false)

        postToList()

        recyclerView =  binding.managementTeamRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = RecyclerAdapter(titlesList, positionList, imagesList, descList, instagramList, facebookList, emailList)
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
        addToList(getString(R.string.aditi), getString(R.string.creativity_lead),  R.drawable.aditi, getString(R.string.adityaDescription), getString(R.string.aditya_instagram), getString(R.string.aditya_facebook), getString(R.string.aditya_email))
        addToList(getString(R.string.deepthi), getString(R.string.editorial_lead),  R.drawable.deepthi, getString(R.string.adityaDescription), getString(R.string.aditya_instagram), getString(R.string.aditya_facebook), getString(R.string.aditya_email))
        addToList(getString(R.string.dharitri), getString(R.string.events_lead),  R.drawable.dharitri, getString(R.string.adityaDescription), getString(R.string.aditya_instagram), getString(R.string.aditya_facebook), getString(R.string.aditya_email))
        addToList(getString(R.string.divya), getString(R.string.outreach_lead),  R.drawable.divya, getString(R.string.adityaDescription), getString(R.string.aditya_instagram), getString(R.string.aditya_facebook), getString(R.string.aditya_email))
        addToList(getString(R.string.lohith), getString(R.string.events_lead),  R.drawable.lohith, getString(R.string.adityaDescription), getString(R.string.aditya_instagram), getString(R.string.aditya_facebook), getString(R.string.aditya_email))
        addToList(getString(R.string.prabhajana), getString(R.string.management_lead),  R.drawable.prabhajana, getString(R.string.adityaDescription), getString(R.string.aditya_instagram), getString(R.string.aditya_facebook), getString(R.string.aditya_email))
        addToList(getString(R.string.pulkit), getString(R.string.editorial_lead),  R.drawable.pulkit, getString(R.string.adityaDescription), getString(R.string.aditya_instagram), getString(R.string.aditya_facebook), getString(R.string.aditya_email))
        addToList(getString(R.string.raghav), getString(R.string.outreach_lead),  R.drawable.raghav, getString(R.string.adityaDescription), getString(R.string.aditya_instagram), getString(R.string.aditya_facebook), getString(R.string.aditya_email))
        addToList(getString(R.string.sathwik), getString(R.string.design_lead),  R.drawable.sathwik, getString(R.string.adityaDescription), getString(R.string.aditya_instagram), getString(R.string.aditya_facebook), getString(R.string.aditya_email))
        addToList(getString(R.string.shriyank), getString(R.string.design_lead),  R.drawable.shriyank, getString(R.string.adityaDescription), getString(R.string.aditya_instagram), getString(R.string.aditya_facebook), getString(R.string.aditya_email))
        addToList(getString(R.string.tulsi), getString(R.string.marketing_lead),  R.drawable.tulsi, getString(R.string.adityaDescription), getString(R.string.aditya_instagram), getString(R.string.aditya_facebook), getString(R.string.aditya_email))




    }
}