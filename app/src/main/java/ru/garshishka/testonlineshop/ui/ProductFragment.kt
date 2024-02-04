package ru.garshishka.testonlineshop.ui

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.garshishka.testonlineshop.MainActivity
import ru.garshishka.testonlineshop.R
import ru.garshishka.testonlineshop.databinding.FragmentProductBinding
import ru.garshishka.testonlineshop.dto.CatalogueItem
import ru.garshishka.testonlineshop.utils.ImagePagerAdapter
import ru.garshishka.testonlineshop.utils.ItemArg
import ru.garshishka.testonlineshop.utils.getWordForAmount
import ru.garshishka.testonlineshop.utils.getWordForAvailable
import ru.garshishka.testonlineshop.viewmodel.CatalogueViewModel

@AndroidEntryPoint
class ProductFragment : Fragment() {
    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CatalogueViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val item = arguments?.itemArg
        (activity as? MainActivity)?.setToolbarTextViewText("")
        if (item != null) {
            setUpUi(item)
        }
    }

    private fun setUpUi(item: CatalogueItem) {
        var descriptionShow = true
        var contentsShow = false
        binding.apply {
            oldPrice.text = buildString {
                append(item.price.discount.toString())
                append(" ")
                append(item.price.unit)
            }
            oldPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            price.text = buildString {
                append(item.price.price.toString())
                append(" ")
                append(item.price.unit)
            }
            discountAmount.text = buildString {
                append("-")
                append(item.price.discount.toString())
                append("%")
            }
            title.text = item.title
            subtitle.text = item.subtitle
            feedback.isVisible = item.feedback != null
            feedbackRating.isVisible = item.feedback != null
            item.feedback?.let {
                feedbackRating.rating = item.feedback.rating.toFloat()
                feedback.text = buildString {
                    append(it.rating)
                    append(" · ")
                    append(it.count.toString())
                    append(" ")
                    append(getWordForAmount(it.count, "отзыв"))
                }
            }
            productImage.adapter = ImagePagerAdapter(requireContext(), item)
            indicatorView.apply {
                setIndicatorGap(resources.getDimension(R.dimen.big_indicator_gap_6dp))
                setupWithViewPager(productImage)
            }
            favorite.isChecked = item.favorite
            favorite.setOnClickListener { viewModel.favorite(item.id) }
            available.text = buildString {
                append("Доступно для заказа ")
                append(item.available)
                append(" ")
                append(getWordForAvailable(item.available))
            }
            brand.text = item.title
            textDescription.text = item.description
            buttonHideDescription.setOnClickListener {
                descriptionShow = !descriptionShow
                textDescription.isVisible = descriptionShow
                containerButtonBrand.isVisible = descriptionShow
                buttonHideDescription.text = if (descriptionShow) "Скрыть" else "Подробнее"
            }
            for (infoItem in item.info) {
                val infoView =
                    LayoutInflater.from(requireContext()).inflate(R.layout.card_info, null)

                infoView.findViewById<TextView>(R.id.info_title).text = infoItem.title
                infoView.findViewById<TextView>(R.id.info_value).text = infoItem.value

                infoPanel.addView(infoView)
            }
            textContents.text = item.ingredients
            textContents.viewTreeObserver.addOnGlobalLayoutListener {
                buttonHideContents.isVisible = textContents.lineCount > 2 || textContents.layout.getEllipsisCount(textContents.lineCount - 1) > 0
            }
            buttonHideContents.setOnClickListener {
                contentsShow = !contentsShow
                textContents.maxLines = if (contentsShow) Integer.MAX_VALUE else 2
                buttonHideContents.text = if (contentsShow) "Скрыть" else "Подробнее"
            }
        }
    }

    companion object {
        var Bundle.itemArg by ItemArg
    }
}