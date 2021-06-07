package com.example.dsc_nie.teams_intro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView

class TechTeamViewModel: ViewModel() {

    val ini = MutableLiveData<Boolean>()

    init {
        ini.value = true
    }


}