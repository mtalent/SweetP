package com.talent.sweetp.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.talent.sweetp.model.Quote
import com.talent.sweetp.viewmodel.SharedViewModel

@Composable
fun ScreenTwo(viewModel: SharedViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Quotes List",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // LazyColumn for displaying the list of quotes
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(viewModel.quoteList.value) { quote ->
                QuoteItem(quote) {
                    viewModel.fetchQuoteById(quote._id)
                }
            }
        }

        // Display selected quote
        viewModel.selectedQuote.value?.let { selectedQuote ->
            Text(
                text = "\"${selectedQuote.content}\"",
                fontSize = 20.sp,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            Text(
                text = "- ${selectedQuote.author}",
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
    }
}

@Composable
fun QuoteItem(quote: Quote, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        Text(
            text = quote.author,
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = quote.content.split(" ").take(10).joinToString(" ") + "...",
            fontSize = 16.sp
        )
    }
}
