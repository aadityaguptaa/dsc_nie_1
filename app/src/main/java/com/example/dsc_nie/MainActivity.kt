package com.example.dsc_nie

import android.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.dsc_nie.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment =supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment
        val graphInflater = navHostFragment.navController.navInflater
        val navGraph = graphInflater.inflate(R.navigation.navigation)
        navController = navHostFragment.navController
        if(FirebaseAuth.getInstance().currentUser != null) {
            navGraph.startDestination = R.id.homeFragment
        }else{
            navGraph.startDestination = R.id.loginSignupMenuFragment
        }
        navController.graph = navGraph
        //NavigationUI.setupActionBarWithNavController(this, navController)
        //supportActionBar!!.isHideOnContentScrollEnabled = true
        //supportActionBar!!.setHideOnContentScrollEnabled(true)
    }




}