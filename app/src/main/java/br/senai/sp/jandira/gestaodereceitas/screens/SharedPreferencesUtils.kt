package br.senai.sp.jandira.gestaodereceitas.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

object SharedPreferencesUtils {
    private const val PREFS_NAME = "UserPrefs"
    private const val USER_ID_KEY = "user_id_key"

    // Salva o ID do usuário
    fun saveUserId(context: Context, userId: Int) {
        val sharedPrefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        editor.putInt(USER_ID_KEY, userId)
        editor.apply() // <<< AQUI estava o erro!
        Log.d("SharedPreferencesUtils", "ID do usuário salvo: $userId")
    }

    // Recupera o ID do usuário, retorna 0 se não existir
    fun getUserId(context: Context): Int {
        val sharedPrefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val userId = sharedPrefs.getInt(USER_ID_KEY, 0)
        Log.d("SharedPreferencesUtils", "ID do usuário recuperado: $userId")
        return userId
    }
}
