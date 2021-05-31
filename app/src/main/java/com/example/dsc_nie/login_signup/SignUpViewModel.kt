package com.example.dsc_nie.login_signup

import android.content.ContentValues
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.dsc_nie.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase

class SignUpViewModel: ViewModel() {

    var email: String = ""
    var password: String = ""
    val authSuccess = MutableLiveData<Boolean>()
    lateinit var firebaseUser: FirebaseUser
    lateinit var firebaseAuthLocal: FirebaseAuth

    init {
        firebaseAuthLocal = FirebaseAuth.getInstance()
    }

    fun iniAuth(){
        firebaseAuthLocal.createUserWithEmailAndPassword(email, password).addOnCompleteListener(
            OnCompleteListener<AuthResult> { task ->
                if(task.isSuccessful){
                    firebaseUser = task.result!!.user

                    // [START update_profile]
                    val user = Firebase.auth.currentUser
                    val profileUpdates = userProfileChangeRequest {
                        displayName = "Jane Q. User"
                        photoUri = Uri.parse("https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/1200px-Image_created_with_a_mobile_phone.png")
                    }
                    user!!.updateProfile(profileUpdates)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Log.d(ContentValues.TAG, "User profile updated.")
                            }
                        }
                    // [END update_profile]
                    authSuccess.value = true
                    }else{
                    authSuccess.value = false
                }
            }
        )
    }
}

