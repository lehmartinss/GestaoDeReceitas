package br.senai.sp.jandira.gestaodereceitas.screens

import android.content.Context

fun getUserIdFromPrefs(context: Context): Int {
    val sharedPrefs = context.getSharedPreferences("usuario_prefs", Context.MODE_PRIVATE)
    return sharedPrefs.getInt("user_id", -1)
}