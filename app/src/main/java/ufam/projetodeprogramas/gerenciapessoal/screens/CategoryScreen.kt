package ufam.projetodeprogramas.gerenciapessoal.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.smallTopAppBarColors
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import ufam.projetodeprogramas.gerenciapessoal.Events.CategoriesEvent
import ufam.projetodeprogramas.gerenciapessoal.R
import ufam.projetodeprogramas.gerenciapessoal.components.AddCategoryDialog
import ufam.projetodeprogramas.gerenciapessoal.components.CustomNavigationDrawerItemColors
import ufam.projetodeprogramas.gerenciapessoal.components.EditCategoryDialog
import ufam.projetodeprogramas.gerenciapessoal.dataclasses.CategoriesState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(
    state: CategoriesState,
    onEvent: (CategoriesEvent) -> Unit,
    navController: NavController,
){
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.size(250.dp, 800.dp),
                drawerContainerColor = colorResource(
                    id = R.color.white,
                ),
            ){
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.orange))){
                    Text("Navegação", modifier = Modifier
                        .padding(16.dp))
                }
                Divider()
                NavigationDrawerItem(
                    label = { Text(text = "Página inicial") },
                    selected = false,
                    onClick = { navController.navigate("summaryScreen"); coroutineScope.launch{ drawerState.close()} },
                    colors = CustomNavigationDrawerItemColors()
                )
                NavigationDrawerItem(
                    label = { Text(text = "Categorias") },
                    selected = true,
                    onClick = { navController.navigate("categoryScreen"); coroutineScope.launch{ drawerState.close()} },
                    colors = CustomNavigationDrawerItemColors()
                )

            }
        },
        gesturesEnabled = true,
    ) {
        Scaffold(
            containerColor = colorResource(id = R.color.gray),
            topBar = {
                TopAppBar(
                    title = { Text(text = "Categorias") },
                    navigationIcon = {
                        IconButton(onClick = { coroutineScope.launch { drawerState.open() } }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu"
                            )
                        }
                    },
                    colors = smallTopAppBarColors(containerColor = colorResource(id = R.color.orange))
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { onEvent(CategoriesEvent.AddShowDialog) },
                    modifier = Modifier
                        .padding(16.dp)
                        .padding(bottom = 16.dp),
                    containerColor = colorResource(id = R.color.orange)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Adicionar",
                    )
                }
            },
        ){ innerPadding ->
            if (state.isAddingCategory){
                AddCategoryDialog(
                    state = state,
                    onEvent = onEvent,
                    modifier = Modifier
                        .padding(innerPadding)
                )
            }
            if (state.isEditCategory) {
                EditCategoryDialog(
                    categoryToEdit = state.category,
                    onEvent = onEvent,
                    modifier = Modifier.padding(innerPadding),
                )
            }

            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(state.categories){ category ->
                    Row(
                        modifier = Modifier.fillMaxWidth().background(colorResource(id = R.color.white)),
                    ){
                        Column (
                            modifier = Modifier.weight(1f)
                        ){
                            Text(
                                text = category.category,
                                fontSize = 20.sp,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                        IconButton(
                            onClick = { onEvent(CategoriesEvent.deleteCategory(category)) }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete Category"
                            )
                        }
                        IconButton(
                            onClick = { onEvent(CategoriesEvent.editShowDialog); onEvent(CategoriesEvent.setSelectedCategory(category))}
                        ) {
                            Icon(
                                imageVector = Icons.Default.Create,
                                contentDescription = "Edit Category"
                            )
                        }
                    }

                }
            }
        }
    }
}

