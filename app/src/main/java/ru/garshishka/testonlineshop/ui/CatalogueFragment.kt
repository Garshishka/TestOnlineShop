package ru.garshishka.testonlineshop.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import ru.garshishka.testonlineshop.databinding.FragmentCatalogueBinding

class CatalogueFragment : Fragment() {
    private var _binding: FragmentCatalogueBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatalogueBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.chipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
            if (checkedIds.isNotEmpty()) {
                val selectedChip = group.findViewById<Chip>(checkedIds[0])
                selectedChip.isCloseIconVisible = true
                selectedChip.setOnCloseIconClickListener {
                    selectedChip.isCloseIconVisible = false
                    group.clearCheck()
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
    }

}