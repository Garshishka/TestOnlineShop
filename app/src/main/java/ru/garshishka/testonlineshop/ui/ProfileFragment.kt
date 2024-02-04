package ru.garshishka.testonlineshop.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.garshishka.testonlineshop.MainActivity
import ru.garshishka.testonlineshop.R
import ru.garshishka.testonlineshop.databinding.FragmentProfileBinding
import ru.garshishka.testonlineshop.utils.deleteFromSharedPreferences
import ru.garshishka.testonlineshop.utils.getStringFromPrefs
import ru.garshishka.testonlineshop.utils.getWordForAmount
import ru.garshishka.testonlineshop.viewmodel.CatalogueViewModel

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel : CatalogueViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.setToolbarTextViewText(requireContext().getString(R.string.fragment_profile))
        setUpUi()
    }

    private fun setUpUi() {
        binding.apply {
            name.text = getString(
                R.string.profile_name, getStringFromPrefs(
                    requireContext(),
                    "name"
                ), getStringFromPrefs(requireContext(), "surname")
            )
            phone.text = getStringFromPrefs(requireContext(), "phone")
            buttonExit.setOnClickListener {
                viewModel.clearFavorites()
                deleteFromSharedPreferences(requireContext(),"name")
                deleteFromSharedPreferences(requireContext(),"surname")
                deleteFromSharedPreferences(requireContext(),"phone")
                findNavController().navigate(R.id.action_profileFragment_to_registrationFragment)
            }
            buttonFavorite.setOnClickListener {
                findNavController().navigate(R.id.action_profileFragment_to_favoritesCatalogue)
            }
        }
        viewModel.apply {
            favoritesAmount.observe(viewLifecycleOwner) {
                if (it > 0) {
                    binding.favoriteAmount.isVisible = true
                    binding.favoriteAmount.text = getWordForAmount(it,"товар")
                } else {
                    binding.favoriteAmount.isVisible = false
                }
            }
            getFavoritesAmount()
        }
    }

}