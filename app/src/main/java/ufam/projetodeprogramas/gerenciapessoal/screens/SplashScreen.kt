package ufam.projetodeprogramas.gerenciapessoal.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ufam.projetodeprogramas.gerenciapessoal.R

@Composable
fun SplashScreen(navigateToMainScreen: () -> Unit) {
    var alpha by remember { mutableStateOf(0.0f) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        alpha = 0.0f
        coroutineScope.launch {
            for (i in 0..10) {
                alpha = i / 10.0f
                delay(100)
            }
            navigateToMainScreen()
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.app_icone_novo),
            contentDescription = null,
            modifier = Modifier.alpha(alpha)
        )
    }
}
