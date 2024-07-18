package com.talent.sweetp.myapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.talent.sweetp.viewmodel.SharedViewModel
import com.talent.sweetp.views.ScreenFour
import com.talent.sweetp.views.ScreenOne
import com.talent.sweetp.views.ScreenThree
import com.talent.sweetp.views.ScreenTwo

@Composable
fun MyApp() {
    val navController = rememberNavController()
    val sharedViewModel: SharedViewModel = viewModel()

    NavHost(navController = navController, startDestination = "screenOne") {
        composable("screenOne") { ScreenOneScreen(navController, sharedViewModel) }
        composable("screenTwo") { ScreenTwoScreen(navController, sharedViewModel) }
        composable("screenThree") { ScreenThreeScreen(navController, sharedViewModel) }
        composable("screenFour") { ScreenFourScreen(navController, sharedViewModel) }
    }
}

@Composable
fun ScreenOneScreen(navController: NavController, viewModel: SharedViewModel) {
    Column {
        ScreenOne(viewModel)
        Button(onClick = { navController.navigate("screenTwo") }, modifier = Modifier.fillMaxWidth()) {
            Text("Go to Screen Two")
        }
    }
}

@Composable
fun ScreenTwoScreen(navController: NavController, viewModel: SharedViewModel) {
    Column {
        ScreenTwo(viewModel)
        Button(onClick = { navController.navigate("screenThree") }, modifier = Modifier.fillMaxWidth()) {
            Text("Go to Screen Three")
        }
    }
}

@Composable
fun ScreenThreeScreen(navController: NavController, viewModel: SharedViewModel) {
    Column {
        ScreenThree(viewModel)
        Button(onClick = { navController.navigate("screenFour") }, modifier = Modifier.fillMaxWidth()) {
            Text("Go to Screen Four")
        }
    }
}

@Composable
fun ScreenFourScreen(navController: NavController, viewModel: SharedViewModel) {
    Column {
        ScreenFour(viewModel)
        Button(onClick = { navController.navigate("screenOne") }, modifier = Modifier.fillMaxWidth()) {
            Text("Go to Screen One")
        }
    }
}