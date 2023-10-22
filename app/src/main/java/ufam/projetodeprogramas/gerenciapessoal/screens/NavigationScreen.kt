package ufam.projetodeprogramas.gerenciapessoal.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController

@Composable
fun NavigationScreen(navController: NavController) {
    var showSplash by remember { mutableStateOf(true) }

    val navigateToMainScreen = {
        showSplash = false
    }

    Column {
        if (showSplash) {
            SplashScreen(navigateToMainScreen)
        } else {
            WelcomeScreen { // Chame a função WelcomeScreen com o callback
                navController.navigate("summaryScreen")
            }
        }
    }
}