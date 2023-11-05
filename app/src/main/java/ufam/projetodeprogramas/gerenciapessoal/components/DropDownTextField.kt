package ufam.projetodeprogramas.gerenciapessoal.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.toSize
import ufam.projetodeprogramas.gerenciapessoal.Events.InvoicesEvent
import ufam.projetodeprogramas.gerenciapessoal.R
import ufam.projetodeprogramas.gerenciapessoal.dataclasses.CategoriesState
import ufam.projetodeprogramas.gerenciapessoal.dataclasses.InvoicesState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenu(
    state: CategoriesState,
    onInvoicesEvent: (InvoicesEvent) -> Unit,
    invoicesState: InvoicesState
){

    var expanded by remember { mutableStateOf(false) }
    val suggestions = state.categories
    var selectedText by remember { mutableStateOf("") }

    var textfieldSize by remember { mutableStateOf(Size.Zero)}

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    Column(Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = selectedText,
            readOnly = true,
            onValueChange = {
                Log.d("InvoicesViewModel", "DropDownMenu: $it")
                selectedText = it

            },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textfieldSize = coordinates.size.toSize()
                },
            label = {Text("Categorias")},
            trailingIcon = {
                Icon(icon,"contentDescription",
                    Modifier.clickable { expanded = !expanded })
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = colorResource(id = R.color.white),
                focusedIndicatorColor = colorResource(id = R.color.orange),
                unfocusedIndicatorColor = colorResource(id = R.color.orange),
                cursorColor = colorResource(id = R.color.orange),
                textColor = colorResource(id = R.color.orange),
                placeholderColor = colorResource(id = R.color.orange),
            )
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current){textfieldSize.width.toDp()})
        ) {
            suggestions.forEach{ label ->
                DropdownMenuItem(
                    text = { Text(text = label.category) },
                    onClick = {
                    selectedText = label.category
                    expanded = false
                    onInvoicesEvent(InvoicesEvent.setCategory(label.category))
                    }
                )
            }
        }
    }

}