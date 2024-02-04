package ru.garshishka.testonlineshop.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import ru.garshishka.testonlineshop.MainActivity
import ru.garshishka.testonlineshop.R
import ru.garshishka.testonlineshop.databinding.FragmentFavoritesBinding
import ru.garshishka.testonlineshop.ui.tab.TabPagerAdapter

@AndroidEntryPoint
class FavoritesFragment : Fragment() {
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private lateinit var tabAdapter : TabPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.setToolbarTextViewText(requireContext().getString(R.string.fragment_favorites), true)
        tabAdapter= TabPagerAdapter(requireContext(),childFragmentManager)

        binding.apply {
            viewPager.adapter = tabAdapter
            tabs.setupWithViewPager(viewPager)
        }

    }
}