package com.example.dsc_nie.login_signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.dsc_nie.R
import com.example.dsc_nie.databinding.FragmentLoginSignupMenuBinding


class LoginSignupMenuFragment : Fragment() {

    lateinit var  binding: FragmentLoginSignupMenuBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login_signup_menu, container, false
        )

        binding.LSLoginButton.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(R.id.action_loginSignupMenuFragment_to_loginFragment)
        }
        return binding.root
    }


}