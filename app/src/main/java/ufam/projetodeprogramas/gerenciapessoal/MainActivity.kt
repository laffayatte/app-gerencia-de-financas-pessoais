package ufam.projetodeprogramas.gerenciapessoal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import ufam.projetodeprogramas.gerenciapessoal.databases.CategoriesDatabase
import ufam.projetodeprogramas.gerenciapessoal.databases.InvoicesDatabase
import ufam.projetodeprogramas.gerenciapessoal.screens.CategoryScreen
import ufam.projetodeprogramas.gerenciapessoal.screens.InvokesScreen
import ufam.projetodeprogramas.gerenciapessoal.screens.NavigationScreen
import ufam.projetodeprogramas.gerenciapessoal.screens.SummaryScreen
import ufam.projetodeprogramas.gerenciapessoal.ui.theme.GerenciaFinancasPessoaisTheme
import ufam.projetodeprogramas.gerenciapessoal.viewmodels.CategoriesViewModel
import ufam.projetodeprogramas.gerenciapessoal.viewmodels.InvoicesViewModel

class MainActivity : ComponentActivity() {
    private val db_category by lazy {
        Room.databaseBuilder(
            applicationContext,
            CategoriesDatabase::class.java,
            name = "categories_database"
        ).build()
    }
    private val db_invoices by lazy {
        Room.databaseBuilder(
            applicationContext,
            InvoicesDatabase::class.java,
            name = "invoices_database"
        ).build()
    }

    private val CategoryViewModel by viewModels<CategoriesViewModel>(
        factoryProducer = {
            object: ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return CategoriesViewModel(db_category.categoriesDAO) as T
                }
            }
        }
    )
    private val InvoicesViewModel by viewModels<InvoicesViewModel>(
        factoryProducer = {
            object: ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return InvoicesViewModel(db_invoices.invoicesDAO) as T
                }
            }
        }
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GerenciaFinancasPessoaisTheme {
                val navController = rememberNavController()
                val categoryState by CategoryViewModel.state.collectAsState()
                val invoicesState by InvoicesViewModel.state.collectAsState()

                NavHost(
                    navController = navController,
                    startDestination = "navigationScreen"
                ) {
                    composable("navigationScreen") {
                        NavigationScreen(navController)
                    }
                    composable("summaryScreen") {
                        SummaryScreen(
                            navController = navController,
                            categoryState = categoryState,
                            onCategoryEvent = CategoryViewModel::onEvent,
                            invoicesState = invoicesState,
                            onInvoiceEvent = InvoicesViewModel::onEvent
                        )
                    }
                    composable("categoryScreen") {
                        CategoryScreen(
                            state = categoryState,
                            onEvent = CategoryViewModel::onEvent,
                            navController = navController,
                        )
                    }
                    composable("addInvokesScreen"){
                        InvokesScreen(
                            navController = navController,
                            state = categoryState,
                            invoicesState = invoicesState,
                            onCategoryEvent = CategoryViewModel::onEvent,
                            onInvokeEvent = InvoicesViewModel::onEvent
                        )
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
