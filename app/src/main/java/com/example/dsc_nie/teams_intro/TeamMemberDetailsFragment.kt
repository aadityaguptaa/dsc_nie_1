package com.example.dsc_nie.teams_intro

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.example.dsc_nie.R
import com.example.dsc_nie.databinding.FragmentTeamMemberDetailsBinding


class TeamMemberDetailsFragment : Fragment() {
    lateinit var binding: FragmentTeamMemberDetailsBinding
    val args:TeamMemberDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_team_member_details, container, false
        )
        // Inflate the layout for this fragment
        binding.teamImage.setImageResource(args.imageId)
        return binding.root
    }


}