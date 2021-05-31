package com.example.dsc_nie.login_signup

import android.content.ContentValues.TAG
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
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
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_sign_up, container, false
        )

        binding.SignupPageLoginTextView.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(R.id.action_signUpFragment_to_loginFragment)
        }


        binding.SignUpPageRegisterButton.setOnClickListener { view: View ->
            when{
                TextUtils.isEmpty(binding.SignUpPageEmailEditText.text.toString().trim{ it <= ' '}) -> {
                    Toast.makeText(
                            context,
                            "Please Enter Email.",
                            Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(binding.SignUpPagePasswordEditText.text.toString().trim{ it <= ' '}) -> {
                    Toast.makeText(
                            context,
                            "Please Enter Password.",
                            Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {

                    val email: String = binding.SignUpPageEmailEditText.text.toString().trim{ it <= ' '}
                    val password: String = binding.SignUpPagePasswordEditText.text.toString().trim{ it <= ' '}

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                            OnCompleteListener<AuthResult> { task ->
                                if(task.isSuccessful){

                                    val firebaseUser: FirebaseUser = task.result!!.user

                                    // [START update_profile]
                                    val user = Firebase.auth.currentUser

                                    val profileUpdates = userProfileChangeRequest {
                                        displayName = "Jane Q. User"
                                        photoUri = Uri.parse("https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/1200px-Image_created_with_a_mobile_phone.png")
                                    }


                                    user!!.updateProfile(profileUpdates)
                                        .addOnCompleteListener { task ->
                                            if (task.isSuccessful) {
                                                Log.d(TAG, "User profile updated.")
                                            }
                                        }
                                    // [END update_profile]


                                    Toast.makeText(
                                            context,
                                            "You are registered successfully.",
                                            Toast.LENGTH_SHORT
                                    ).show()

                                    Navigation.findNavController(view).navigate(R.id.action_signUpFragment_to_homeFragment)
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