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
import com.example.dsc_nie.databinding.FragmentTechTeamBinding


class TechTeamFragment : Fragment() {
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
        val binding: FragmentTechTeamBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_tech_team, container, false)

        postToList()


        recyclerView =  binding.techTeamRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = RecyclerAdapter(titlesList, positionList, imagesList, descList, instagramList, facebookList, emailList)

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        addToList(getString(R.string.pratyusha), getString(R.string.dsc_lead), R.drawable.pratyushaone, getString(R.string.adityaDescription), getString(R.string.aditya_instagram), getString(R.string.aditya_facebook), getString(R.string.aditya_email))
        addToList(getString(R.string.aditya),getString(R.string.android_lead),  R.drawable.adityaone, getString(R.string.adityaDescription), getString(R.string.aditya_instagram), getString(R.string.aditya_facebook), getString(R.string.aditya_email))
        addToList(getString(R.string.harsh),getString(R.string.ml_lead),  R.drawable.harsh, getString(R.string.adityaDescription), getString(R.string.aditya_instagram), getString(R.string.aditya_facebook), getString(R.string.aditya_email))
        addToList(getString(R.string.iresh),getString(R.string.web_dev_lead_lead),  R.drawable.iresh, getString(R.string.adityaDescription), getString(R.string.aditya_instagram), getString(R.string.aditya_facebook), getString(R.string.aditya_email))
        addToList(getString(R.string.pranav),getString(R.string.technical_advisor),  R.drawable.pranav, getString(R.string.adityaDescription), getString(R.string.aditya_instagram), getString(R.string.aditya_facebook), getString(R.string.aditya_email))
        addToList(getString(R.string.rishabh),getString(R.string.web_dev_lead_lead),  R.drawable.rishabh, getString(R.string.adityaDescription), getString(R.string.aditya_instagram), getString(R.string.aditya_facebook), getString(R.string.aditya_email))
        addToList(getString(R.string.sanskar),getString(R.string.technical_lead),  R.drawable.sanskar, getString(R.string.adityaDescription), getString(R.string.aditya_instagram), getString(R.string.aditya_facebook), getString(R.string.aditya_email))
        addToList(getString(R.string.shujan),getString(R.string.microcontrollers_lead),  R.drawable.shujan, getString(R.string.adityaDescription), getString(R.string.aditya_instagram), getString(R.string.aditya_facebook), getString(R.string.aditya_email))
        addToList(getString(R.string.tanmoy),getString(R.string.android_lead),  R.drawable.tanmoy, getString(R.string.adityaDescription), getString(R.string.aditya_instagram), getString(R.string.aditya_facebook), getString(R.string.aditya_email))
    }
}