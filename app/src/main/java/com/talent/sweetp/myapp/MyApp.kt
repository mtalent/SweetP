package com.talent.sweetp.myapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.talent.sweetp.viewmodel.SharedViewModel
import com.talent.sweetp.views.LoginScreen
import com.talent.sweetp.views.ScreenFour
import com.talent.sweetp.views.ScreenOne
import com.talent.sweetp.views.ScreenThree
import com.talent.sweetp.views.ScreenTwo
import com.talent.sweetp.views.SelectedQuote

@Composable
fun MyApp() {
    val navController = rememberNavController()
    val sharedViewModel: SharedViewModel = viewModel()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") { screenLoginScreen(navController, sharedViewModel) }
        composable("screenOne") { ScreenOneScreen(navController, sharedViewModel) }
        composable("screenTwo") { ScreenTwoScreen(navController, sharedViewModel) }
        composable("screenThree") { ScreenThreeScreen(navController, sharedViewModel) }
        composable("screenFour") { ScreenFourScreen(navController, sharedViewModel) }
    }
}


@Composable
fun screenLoginScreen(navController: NavController, viewModel: SharedViewModel) {
    LoginScreen(viewModel) {
        navController.navigate("screenOne") {
            popUpTo("login") { inclusive = true }
        }
    }
}

@Composable
fun ScreenOneScreen(navController: NavController, viewModel: SharedViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // ScreenOne content
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            ScreenOne(viewModel)
        }

        // Navigation Buttons: Previous (disabled) and Next
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .height(50.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { /* No previous screen for Screen One */ },
                enabled = false,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Previous", fontSize = 18.sp)
            }
            Button(
                onClick = { navController.navigate("screenTwo") },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Next", fontSize = 18.sp)
            }
        }
    }
}

@Composable
fun ScreenTwoScreen(navController: NavController, viewModel: SharedViewModel) {
    LaunchedEffect(Unit) {
        viewModel.resetSelectedQuote()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Conditional content: show either the list or the selected quote
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            if (viewModel.selectedQuote.value == null) {
                // Show the list of quotes
                ScreenTwo(viewModel)
            } else {
                // Show the selected quote
                SelectedQuote(viewModel.selectedQuote.value!!)
            }
        }

        // Navigation Buttons: Previous and Next
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .height(50.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Previous", fontSize = 18.sp)
            }
            Button(
                onClick = {
                    navController.navigate("screenThree")
                    //viewModel.resetSelectedQuote()
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Next", fontSize = 18.sp)
            }
        }
    }
}

@Composable
fun ScreenThreeScreen(navController: NavController, viewModel: SharedViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // ScreenThree content
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            ScreenThree(viewModel)
        }

        // Navigation Buttons: Previous and Next
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .height(50.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Previous", fontSize = 18.sp)
            }
            Button(
                onClick = { navController.navigate("screenFour") },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Next", fontSize = 18.sp)
            }
        }
    }
}

@Composable
fun ScreenFourScreen(navController: NavController, viewModel: SharedViewModel) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // ScreenFour content (Trivia Game)
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            ScreenFour(viewModel)
        }

        // Navigation Buttons: Previous and Next (disabled)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .height(50.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Previous", fontSize = 18.sp)
            }
            Button(
                onClick = { /* No next screen for Screen Four */ },
                enabled = false,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Next", fontSize = 18.sp)
            }
        }
    }
}

