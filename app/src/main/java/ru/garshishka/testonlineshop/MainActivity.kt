package ru.garshishka.testonlineshop

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.View
import android.widget.Button
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
            menuPanel = menu
            buttonMain.setOnClickListener { changeFragment(MenuDestination.MAIN) }
            buttonCatalogue.setOnClickListener { changeFragment(MenuDestination.CATALOGUE) }
            buttonCart.setOnClickListener { changeFragment(MenuDestination.CART) }
            buttonDiscounts.setOnClickListener { changeFragment(MenuDestination.DISCOUNTS) }
            buttonProfile.setOnClickListener { changeFragment(MenuDestination.PROFILE) }

        }
    }


    private fun updateUIVisibility(destination: Int) {
            menuPanel.isVisible = destination != R.id.registrationFragment
    }

    private fun changeFragment(destination: MenuDestination) {
        binding.apply {
            val buttons =
                listOf(buttonCart, buttonCatalogue, buttonMain, buttonDiscounts, buttonProfile)
            buttons.forEach { changeButtonColors(it) }
            val pressedButton = when (destination) {
                MenuDestination.MAIN -> buttonMain
                MenuDestination.CATALOGUE -> buttonCatalogue
                MenuDestination.PROFILE -> buttonProfile
                MenuDestination.CART -> buttonCart
                MenuDestination.DISCOUNTS -> buttonDiscounts
            }
            changeButtonColors(pressedButton,true)
        }
        val destinationId = when (destination) {
            MenuDestination.MAIN -> R.id.action_global_mainFragment
            MenuDestination.CATALOGUE -> R.id.action_global_catalogueFragment
            MenuDestination.PROFILE -> R.id.action_global_profileFragment
            MenuDestination.CART -> R.id.action_global_cartFragment
            MenuDestination.DISCOUNTS -> R.id.action_global_discountsFragment
        }
        findNavController(R.id.fragment_container).navigate(destinationId)
    }

    private fun changeButtonColors(button: Button, pinkColor: Boolean = false) {
        val color =
            if (pinkColor) resources.getColor(R.color.pink) else resources.getColor(R.color.dark_grey)
        button.setTextColor(color)
        val icon = button.compoundDrawables[1]
        icon.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
    }

    fun setToolbarTextViewText(text: String) {
        val textView = binding.toolbarTitle
        textView.text = text
    }
}

enum class MenuDestination {
    MAIN, CATALOGUE, PROFILE, CART, DISCOUNTS
}