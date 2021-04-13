package com.example.dsc_nie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment
        val graphInflater = navHostFragment.navController.navInflater
        val navGraph = graphInflater.inflate(R.navigation.navigation)
        val navController = navHostFragment.navController

        if(FirebaseAuth.getInstance().currentUser != null) {
            navGraph.startDestination = R.id.homeFragment
        }else{
            navGraph.startDestination = R.id.loginSignupMenuFragment
        }
        navController.graph = navGraph

    }
}