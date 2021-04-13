package com.example.dsc_nie.login_signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.dsc_nie.R
import com.example.dsc_nie.databinding.FragmentLoginSignupMenuBinding
import com.google.firebase.auth.FirebaseAuth


class LoginSignupMenuFragment : Fragment() {

    lateinit var  binding: FragmentLoginSignupMenuBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login_signup_menu, container, false
        )

        /*if(FirebaseAuth.getInstance().currentUser!= null){
            Navigation.findNavController(binding.root.findViewById(R.id.LoginSignupPageLoginButton)).navigate(R.id.action_loginSignupMenuFragment_to_homeFragment)
        }*/


        binding.LoginSignupPageLoginButton.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(R.id.action_loginSignupMenuFragment_to_loginFragment)
        }

        binding.LoginSignupPageSignUpButton.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(R.id.action_loginSignupMenuFragment_to_signUpFragment)
        }

        return binding.root
    }


}