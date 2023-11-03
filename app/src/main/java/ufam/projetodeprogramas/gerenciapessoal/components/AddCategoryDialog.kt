package ufam.projetodeprogramas.gerenciapessoal.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import ufam.projetodeprogramas.gerenciapessoal.Events.CategoriesEvent
import ufam.projetodeprogramas.gerenciapessoal.R
import ufam.projetodeprogramas.gerenciapessoal.dataclasses.CategoriesState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCategoryDialog(
    state: CategoriesState,
    onEvent: (CategoriesEvent) -> Unit,
    modifier: Modifier
) {
    AlertDialog(
        onDismissRequest = { onEvent(CategoriesEvent.AddHideDialog) },
        confirmButton = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IconButton(
                    onClick = {onEvent(CategoriesEvent.saveCategory)},
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(colorResource(id = R.color.purple_500))
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Adicionar categoria",
                        tint = colorResource(id = R.color.gray),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        },
        dismissButton = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IconButton(
                    onClick = {onEvent(CategoriesEvent.AddHideDialog)},
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(colorResource(id = R.color.purple_500))
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "sair do adicionar categoria",
                        tint = colorResource(id = R.color.gray),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        },
        title = { Text(text = "Adicionar categoria") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = state.category,
                    onValueChange = {onEvent(CategoriesEvent.setCategory(it))},
                    placeholder = {
                        Text(text = "Nome da categoria")
                    }
                )
            }
        },
    )
}