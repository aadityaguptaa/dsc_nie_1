package com.example.dsc_nie.login_signup

import android.content.ContentValues.TAG
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.dsc_nie.R
import com.example.dsc_nie.databinding.FragmentSignUpBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase


class SignUpFragment : Fragment() {

    lateinit var binding: FragmentSignUpBinding
    lateinit var viewModel: SignUpViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_sign_up, container, false
        )

        viewModel = ViewModelProviders.of(this).get(SignUpViewModel::class.java)

        binding.SignupPageLoginTextView.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(R.id.action_signUpFragment_to_loginFragment)
        }

        setOnClickListener()

        addObserver()

        return binding.root
    }

    private fun checkEmailPasswordEmpty(): Boolean{
        var message = ""
        when {
            TextUtils.isEmpty(binding.SignUpPageNameEditText.text.toString().trim{ it <= ' '}) -> {
                message = "Please Enter Name"
            }
            TextUtils.isEmpty(binding.SignUpPageEmailEditText.text.toString().trim{ it <= ' '}) -> {
                message = "Please Enter Email."
            }
            TextUtils.isEmpty(binding.SignUpPageUSNEditText.text.toString().trim{ it <= ' '}) -> {
                message = "Please Enter USN"
            }
            TextUtils.isEmpty(binding.SignUpPagePasswordEditText.text.toString().trim{ it <= ' '}) -> {
                message = "Please Enter Password."
            }
            TextUtils.isEmpty(binding.SignUpPageConfirmPasswordEditText.text.toString().trim{ it <= ' '}) -> {
                message = "Confirm Password can't be empty"
            }
            else -> {
                return false
            }
        }
        Toast.makeText(
            context,
            message,
            Toast.LENGTH_SHORT
        ).show()
        return true
    }

    private fun isPasswordValid(text: Editable?): Boolean {
        return text != null && text.length >= 8
    }

    private fun addObserver(){
        viewModel.authSuccess.observe(viewLifecycleOwner, Observer {
            if(it == true){
                Toast.makeText(
                    context,
                    "You are registered successfully.",
                    Toast.LENGTH_SHORT
                ).show()
                Navigation.findNavController(binding.SignUpPageRegisterButton).navigate(R.id.action_signUpFragment_to_homeFragment)
            }else{
                Toast.makeText(
                    context,
                    "Error Signing Up, Try Again",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun setOnClickListener(){
        binding.SignUpPageRegisterButton.setOnClickListener { view: View ->
            if (!checkEmailPasswordEmpty()) {
                val email: String = binding.SignUpPageEmailEditText.text.toString().trim { it <= ' ' }
                val password: String = binding.SignUpPagePasswordEditText.text.toString().trim { it <= ' ' }
                val confirmPassword: String = binding.SignUpPageConfirmPasswordEditText.text.toString().trim { it <= ' ' }

                if (!isPasswordValid(binding.SignUpPagePasswordEditText.text!!)) {
                    binding.SignUpPagePasswordTextView.error = getString(R.string.dsc_error_password)
                }else if(!isPasswordValid(binding.SignUpPageConfirmPasswordEditText.text!!)){
                    binding.SignUpPageConfirmPasswordTextView.error = getString(R.string.dsc_error_password)
                }else{
                    if(password.equals(confirmPassword)){
                        viewModel.email = email
                        viewModel.password = password
                        viewModel.iniAuth()
                    }else{
                        binding.SignUpPageConfirmPasswordTextView.error = getString(R.string.dsc_error_passwords_dont_match)
                    }
                }
            }
        }
    }
}