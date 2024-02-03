package ru.garshishka.testonlineshop.viewholder

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.garshishka.testonlineshop.R
import ru.garshishka.testonlineshop.databinding.CardCatalogueElementBinding
import ru.garshishka.testonlineshop.dto.CatalogueItem
import ru.garshishka.testonlineshop.utils.ImagePagerAdapter
import ru.garshishka.testonlineshop.utils.getImageArrayForItem

class CatalogueItemViewHolder(
    private val binding: CardCatalogueElementBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: CatalogueItem) {
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
            rating.isVisible = item.feedback != null
            ratingAmount.isVisible = item.feedback != null
            item.feedback?.let {
                rating.text = it.rating.toString()
                ratingAmount.text = buildString {
                    append("(")
                    append(it.count.toString())
                    append(")")
                }
            }
            val images = getImageArrayForItem(item.id)
            productImage.adapter = ImagePagerAdapter(itemView.context, images)
            indicatorView.apply {
                setIndicatorGap(resources.getDimension(R.dimen.small_indicator_gap_4dp))
                setupWithViewPager(productImage)
            }
        }
    }
}

class CatalogueItemAdapter() :
    ListAdapter<CatalogueItem, CatalogueItemViewHolder>(CatalogueItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogueItemViewHolder {
        val binding =
            CardCatalogueElementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatalogueItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CatalogueItemViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.bind(item)
    }

}

class CatalogueItemCallback : DiffUtil.ItemCallback<CatalogueItem>() {
    override fun areItemsTheSame(oldItem: CatalogueItem, newItem: CatalogueItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CatalogueItem, newItem: CatalogueItem): Boolean {
        return oldItem == newItem
    }

}