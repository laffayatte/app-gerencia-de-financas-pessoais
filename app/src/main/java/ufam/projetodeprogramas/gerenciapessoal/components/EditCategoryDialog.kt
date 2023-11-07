package ufam.projetodeprogramas.gerenciapessoal.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditCategoryDialog(
    categoryToEdit: String,
    onEvent: (CategoriesEvent) -> Unit,
    modifier: Modifier
) {
    AlertDialog(
        onDismissRequest = { onEvent(CategoriesEvent.editHideDialog) },
        confirmButton = {
            IconButton(
                onClick = {
                    onEvent(CategoriesEvent.editCategory(categoryToEdit))
                    onEvent(CategoriesEvent.editHideDialog)
                },
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(colorResource(id = R.color.purple_500))
                    .padding(12.dp)
            ) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = null)
            }
        },
        dismissButton = {
            IconButton(
                onClick = { onEvent(CategoriesEvent.editHideDialog) },
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(colorResource(id = R.color.purple_500))
                    .padding(12.dp)
            ) {
                Icon(imageVector = Icons.Default.Close, contentDescription = null)
            }
        },
        title = { Text(text = "Editar categoria") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = categoryToEdit,
                    onValueChange = {onEvent(CategoriesEvent.setCategory(it))},
                    placeholder = {
                        Text(text = "Nome da categoria")
                    }
                )
            }
        },
    )
}
