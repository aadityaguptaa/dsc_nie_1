package com.example.dsc_nie.login_signup

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
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
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_login, container, false
        )

        binding.LoginPageRegisterTextView.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_signUpFragment)
        }


        binding.LoginPageLoginButton.setOnClickListener { view: View ->
            when{
                TextUtils.isEmpty(binding.LoginPageEmailEditText.text.toString().trim{ it <= ' '}) -> {
                    Toast.makeText(
                            context,
                            "Please Enter Email.",
                            Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(binding.LoginPagePasswordEditText.text.toString().trim{ it <= ' '}) -> {
                    Toast.makeText(
                            context,
                            "Please Enter Password.",
                            Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {

                    val email: String = binding.LoginPageEmailEditText.text.toString().trim{ it <= ' '}
                    val password: String = binding.LoginPagePasswordEditText.text.toString().trim{ it <= ' '}

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(
                            OnCompleteListener<AuthResult> { task ->
                                if(task.isSuccessful){

                                    val firebaseUser: FirebaseUser = task.result!!.user

                                    Toast.makeText(
                                            context,
                                            "You LoggedIn successfully.",
                                            Toast.LENGTH_SHORT
                                    ).show()

                                    Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homeFragment)


                                }else{

                                    Toast.makeText(
                                            context,
                                            task.exception!!.message.toString(),
                                            Toast.LENGTH_SHORT
                                    ).show()
                                }

                            }
                    )
                }
            }
        }
        return binding.root
    }


}