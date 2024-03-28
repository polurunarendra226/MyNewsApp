package com.mynews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.mynews.databinding.ActivityMainBinding
import com.mynews.viewmodels.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val binding:ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    lateinit var navhostfragmet:NavHostFragment
    lateinit var navController: NavController



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.bottomNavigationView.setupWithNavController(findNavController(R.id.fragmentContainerView))
        setSupportActionBar(findViewById(R.id.toolbar))



        navhostfragmet= supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        navController = navhostfragmet.navController

        setupActionBarWithNavController(navController)



    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() ||super.onSupportNavigateUp()
    }
}