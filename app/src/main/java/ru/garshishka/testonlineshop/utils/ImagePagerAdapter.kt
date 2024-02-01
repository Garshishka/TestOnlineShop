package ru.garshishka.testonlineshop.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import ru.garshishka.testonlineshop.R

class ImagePagerAdapter(private val parentContext: Context, private val images: IntArray) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(parentContext)
        val view: View = inflater.inflate(R.layout.page_image, container, false)

        val imageView: ImageView = view.findViewById(R.id.imageView)
        imageView.setImageResource(images[position])

        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }
}