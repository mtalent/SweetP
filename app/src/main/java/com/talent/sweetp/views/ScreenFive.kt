package com.talent.sweetp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.talent.sweetp.viewmodel.SharedViewModel

@Composable
fun ScreenFive(viewModel: SharedViewModel) {
    val cityName = remember { mutableStateOf("") }
    val weatherData = viewModel.weatherData.value

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(16.dp)
    ) {
        // Title
        Text(text = "Weather Checker", fontSize = 24.sp)

        Spacer(modifier = Modifier.height(16.dp))

        // TextField for city input
        TextField(
            value = cityName.value,
            onValueChange = { cityName.value = it },
            label = { Text("Enter City Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Button to fetch weather
        Button(onClick = {
            if (cityName.value.isNotEmpty()) {
                viewModel.fetchWeather(cityName.value, "0416d64d47a0d891f5f0f97d4d974d28") // Using your API key
            }
        }) {
            Text(text = "Get Weather")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display weather data
        if (weatherData != null) {
            Text(text = "City: ${weatherData.name}")
            Text(text = "Temperature: ${weatherData.main.temp}°C")
            Text(text = "Weather: ${weatherData.weather[0].description}")
            // Example of displaying the weather icon (using Coil or similar library for image loading)
            Image(
                painter = rememberAsyncImagePainter("http://openweathermap.org/img/wn/${weatherData.weather[0].icon}@2x.png"),
                contentDescription = "Weather Icon"
            )
        } else {
            Text(text = "Enter a city to check the weather or no data available.")
        }
    }
}
