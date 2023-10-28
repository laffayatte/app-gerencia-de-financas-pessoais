package ufam.projetodeprogramas.gerenciapessoal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ufam.projetodeprogramas.gerenciapessoal.screens.CategoryScreen
import ufam.projetodeprogramas.gerenciapessoal.screens.NavigationScreen
import ufam.projetodeprogramas.gerenciapessoal.screens.SummaryScreen
import ufam.projetodeprogramas.gerenciapessoal.ui.theme.GerenciaFinancasPessoaisTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GerenciaFinancasPessoaisTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "navigationScreen"
                ) {
                    composable("navigationScreen") {
                        NavigationScreen(navController)
                    }
                    composable("summaryScreen") {
                        SummaryScreen(navController)
                    }
                    composable("categoryScreen") {
                        CategoryScreen(navController)
                    }
                }
                val onBackPressedCallback = object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        if (navController.currentBackStackEntry?.destination?.route == "summaryScreen") {
                            finish()
                        } else {
                            navController.popBackStack()
                        }
                    }
                }
                onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
            }
        }
    }

}
