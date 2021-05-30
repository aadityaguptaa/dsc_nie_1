package com.example.dsc_nie.login_signup

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

class LoginViewModel: ViewModel() {

    var email: String = ""
    var password: String = ""
    //lateinit var userImageUrl: Uri
    val authSuccess = MutableLiveData<Boolean>()
    lateinit var firebaseUser: FirebaseUser
    lateinit var firebaseAuthLocal: FirebaseAuth

    init {
        firebaseAuthLocal = FirebaseAuth.getInstance()
    }

    fun iniAuth(){

        firebaseAuthLocal.signInWithEmailAndPassword(email, password).addOnCompleteListener(
            OnCompleteListener<AuthResult> { task ->
                if(task.isSuccessful){
                    firebaseUser = task.result!!.user
                    UserGlobal.name = firebaseUser.displayName
                    UserGlobal.imageUrl = firebaseUser.photoUrl.toString()
                    authSuccess.value = true
                }else{
                    authSuccess.value = false
                }
            }
        )
    }

}

object UserGlobal{
    lateinit var name:String
    lateinit var imageUrl:String
}
