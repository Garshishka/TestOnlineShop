package ru.garshishka.testonlineshop

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import ru.garshishka.testonlineshop.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private lateinit var titleView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpUi()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            updateUIVisibility(destination.id)
        }
    }

    private fun setUpUi() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.apply {
            titleView = toolbarTitle
            bottomNavigation.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_main -> {
                        findNavController(R.id.fragment_container).navigate(R.id.action_global_mainFragment)
                        true
                    }

                    R.id.menu_catalogue -> {
                        findNavController(R.id.fragment_container).navigate(R.id.action_global_catalogueFragment)
                        true
                    }

                    R.id.menu_cart -> {
                        findNavController(R.id.fragment_container).navigate(R.id.action_global_cartFragment)
                        true
                    }

                    R.id.menu_discounts -> {
                        findNavController(R.id.fragment_container).navigate(R.id.action_global_discountsFragment)
                        true
                    }

                    R.id.menu_profile -> {
                        findNavController(R.id.fragment_container).navigate(R.id.action_global_profileFragment)
                        true
                    }

                    else -> false
                }
            }
            buttonBack.setOnClickListener {
                findNavController(R.id.fragment_container).navigateUp()
            }
        }
    }


    private fun updateUIVisibility(destination: Int) {
        binding.apply {
            bottomNavigation.isVisible = destination != R.id.registrationFragment
            buttonBack.isVisible =
                destination == R.id.favoritesCatalogue || destination == R.id.productFragment
            buttonShare.isVisible = destination == R.id.productFragment
        }
    }

    fun setToolbarTextViewText(text: String, sideText : Boolean = false) {
        if (sideText){
            binding.toolbarSidetitle.text = text
            binding.toolbarTitle.text = ""
        } else {
            binding.toolbarSidetitle.text = ""
            binding.toolbarTitle.text = text
        }
    }

    fun setActiveButtonMain() {
        binding.bottomNavigation.selectedItemId = R.id.menu_main
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}