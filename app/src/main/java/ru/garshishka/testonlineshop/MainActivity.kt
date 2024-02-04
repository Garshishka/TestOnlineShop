package ru.garshishka.testonlineshop

import android.os.Bundle
import android.view.View
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
    private lateinit var menuPanel : View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpUi()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController

        navController.addOnDestinationChangedListener{ _, destination, _ ->
                updateUIVisibility(destination.id)
            }
    }

    private fun setUpUi() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.apply {
            menuPanel = bottomMenuContainer
            bottomNavigation.setOnItemSelectedListener { item ->
                when(item.itemId) {
                    R.id.menu_main -> {
                        findNavController(R.id.fragment_container).navigate(R.id.action_global_mainFragment)
                        true
                    }
                    R.id.menu_catalogue -> {
                        findNavController(R.id.fragment_container).navigate(R.id.action_global_catalogueFragment)
                        true
                    }
                    R.id.menu_cart ->{
                        findNavController(R.id.fragment_container).navigate(R.id.action_global_cartFragment)
                        true
                    }
                    R.id.menu_discounts ->{
                        findNavController(R.id.fragment_container).navigate(R.id.action_global_discountsFragment)
                        true
                    }
                    R.id.menu_profile ->{
                        findNavController(R.id.fragment_container).navigate(R.id.action_global_profileFragment)
                        true
                    }
                    else -> false
                }
            }
        }
    }


    private fun updateUIVisibility(destination: Int) {
            menuPanel.isVisible = destination != R.id.registrationFragment
    }

    fun setToolbarTextViewText(text: String) {
        val textView = binding.toolbarTitle
        textView.text = text
    }

    fun setActiveButtonMain(){
      binding.bottomNavigation.selectedItemId = R.id.menu_main
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}