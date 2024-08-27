package com.talent.sweetp.viewmodel

// SharedViewModel.kt


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.talent.sweetp.api.QuoteApiService
import com.talent.sweetp.model.Quote
import com.talent.sweetp.repository.QuoteRepository
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {
    var sharedText = mutableStateOf("Initial State")
    var username = mutableStateOf("")
    var password = mutableStateOf("")
    var quote = mutableStateOf<Quote?>(null)
    var quoteList = mutableStateOf<List<Quote>>(emptyList())
    var selectedQuote = mutableStateOf<Quote?>(null)
    var user = mutableStateOf<FirebaseUser?>(null)
    var authError = mutableStateOf<String?>(null)

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    //private val database = FirebaseDatabase.getInstance().reference

    private val repository: QuoteRepository

    init {
        val apiService = QuoteApiService.create()
        repository = QuoteRepository(apiService)
        fetchQuotes(1)
    }

    fun updateText(newText: String) {
        sharedText.value = newText
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

    fun fetchQuotes(page: Int) {
        viewModelScope.launch {
            val response = repository.getQuotesByPage(page)
            if (response.isSuccessful) {
                quoteList.value = response.body()?.results ?: emptyList()
            } else {
                // Handle error
            }
        }
    }

    fun fetchQuoteById(id: String) {
        viewModelScope.launch {
            val response = repository.getQuoteById(id)
            if (response.isSuccessful) {
                selectedQuote.value = response.body()
            } else {
                // Handle error
            }
        }
    }

    // Reset selected quote
    fun resetSelectedQuote() {
        selectedQuote.value = null
    }

    fun registerUser(onRegistrationSuccess: () -> Unit) {
        viewModelScope.launch {
            if (username.value.isNotEmpty() && password.value.isNotEmpty()) {
                auth.createUserWithEmailAndPassword(username.value, password.value)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            user.value = auth.currentUser
                            // You can save additional user data in the database if needed
                            onRegistrationSuccess()
                        } else {
                            authError.value = task.exception?.message
                        }
                    }
            } else {
                authError.value = "Please enter a valid username and password."
            }
        }
    }

    fun loginUser(onLoginSuccess: () -> Unit) {
        viewModelScope.launch {
            if (username.value.isNotEmpty() && password.value.isNotEmpty()) {
                auth.signInWithEmailAndPassword(username.value, password.value)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            user.value = auth.currentUser
                            onLoginSuccess()
                        } else {
                            authError.value = task.exception?.message
                        }
                    }
            } else {
                authError.value = "Please enter a valid username and password."
            }
        }
    }

    fun signOut() {
        auth.signOut()
        user.value = null
    }

}
