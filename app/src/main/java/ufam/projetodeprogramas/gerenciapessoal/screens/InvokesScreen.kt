package ufam.projetodeprogramas.gerenciapessoal.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ufam.projetodeprogramas.gerenciapessoal.Events.CategoriesEvent
import ufam.projetodeprogramas.gerenciapessoal.Events.InvoicesEvent
import ufam.projetodeprogramas.gerenciapessoal.R
import ufam.projetodeprogramas.gerenciapessoal.components.DropDownMenu
import ufam.projetodeprogramas.gerenciapessoal.dataclasses.CategoriesState
import ufam.projetodeprogramas.gerenciapessoal.dataclasses.InvoicesState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun InvokesScreen(
    state: CategoriesState,
    invoicesState: InvoicesState,
    onCategoryEvent: (CategoriesEvent) -> Unit,
    onInvokeEvent: (InvoicesEvent) -> Unit,
    navController: NavController,
){
    val focusManager = LocalFocusManager.current
    val keybordController = LocalSoftwareKeyboardController.current

    val textFieldValueInvoices = remember {
        mutableStateOf(
            TextFieldValue(
                text = "",
                selection = TextRange(0)
            )
        )
    }
    val textFieldValueDate = remember {
        mutableStateOf(
            TextFieldValue(
                text = "",
                selection = TextRange(0)
            )
        )
    }
    Scaffold(
        containerColor = colorResource(id = R.color.gray),
        topBar = {
            TopAppBar(
                title = { Text(text = "Nova transação") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("summaryScreen") }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Voltar"
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = colorResource(id = R.color.green_light)),
                actions = {
                    IconButton(
                        onClick = {onInvokeEvent(InvoicesEvent.saveInvoice); navController.popBackStack()},
                        modifier = Modifier.padding(8.dp)
                    ){
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Adicionar categoria",
                            tint = colorResource(id = R.color.white)
                        )
                    }
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Valor",
                        modifier = Modifier
                            .padding(start = 8.dp, end = 3.dp)
                            .size(28.dp),

                    )
                    OutlinedTextField(
                        value = textFieldValueInvoices.value,
                        onValueChange = {
                            textFieldValueInvoices.value = TextFieldValue(
                                text = it.text.replace(Regex("[^0-9.-]"), ""),
                                selection = TextRange(it.text.length)
                            )
                            Log.d("InvoicesViewModel", "Invoice value: $it")
                            Log.d("InvoicesViewModel", "Invoice value: ${it.text}")
                            onInvokeEvent(InvoicesEvent.setInvoice(it.text))
                        },
                        maxLines = 1,
                        label = {
                            Text(text = "Valor", color = colorResource(id = R.color.darker_gray))
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = colorResource(id = R.color.white),
                            focusedIndicatorColor = colorResource(id = R.color.green_light),
                            unfocusedIndicatorColor = colorResource(id = R.color.green_light),
                            cursorColor = colorResource(id = R.color.green_light),
                            textColor = colorResource(id = R.color.green_light),
                            placeholderColor = colorResource(id = R.color.green_light),
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(onDone = {focusManager.clearFocus(); keybordController?.hide()})
                    )
                }
                Text(
                    text = "*Números positivos são rendimentos e negativos são gastos",
                    color = colorResource(id = R.color.red),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp, end = 3.dp).align(Alignment.CenterHorizontally)
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ){

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "data",
                        modifier = Modifier
                            .padding(start = 8.dp, end = 3.dp)
                            .size(28.dp),

                        )
                    OutlinedTextField(
                        value = textFieldValueDate.value,
                        onValueChange = {
                            textFieldValueDate.value = TextFieldValue(
                                text = it.text.replace(Regex("[^0-9/]"), ""),
                                selection = TextRange(it.text.length)
                            )
                            onInvokeEvent(InvoicesEvent.setDate(it.text))
                        },
                        label = {
                            Text(text = "Data", color = colorResource(id = R.color.darker_gray))
                        },
                        maxLines = 1,
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = colorResource(id = R.color.white),
                            focusedIndicatorColor = colorResource(id = R.color.green_light),
                            unfocusedIndicatorColor = colorResource(id = R.color.green_light),
                            cursorColor = colorResource(id = R.color.green_light),
                            textColor = colorResource(id = R.color.green_light),
                            placeholderColor = colorResource(id = R.color.green_light),
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(onDone = {focusManager.clearFocus(); keybordController?.hide()})
                    )
                }
                Text(
                    text = "*Formato esperado dd/mm/aaaa",
                    color = colorResource(id = R.color.red),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 20.dp, end = 3.dp)
                )
            }

            Divider(modifier = Modifier.padding(6.dp).fillMaxWidth(), thickness = 2.dp)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = "data",
                    modifier = Modifier
                        .padding(start = 8.dp, end = 3.dp)
                        .size(28.dp),
                    )
                DropDownMenu(state, onInvoicesEvent = onInvokeEvent, invoicesState = invoicesState)
            }
        }
    }
}