package com.talent.sweetp.viewmodel

// SharedViewModel.kt


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    var sharedText = mutableStateOf("Initial State")

    fun updateText(newText: String) {
        sharedText.value = newText
    }
}
