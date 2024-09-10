package com.talent.sweetp.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage


@Composable
fun BlackjackTable(playerCards: List<String>, dealerCards: List<String>) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        // Dealer's cards positioned close to the top
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp), // Adjust as needed for more precise control
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top
        ) {
            dealerCards.forEach { cardName ->
                AsyncImage(
                    model = cardName,
                    contentDescription = null,
                    modifier = Modifier
                        .width(100.dp)
                        .height(150.dp)
                        .padding(4.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp)) // Adjust spacing between dealer and player cards

        // Player's cards stay in place as usual
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Bottom
        ) {
            playerCards.forEach { cardName ->
                AsyncImage(
                    model = cardName,
                    contentDescription = null,
                    modifier = Modifier
                        .width(100.dp)
                        .height(150.dp)
                        .padding(4.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp)) // Space between player cards and buttons

        // Hit and Stay buttons below the player cards
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { /* Hit action */ }) {
                Text("Hit")
            }
            Button(onClick = { /* Stay action */ }) {
                Text("Stay")
            }
        }
    }
}
