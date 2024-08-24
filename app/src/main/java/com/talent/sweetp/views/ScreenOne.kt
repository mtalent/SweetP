package com.talent.sweetp.views


import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.talent.sweetp.viewmodel.SharedViewModel

@Composable
fun ScreenOne(viewModel: SharedViewModel) {
    Column {

        Button(onClick = { viewModel.fetchRandomQuote() }) {
            Text("Fetch Random Quote")
        }

        viewModel.quote.value?.let { quote ->
            Text(text = "Quote: ${quote.content}")
            Text(text = "Author: ${quote.author}")
        }


    }
}