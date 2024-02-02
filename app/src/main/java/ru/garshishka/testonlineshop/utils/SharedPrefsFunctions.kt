package ru.garshishka.testonlineshop.utils

import android.content.Context
import android.content.SharedPreferences

private const val PREFS_FILENAME = "user"

fun saveStringToPrefs(context: Context, key: String, value: String) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)
    val editor: SharedPreferences.Editor = sharedPreferences.edit()
    editor.putString(key, value)
    editor.apply()
}

fun getStringFromPrefs(context: Context, key: String): String? {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)
    return sharedPreferences.getString(key, null)
}