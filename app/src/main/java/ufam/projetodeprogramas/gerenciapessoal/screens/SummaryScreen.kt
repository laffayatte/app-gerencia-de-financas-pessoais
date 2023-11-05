package ufam.projetodeprogramas.gerenciapessoal.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.rounded.Add
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import ufam.projetodeprogramas.gerenciapessoal.Events.CategoriesEvent
import ufam.projetodeprogramas.gerenciapessoal.Events.InvoicesEvent
import ufam.projetodeprogramas.gerenciapessoal.R
import ufam.projetodeprogramas.gerenciapessoal.components.CircularProgressBar
import ufam.projetodeprogramas.gerenciapessoal.components.CustomNavigationDrawerItemColors
import ufam.projetodeprogramas.gerenciapessoal.components.PieChart
import ufam.projetodeprogramas.gerenciapessoal.dataclasses.CategoriesState
import ufam.projetodeprogramas.gerenciapessoal.dataclasses.InvoicesState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SummaryScreen(
    navController: NavController,
    categoryState: CategoriesState,
    onCategoryEvent: (CategoriesEvent) -> Unit,
    onInvoiceEvent: (InvoicesEvent) -> Unit,
    invoicesState: InvoicesState
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(invoicesState){
        onInvoiceEvent(InvoicesEvent.getAllInvoices)
        onInvoiceEvent(InvoicesEvent.getAllExpenses)
        onInvoiceEvent(InvoicesEvent.getAllIncomes)
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.size(250.dp, 800.dp),
                drawerContainerColor = colorResource(
                    id = R.color.white,
                ),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colorResource(id = R.color.blue_ciano))
                ) {
                    Text(
                        "Navegação", modifier = Modifier
                            .padding(16.dp)
                    )
                }
                Divider()
                NavigationDrawerItem(
                    label = { Text(text = "Página inicial") },
                    selected = true,
                    onClick = { navController.navigate("summaryScreen"); coroutineScope.launch { drawerState.close() } },
                    colors = CustomNavigationDrawerItemColors()
                )
                NavigationDrawerItem(
                    label = { Text(text = "Categorias") },
                    selected = false,
                    onClick = { navController.navigate("categoryScreen"); coroutineScope.launch { drawerState.close() } },
                    colors = CustomNavigationDrawerItemColors()
                )

            }
        },
        gesturesEnabled = true,
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .background(colorResource(id = R.color.blue_ciano))) {
                            Text(
                                text = "Saldo Total",
                                modifier = Modifier.align(Alignment.TopCenter),
                                color = colorResource(id = R.color.white),
                                fontWeight = FontWeight.Bold
                            )
                        }

                    },
                    colors = smallTopAppBarColors(containerColor = colorResource(id = R.color.blue_ciano)),
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                coroutineScope.launch { drawerState.open() }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Abrir menu",
                                tint = Color.White
                            )
                        }
                    },
                    actions = {
                            Box(){IconButton(
                            modifier = Modifier
                                .align(alignment = Alignment.TopEnd),
                            onClick = { /* Ação ao clicar no ícone de overflow */ }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.MoreVert,
                                    contentDescription = "Abrir menu de opções",
                                    tint = Color.White
                                )
                            }
                        }
                    }
                )

            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { navController.navigate("addInvokesScreen") },
                    modifier = Modifier
                        .padding(16.dp)
                        .padding(bottom = 16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Adicionar",
                    )
                }
            },
        ) { innerPadding ->
            Column (
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .verticalScroll(state = rememberScrollState(), enabled = true)
            ){
                Box(
                    modifier = Modifier
                        .size(400.dp, 90.dp)
                        .background(colorResource(id = R.color.blue_ciano))
                ){
                    Text(
                        text = "R$ "+invoicesState.invoice.toString(),
                        modifier = Modifier
                            .align(alignment = Alignment.TopCenter)
                            .padding(top = 16.dp),
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
                Box(
                    modifier = Modifier
                        .size(400.dp, 650.dp)
                        .background(colorResource(id = R.color.gray))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {//Primeiro bloco
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(160.dp)
                                .shadow(elevation = 2.dp, shape = RoundedCornerShape(8.dp))
                                .background(Color.White, shape = RoundedCornerShape(8.dp))
                                .padding(16.dp)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Top
                            ) {
                                Text(
                                    text = "Visão Geral",
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(bottom = 16.dp),
                                    color = colorResource(id = R.color.black)
                                )
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(4.dp),
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .background(
                                                color = colorResource(id = R.color.dark_green),
                                                shape = RoundedCornerShape(20.dp)
                                            )
                                            .padding(4.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Rounded.Add,
                                            contentDescription = "Receitas",
                                            tint = Color.White,
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }
                                    Text(
                                        text = "Receitas",
                                        color = Color.Black,
                                        modifier = Modifier.padding(start = 12.dp)
                                    )
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        contentAlignment = Alignment.CenterEnd
                                    ) {
                                        Text(
                                            text = "R$ "+ invoicesState.incomes.toString(),
                                            color = Color.Black,
                                            modifier = Modifier
                                                .padding(end = 12.dp)
                                                .align(alignment = Alignment.CenterEnd)
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.padding(4.dp))
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(4.dp),
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .background(
                                                color = colorResource(id = R.color.red),
                                                shape = RoundedCornerShape(20.dp)
                                            )
                                            .padding(4.dp)
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.negative),
                                            contentDescription = "Despesas",
                                            tint = Color.White,
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }
                                    Text(
                                        text = "Despesas",
                                        color = Color.Black,
                                        modifier = Modifier.padding(start = 12.dp)
                                    )
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        contentAlignment = Alignment.CenterEnd
                                    ) {
                                        Text(
                                            text = "R$ "+ invoicesState.expenses.toString(),
                                            color = Color.Black,
                                            modifier = Modifier
                                                .padding(end = 12.dp)
                                                .align(alignment = Alignment.CenterEnd)
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.padding(8.dp))
                        // Segundo bloco
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .shadow(
                                    elevation = 2.dp,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .background(Color.White, RoundedCornerShape(8.dp))
                                .padding(8.dp)
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "Economia Mensal",
                                fontWeight = FontWeight.Bold,
                                color = colorResource(id = R.color.black)
                            )
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Top
                            ) {
                                Box(modifier = Modifier.padding(start = 25.dp, top = 45.dp)) {
                                    val difference = invoicesState.incomes + invoicesState.expenses
                                    val differenceTotal = difference/invoicesState.incomes
                                    CircularProgressBar(percentage = differenceTotal, number = 100)
                                }
                                Spacer(modifier = Modifier.padding(8.dp))
                                Box(Modifier.padding(start = 40.dp)) {
                                    Text(
                                        text = "R$ " +(invoicesState.incomes + invoicesState.expenses).toString(),
                                        color = if (invoicesState.incomes + invoicesState.expenses >= 0){
                                            Color.Green
                                        } else if(invoicesState.incomes + invoicesState.expenses < 0){
                                            Color.Red
                                        } else{
                                            Color.Black
                                        },
                                        modifier = Modifier
                                            .padding(end = 12.dp)
                                            .align(alignment = Alignment.CenterEnd)
                                    )
                                }
                                Box(Modifier.padding(start = 23.dp)) {
                                    Text(
                                        text = "Valor economizado",
                                        fontSize = 12.sp,
                                        color = colorResource(id = R.color.dark_gray),
                                        modifier = Modifier
                                            .padding(end = 12.dp)
                                            .align(alignment = Alignment.CenterEnd)
                                    )
                                }
                            }
                            Column(
                                modifier = Modifier
                                    .fillMaxSize(),
                                horizontalAlignment = Alignment.End
                            ) {
                                Spacer(modifier = Modifier.padding(25.dp))
                                Text(
                                    text = "Receitas consideradas",
                                    fontSize = 12.sp,
                                    color = colorResource(id = R.color.dark_gray)
                                )
                                Text(text = "R$ "+invoicesState.incomes.toString() , color = Color.Green)
                                Spacer(modifier = Modifier.padding(8.dp))
                                Text(
                                    text = "Despesas consideradas",
                                    fontSize = 12.sp,
                                    color = colorResource(id = R.color.dark_gray)
                                )
                                Text(text = "R$ "+invoicesState.expenses.toString() , color = Color.Red)
                            }
                        }

                        Spacer(modifier = Modifier.padding(8.dp))
                        // Terceiro bloco
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(280.dp)
                                .shadow(
                                    elevation = 2.dp,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .background(Color.White, RoundedCornerShape(8.dp))
                                .padding(16.dp)

                        ) {
                            Column (
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Top
                            ){
                                Text(text = "Despesas por categoria", fontWeight = FontWeight.Bold, color = colorResource(id = R.color.black))

                                Spacer(modifier = Modifier.size(15.dp))

                                Box(
                                    modifier = Modifier.padding(start = 35.dp, top = 10.dp)
                                ){
                                    PieChart(
                                        data = mapOf(
                                            Pair("Sample-1", 150),
                                            Pair("Sample-2", 120),
                                            Pair("Sample-3", 110),
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
