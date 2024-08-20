package com.talent.sweetp.viewmodel

// SharedViewModel.kt


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    var sharedText = mutableStateOf("Initial State")
    var username = mutableStateOf("")
    var password = mutableStateOf("")

    fun updateText(newText: String) {
        sharedText.value = newText
    }

    fun loginUser(onLoginSuccess: () -> Unit) {
        if (username.value.isNotEmpty() && password.value.isNotEmpty()) {
            // Implement your login logic here
            onLoginSuccess()
        }
    }
}
