package com.talent.sweetp.views


import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.talent.sweetp.viewmodel.SharedViewModel

@Composable
fun ScreenTwo(viewModel: SharedViewModel) {
    Column {
        Text(text = viewModel.sharedText.value)
        Button(onClick = { viewModel.updateText("Updated from Screen Two") }) {
            Text("Update Text")
        }
    }
}