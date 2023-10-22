package ufam.projetodeprogramas.gerenciapessoal.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ufam.projetodeprogramas.gerenciapessoal.R

@Composable
fun WelcomeScreen(onNavigateToSummary: () -> Unit) {
    Column (
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.padding(10.dp))
        Image(
            painter = painterResource(id = R.drawable.app_icone_novo_sem_fundo),
            contentDescription = null,
        )
        Text(
            text = "Fortuna", fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.padding(10.dp))

        Text(
            text = "Gerencie suas finanças pessoais,\n      de forma simples e prática!",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Image(
            painter = painterResource(id = R.drawable.icone_de_amostra_cortado),
            contentDescription = null,
            modifier = Modifier.size(300.dp)
        )
        Button(
            onClick = { onNavigateToSummary() },
            modifier = Modifier
                .padding(10.dp)
                .size(120.dp, 45.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = "Entrar", fontSize = 20.sp)
            }
        }
    }
}