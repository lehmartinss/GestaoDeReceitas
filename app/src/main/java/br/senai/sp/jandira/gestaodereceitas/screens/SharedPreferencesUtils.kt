package br.senai.sp.jandira.gestaodereceitas.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

object SharedPreferencesUtils {
    private const val PREFS_NAME = "user_prefs"
    private const val USER_ID_KEY = "user_id_key"

    fun saveUserId(context: Context, userId: Int) {
        val sharedPrefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPrefs.edit()
        editor.putInt(USER_ID_KEY, userId)
        editor.apply()
        // Mantendo a tag de log original para o filtro 'SharedPreferencesUtils'
        Log.d("SharedPreferencesUtils", "ID do usuario salvo: $userId")
    }

    fun getUserId(context: Context): Int {
        val sharedPrefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val userId = sharedPrefs.getInt(USER_ID_KEY, 0)
        Log.d("SharedPreferencesUtils", "ID do usuario recuperado: $userId")
        return userId
    }

    fun clearUserId(context: Context) {
        val sharedPrefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPrefs.edit()
        editor.remove(USER_ID_KEY)
        editor.apply()
        Log.d("SharedPreferencesUtils", "ID do usuario limpo das SharedPreferences.")
    }
}