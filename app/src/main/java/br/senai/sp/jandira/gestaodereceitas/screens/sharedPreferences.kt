package br.senai.sp.jandira.gestaodereceitas.screens

import android.content.Context

fun getUserIdFromPrefs(context: Context): Int {
    val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    val userIdString = sharedPreferences.getString("userId", "") ?: ""
    return userIdString.toIntOrNull() ?: 0
}
