package com.example.dsc_nie.login_signup

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.dsc_nie.R
import com.example.dsc_nie.databinding.FragmentLoginBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding
    lateinit var viewModel: LoginViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_login, container, false
        )

        //Inflate yhe View Model
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        binding.LoginPageRegisterTextView.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_signUpFragment)
        }


        binding.LoginPageLoginButton.setOnClickListener {
            if(!checkEmailPasswordEmpty()) {
                if (!isPasswordValid(binding.LoginPagePasswordEditText.text!!)) {
                    binding.LoginPagePasswordTextView.error = getString(R.string.dsc_error_password)
                } else {
                    // Clear the error.
                    viewModel.email =
                        binding.LoginPageEmailEditText.text.toString().trim { it <= ' ' }
                    viewModel.password =
                        binding.LoginPagePasswordEditText.text.toString().trim { it <= ' ' }
                    viewModel.iniAuth()
                    binding.LoginPagePasswordTextView.error = null
                }
            }
        }


        viewModel.authSuccess.observe(viewLifecycleOwner, Observer { success ->
            if(success) {
                Navigation.findNavController(binding.LoginPageLoginButton)
                    .navigate(R.id.action_loginFragment_to_homeFragment)
            }else{
                Toast.makeText(
                    context,
                    "Auth unsuccessful, Please check your Email and Password!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        return binding.root
    }

    private fun checkEmailPasswordEmpty(): Boolean{
        when {
            TextUtils.isEmpty(binding.LoginPageEmailEditText.text.toString().trim{ it <= ' '}) -> {
                Toast.makeText(
                    context,
                    "Please Enter Email.",
                    Toast.LENGTH_SHORT
                ).show()
                return true
            }
            TextUtils.isEmpty(binding.LoginPagePasswordEditText.text.toString().trim{ it <= ' '}) -> {
                Toast.makeText(
                    context,
                    "Please Enter Password.",
                    Toast.LENGTH_SHORT
                ).show()
                return true
            }
            else -> {
                return false
            }
        }
    }

    private fun isPasswordValid(text: Editable?): Boolean {
        return text != null && text.length >= 8
    }


}