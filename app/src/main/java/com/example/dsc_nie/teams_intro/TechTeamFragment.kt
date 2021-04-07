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
    private var descList = mutableListOf<String>()
    private var imagesList = mutableListOf<Int>()
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentTechTeamBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_tech_team, container, false)

        postToList()


        recyclerView =  binding.techTeamRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = RecyclerAdapter(titlesList, descList, imagesList)

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private fun addToList(title: String, description: String, image: Int){
        titlesList.add(title)
        descList.add(description)
        imagesList.add(image)
    }

    private fun postToList(){
        addToList(getString(R.string.dsc_lead), getString(R.string.pratyusha), R.drawable.pratyusha)
        addToList(getString(R.string.android_lead), getString(R.string.aditya), R.drawable.adityaone)
        addToList(getString(R.string.ml_lead), getString(R.string.harsh), R.drawable.harsh)
        addToList(getString(R.string.web_dev_lead_lead), getString(R.string.iresh), R.drawable.iresh)
        addToList(getString(R.string.technical_advisor), getString(R.string.pranav), R.drawable.pranav)
        addToList(getString(R.string.web_dev_lead_lead), getString(R.string.rishabh), R.drawable.rishabh)
        addToList(getString(R.string.technical_lead), getString(R.string.sanskar), R.drawable.sanskar)
        addToList(getString(R.string.microcontrollers_lead), getString(R.string.shujan), R.drawable.shujan)
        addToList(getString(R.string.android_lead), getString(R.string.tanmoy), R.drawable.tanmoy)
    }
}