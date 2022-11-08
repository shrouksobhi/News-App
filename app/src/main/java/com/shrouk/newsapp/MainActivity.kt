package com.shrouk.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var bottomNavigationView: BottomNavigationView =
            findViewById(R.id.bottomNavigationView)
//        navController=Navigation.findNavController(this,R.id.activity_template_nav_host_fragment)
//        setupWithNavController(R.id.bottomNavigationView,navController)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.activity_main_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            .setupWithNavController(navController)



        navController.addOnDestinationChangedListener { nc: NavController, nd: NavDestination, _: Bundle? ->
            if ((nd.id == R.id.fragment_home) ||
                (nd.id == R.id.fragment_saved) ||
                (nd.id == R.id.fragment_search)

            ) {
                bottomNavigationView.visibility = View.VISIBLE
            } else
                bottomNavigationView.visibility = View.GONE
        }
    }
}