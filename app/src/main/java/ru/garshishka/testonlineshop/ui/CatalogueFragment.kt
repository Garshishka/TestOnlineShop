package ru.garshishka.testonlineshop.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.chip.Chip
import ru.garshishka.testonlineshop.MainActivity
import ru.garshishka.testonlineshop.R
import ru.garshishka.testonlineshop.databinding.FragmentCatalogueBinding
import ru.garshishka.testonlineshop.viewholder.CatalogueItemAdapter
import ru.garshishka.testonlineshop.viewmodel.CatalogueViewModel
import java.io.BufferedReader
import java.io.InputStreamReader

class CatalogueFragment : Fragment() {
    private var _binding: FragmentCatalogueBinding? = null
    private val viewModel : CatalogueViewModel by activityViewModels()
    private val adapter = CatalogueItemAdapter()
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatalogueBinding.inflate(inflater, container, false)
        viewModel.parseJson(readJsonFile(requireContext(),R.raw.items))
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


        viewModel.catalogueItems.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
    }

}

fun readJsonFile(context: Context, resourceId: Int): String {
    val inputStream = context.resources.openRawResource(resourceId)
    val bufferedReader = BufferedReader(InputStreamReader(inputStream))
    val stringBuilder = StringBuilder()

    bufferedReader.useLines { lines ->
        lines.forEach { stringBuilder.append(it) }
    }

    return stringBuilder.toString()
}