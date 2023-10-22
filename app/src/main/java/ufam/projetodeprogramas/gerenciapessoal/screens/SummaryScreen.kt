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
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ufam.projetodeprogramas.gerenciapessoal.R
import ufam.projetodeprogramas.gerenciapessoal.components.CircularProgressBar
import ufam.projetodeprogramas.gerenciapessoal.components.PieChart

@Composable
fun SummaryScreen(){

    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState(), enabled = true)
    ){
        Box(
            modifier = Modifier
                .size(400.dp, 150.dp)
                .background(colorResource(id = R.color.blue_ciano))
        ){
            IconButton(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart),
                onClick = { /* Ação ao clicar no ícone do hamburguer */ }
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Abrir menu",
                    tint = Color.White
                )
            }
            IconButton(
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
            Text(
                text = "Saldo Total",
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .padding(top = 16.dp),
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "R$ 100,00",
                modifier = Modifier
                    .align(alignment = Alignment.Center)
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
                            modifier = Modifier.padding(bottom = 16.dp)
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
                                    text = "R$ 100,00",
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
                                    text = "R$ 0,00",
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
                        fontWeight = FontWeight.Bold
                    )
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Top
                    ) {
                        Box(modifier = Modifier.padding(start = 25.dp, top = 45.dp)) {
                            CircularProgressBar(percentage = 0.8f, number = 100)
                        }
                        Spacer(modifier = Modifier.padding(8.dp))
                        Box(Modifier.padding(start = 40.dp)) {
                            Text(
                                text = "R$ 100,00",
                                color = Color.Black,
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
                        Text(text = "R$ 100,00", color = Color.Green)
                        Spacer(modifier = Modifier.padding(8.dp))
                        Text(
                            text = "Despesas consideradas",
                            fontSize = 12.sp,
                            color = colorResource(id = R.color.dark_gray)
                        )
                        Text(text = "R$ 0,00", color = Color.Red)
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
                        Text(text = "Despesas por categoria", fontWeight = FontWeight.Bold)

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
