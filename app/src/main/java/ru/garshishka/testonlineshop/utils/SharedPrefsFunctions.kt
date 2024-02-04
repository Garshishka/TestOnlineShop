package ru.garshishka.testonlineshop.utils

import android.content.Context

private const val PREFS_FILENAME = "user"

fun saveStringToPrefs(context: Context, key: String, value: String) {
    context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE).edit()
        .putString(key, value)
        .apply()
}

fun getStringFromPrefs(context: Context, key: String): String? =
    context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE).getString(key, null)


fun deleteFromSharedPreferences(context: Context, key: String) {
    context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE).edit()
        .remove(key)
        .apply()
}