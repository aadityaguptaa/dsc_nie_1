package com.example.dsc_nie.login_signup

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.dsc_nie.R
import com.example.dsc_nie.databinding.FragmentLoginMaterialFragmentBinding


class login_material_fragment : Fragment() {

    lateinit var binding: FragmentLoginMaterialFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            layoutInflater, R.layout.fragment_login_material_fragment, container, false
        )
        // Inflate the layout for this fragment

        // Set an error if the password is less than 8 characters.
        binding.nextButton.setOnClickListener {
            if (!isPasswordValid(binding.passwordEditText.text!!)) {
                binding.passwordTextInput.error = getString(R.string.dsc_error_password)
            } else {
                // Clear the error.
                binding.passwordTextInput.error = null
            }
        }

        // Clear the error once more than 8 characters are typed.
        binding.passwordEditText.setOnKeyListener { _, _, _ ->
            if (isPasswordValid(binding.passwordEditText.text!!)) {
                // Clear the error.
                binding.passwordTextInput.error = null
            }
            false
        }
        return binding.root
    }

    private fun isPasswordValid(text: Editable?): Boolean {
        return text != null && text.length >= 8
    }


}