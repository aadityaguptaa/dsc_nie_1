package com.example.dsc_nie

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.dsc_nie.R
import com.example.dsc_nie.databinding.FragmentAboutUsBinding

class AboutUsFragment : Fragment() {

    lateinit var binding: FragmentAboutUsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //inflating the layout
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_about_us, container, false
        )

        setHyperLinks()

        return binding.root
    }


    //this one makes all the textViews hyperlinks
    private fun setHyperLinks(){
        binding.DSCInstagramLinkTextView.movementMethod = LinkMovementMethod.getInstance()
        binding.DSCDiscordLinkTextView.movementMethod = LinkMovementMethod.getInstance()
        binding.DSCTwitterLinkTextView.movementMethod = LinkMovementMethod.getInstance()
        binding.DSCLinkedinLinkTextView.movementMethod = LinkMovementMethod.getInstance()
    }
}