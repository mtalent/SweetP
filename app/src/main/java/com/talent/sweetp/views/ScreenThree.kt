package com.talent.sweetp.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.talent.sweetp.viewmodel.SharedViewModel

@Composable
fun ScreenThree(viewModel: SharedViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Title
        Text(
            text = "RANDOM JOKE",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Fetch Joke Button
        Button(
            onClick = { viewModel.fetchRandomJoke() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .height(50.dp)
        ) {
            Text(text = "Fetch Random Joke", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Displaying Joke
        viewModel.joke.value?.let { joke ->
            Text(
                text = joke.joke,
                fontSize = 20.sp,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }
    }
}
