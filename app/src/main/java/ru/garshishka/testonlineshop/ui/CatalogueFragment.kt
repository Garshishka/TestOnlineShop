package ru.garshishka.testonlineshop.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import ru.garshishka.testonlineshop.MainActivity
import ru.garshishka.testonlineshop.R
import ru.garshishka.testonlineshop.databinding.FragmentCatalogueBinding
import ru.garshishka.testonlineshop.utils.UserInteractionListener
import ru.garshishka.testonlineshop.utils.enums.FilterType
import ru.garshishka.testonlineshop.utils.enums.SortType
import ru.garshishka.testonlineshop.utils.enums.getFilterType
import ru.garshishka.testonlineshop.viewholder.CatalogueItemAdapter
import ru.garshishka.testonlineshop.viewmodel.CatalogueViewModel

@AndroidEntryPoint
class CatalogueFragment : Fragment() {
    private var _binding: FragmentCatalogueBinding? = null
    private val binding get() = _binding!!
    private val viewModel : CatalogueViewModel by activityViewModels()

    private val userInteractionListener = object : UserInteractionListener{
        override fun onFavoriteClick(id: String) {
            viewModel.favorite(id)
        }
    }

    private val adapter = CatalogueItemAdapter(userInteractionListener)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatalogueBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? MainActivity)?.setToolbarTextViewText(requireContext().getString(R.string.fragment_catalogue))

        binding.apply {
            catalogueItems.adapter = adapter
            chipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
                if (checkedIds.isNotEmpty()) {
                    val selectedChip = group.findViewById<Chip>(checkedIds[0])
                    viewModel.changeFilter(getFilterType(selectedChip.id))
                    selectedChip.isCloseIconVisible = true
                    selectedChip.setOnCloseIconClickListener {
                        selectedChip.isCloseIconVisible = false
                        group.clearCheck()
                        viewModel.changeFilter(FilterType.ALL)
                    }

                    for (chipId in group.children.map { it.id }) {
                        if (chipId != checkedIds[0]) {
                            group.findViewById<Chip>(chipId).isCloseIconVisible = false
                        }
                    }
                } else {
                    for (chipId in group.children.map { it.id }) {
                        group.findViewById<Chip>(chipId).isCloseIconVisible = false
                    }
                }
            }
            sortSpinner.setText(getString(R.string.sort_popular),false)
            sortSpinner.setOnItemClickListener { _, _, _, l ->
                viewModel.changeSort(
                    when (l){
                        0L -> SortType.POPULARITY
                        1L -> SortType.PRICE_DOWN
                        2L -> SortType.PRICE_UP
                        else -> SortType.PRICE_DOWN
                    }
                )
            }
        }


        viewModel.apply {
            catalogueItems.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }
    }

}