package br.senai.sp.jandira.gestaodereceitas.screens

import android.net.Uri
import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import java.util.UUID

fun uploadImageToFirebase(uri: Uri, onSuccess: (String) -> Unit, onError: (Exception) -> Unit) {
    val storage = Firebase.storage
    val storageRef = storage.reference

    // Cria uma referência com nome único
    val imageRef = storageRef.child("imagens/${UUID.randomUUID()}.jpg")

    val uploadTask = imageRef.putFile(uri)

    uploadTask.addOnSuccessListener {
        // Pega a URL da imagem enviada
        imageRef.downloadUrl.addOnSuccessListener { downloadUri ->
            onSuccess(downloadUri.toString())
        }.addOnFailureListener {
            onError(it)
        }
    }.addOnFailureListener {
        onError(it)
    }
}
