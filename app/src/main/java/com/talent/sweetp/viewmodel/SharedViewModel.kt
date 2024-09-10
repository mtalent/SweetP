package com.talent.sweetp.viewmodel

// SharedViewModel.kt


import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.talent.sweetp.api.ApiService
import com.talent.sweetp.model.Joke
import com.talent.sweetp.model.Quote
import com.talent.sweetp.model.TriviaQuestion
import com.talent.sweetp.repository.Repository
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
    var joke = mutableStateOf<Joke?>(null)

    var triviaQuestions = mutableStateOf<List<TriviaQuestion>>(emptyList())
    var currentQuestionIndex = mutableIntStateOf(0)
    var selectedAnswer = mutableStateOf<String?>(null)
    var isAnswerCorrect = mutableStateOf<Boolean?>(null)
    var playerCards = mutableStateOf<List<String>>(emptyList())
        private set

    var dealerCards = mutableStateOf<List<String>>(emptyList())
        private set
    var allCards = initializeDeck()

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    //private val database = FirebaseDatabase.getInstance().reference

    private val repository: Repository

    init {
        val apiService = ApiService.createQuoteApi()
        val jokeApiService = ApiService.createJokeApi()
        val triviaApiService = ApiService.createTriviaApi()
        repository = Repository(apiService, jokeApiService, triviaApiService)
        fetchQuotes(5)
        fetchTriviaQuestions()
        dealInitialCards()


    }





    fun updateText(newText: String) {
        sharedText.value = newText
    }

    fun fetchRandomJoke() {
        viewModelScope.launch {
            val response = repository.getRandomJoke()
            if (response.isSuccessful) {
                joke.value = response.body()
            } else {
                // Handle error
            }
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

    fun fetchTriviaQuestions() {
        viewModelScope.launch {
            val response = repository.getTriviaQuestions()
            if (response.isSuccessful) {
                triviaQuestions.value = response.body()?.results ?: emptyList()
                currentQuestionIndex.value = 0
                selectedAnswer.value = null
                isAnswerCorrect.value = null
            } else {
                // Handle error
            }
        }
    }

    fun checkAnswer(selected: String) {
        val correctAnswer = triviaQuestions.value[currentQuestionIndex.value].correct_answer
        isAnswerCorrect.value = selected == correctAnswer
    }

    fun nextQuestion() {
        if (currentQuestionIndex.value < triviaQuestions.value.size - 1) {
            currentQuestionIndex.value += 1
            selectedAnswer.value = null
            isAnswerCorrect.value = null
        }
    }


    // Holds the state of the cards for both player and dealer


    // Function to initialize or shuffle a new hand
    fun dealInitialCards() {
        playerCards.value = allCards.take(2) // Assign first two cards to player
        dealerCards.value = allCards.takeLast(2) // Assign last two cards to dealer
    }

    fun initializeDeck(deck: MutableList<String> = mutableListOf<String>()) : MutableList<String> {
        val suits = listOf("hearts", "diamonds", "clubs", "spades")
        val faceCards = listOf("jack", "queen", "king")

        // Loop through suits
        for (suit in suits) {
            // Loop through number cards 1 to 10
            for (value in 1..10) {
                val cardName = "${value}_of_$suit"
                val cardUrl = "http://kotliniskool.com/cards/${cardName.trim()}.png".trim()
                deck.add(cardUrl)
            }
            // Loop through face cards
            for (face in faceCards) {
                val cardName = "${face}_of_$suit"
                val cardUrl = "http://kotliniskool.com/cards/${cardName.trim()}.png".trim()
                deck.add(cardUrl)
            }
        }

        // Shuffle the deck before returning
        deck.shuffle()

        return deck
    }




}
