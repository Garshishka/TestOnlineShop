package ru.garshishka.testonlineshop.ui.tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.garshishka.testonlineshop.R
import ru.garshishka.testonlineshop.databinding.TabFragmentRecyclerviewBinding
import ru.garshishka.testonlineshop.dto.CatalogueItem
import ru.garshishka.testonlineshop.ui.ProductFragment.Companion.itemArg
import ru.garshishka.testonlineshop.utils.UserInteractionListener
import ru.garshishka.testonlineshop.viewholder.CatalogueItemAdapter
import ru.garshishka.testonlineshop.viewmodel.CatalogueViewModel

@AndroidEntryPoint
class TabRecycleViewFragment : Fragment() {
    private var _binding: TabFragmentRecyclerviewBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CatalogueViewModel by activityViewModels()

    private val userInteractionListener = object : UserInteractionListener {
        override fun onFavoriteClick(id: String) {
            viewModel.favorite(id)
        }

        override fun onCardClick(item: CatalogueItem) {
            findNavController().navigate(R.id.action_favoritesCatalogue_to_productFragment,
                Bundle().apply
                { itemArg = item })
        }
    }

    private val adapter = CatalogueItemAdapter(userInteractionListener)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TabFragmentRecyclerviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            catalogueItems.adapter = adapter
        }

        viewModel.apply {
            favoriteItems.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }
    }
}