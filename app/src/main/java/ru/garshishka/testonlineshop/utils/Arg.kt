package ru.garshishka.testonlineshop.utils

import android.os.Bundle
import ru.garshishka.testonlineshop.dto.CatalogueItem
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

object ItemArg : ReadWriteProperty<Bundle, CatalogueItem?> {
    override fun getValue(thisRef: Bundle, property: KProperty<*>): CatalogueItem? =
        thisRef.getParcelable(property.name)

    override fun setValue(thisRef: Bundle, property: KProperty<*>, value: CatalogueItem?) {
        thisRef.putParcelable(property.name, value)
    }
}