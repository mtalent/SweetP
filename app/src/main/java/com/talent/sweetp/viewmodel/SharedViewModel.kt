package com.talent.sweetp.viewmodel

// SharedViewModel.kt


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.talent.sweetp.api.QuoteApiService
import com.talent.sweetp.model.Quote
import com.talent.sweetp.repository.QuoteRepository
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {
    var sharedText = mutableStateOf("Initial State")
    var username = mutableStateOf("")
    var password = mutableStateOf("")
    var quote = mutableStateOf<Quote?>(null)

    private val repository: QuoteRepository

    init {
        val apiService = QuoteApiService.create()
        repository = QuoteRepository(apiService)
    }

    fun updateText(newText: String) {
        sharedText.value = newText
    }

    fun loginUser(onLoginSuccess: () -> Unit) {
        if (username.value.isNotEmpty() && password.value.isNotEmpty()) {
            // Implement your login logic here
            onLoginSuccess()
        }
    }

    fun fetchRandomQuote() {
        viewModelScope.launch {
            val response = repository.getRandomQuote()
            if (response.isSuccessful) {
                quote.value = response.body()
            } else {
                // Handle error
            }
        }
    }

}
