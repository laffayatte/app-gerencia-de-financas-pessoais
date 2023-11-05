package ufam.projetodeprogramas.gerenciapessoal.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ufam.projetodeprogramas.gerenciapessoal.R
import ufam.projetodeprogramas.gerenciapessoal.dataclasses.InvoicesState

@Composable
fun BlockOfCategoriesByExpenses(
    invoicesState: InvoicesState
){
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