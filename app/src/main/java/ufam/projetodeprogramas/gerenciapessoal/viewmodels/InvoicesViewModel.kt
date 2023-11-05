package ufam.projetodeprogramas.gerenciapessoal.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ufam.projetodeprogramas.gerenciapessoal.Events.InvoicesEvent
import ufam.projetodeprogramas.gerenciapessoal.dataclasses.Invoices
import ufam.projetodeprogramas.gerenciapessoal.dataclasses.InvoicesState
import ufam.projetodeprogramas.gerenciapessoal.interfaces.InvoicesDAO

enum class SortedType{
    Invoices,
    Incomes,
    Expenses
}

class InvoicesViewModel(
    private val dao : InvoicesDAO
): ViewModel(){
    private val _state = MutableStateFlow(InvoicesState())
    private val _sortedType = MutableStateFlow(SortedType.Invoices)

//    val state = combine(_state, _sortedType){state, sortedType ->
//        state.copy(
//            invoice = dao.getAllInvoicesTogether(),
//            expenses = dao.getAllExpenses(),
//            incomes = dao.getAllIncomes(),
//            sortType = sortedType
//        )
//    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), InvoicesState())
    val state = combine(_state, _sortedType) { state, sortedType ->
        val invoice = viewModelScope.async(Dispatchers.IO) {
            dao.getAllInvoicesTogether()
        }
        val expenses = viewModelScope.async(Dispatchers.IO) {
            dao.getAllExpenses()
        }
        val incomes = viewModelScope.async(Dispatchers.IO) {
            dao.getAllIncomes()
        }

        state.copy(
            invoice = invoice.await(),
            expenses = expenses.await(),
            incomes = incomes.await(),
            sortType = sortedType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), InvoicesState())

    fun onEvent(event: InvoicesEvent){
        when(event){
            InvoicesEvent.saveInvoice -> {
                Log.d("InvoicesViewModel", "onState: ${state.value}")
                val invoice = state.value.invoiceString.toFloatOrNull() ?: 0.0f
                val category = state.value.category
                val date = state.value.date
                Log.d("InvoicesViewModel", "onEvent: $invoice | $category | $date")
                if(invoice.isNaN() || category.isBlank() || date.isBlank()){
                    return
                }
                val new_invoice = Invoices(
                    invoice = invoice,
                    category = category,
                    date = date
                )
                Log.d("InvoicesViewModel", "onEvent: $new_invoice")
                viewModelScope.launch {
                    dao.insertInvoice(new_invoice)
                }
                _state.update{
                    it.copy(invoice = 0.0f, invoiceString = "", isAddingInvoice = false)
                }
            }
            is InvoicesEvent.setInvoice -> {
                val invoice = event.invoice.toFloatOrNull() ?: 0.0f
                val invoiceString = event.invoice
                Log.d("InvoicesViewModel", "onEvent: $invoice")
                Log.d("InvoicesViewModel", "onEvent: $invoiceString")
                _state.update{
                    Log.d("InvoicesViewModel", "$invoice")
                    it.copy(invoice = invoice , invoiceString = invoiceString)
                }
            }
            is InvoicesEvent.setDate -> {
                val date = event.date
                _state.update{
                    it.copy(date = date)
                }
            }
            is InvoicesEvent.setCategory -> {
                val category = event.category
                Log.d("InvoicesViewModel", "onEvent: $category")
                _state.update{
                    it.copy(category = category)
                }
            }
            InvoicesEvent.getAllInvoices -> {
                _sortedType.update{
                    SortedType.Invoices
                }
            }
            InvoicesEvent.getAllExpenses -> {
                _sortedType.update{
                    SortedType.Expenses
                }
            }
            InvoicesEvent.getAllIncomes -> {
                _sortedType.update{
                    SortedType.Incomes
                }
            }
            InvoicesEvent.deleteInvoices -> {
                viewModelScope.launch {
                    dao.deleteInvoices()
                }
            }
            else -> {}
        }
    }
}