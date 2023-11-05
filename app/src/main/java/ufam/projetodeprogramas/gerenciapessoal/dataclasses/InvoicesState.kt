package ufam.projetodeprogramas.gerenciapessoal.dataclasses

import ufam.projetodeprogramas.gerenciapessoal.viewmodels.SortedType

data class InvoicesState (
    val invoiceString: String = "",
    val invoice: Float = 0.0f,
    val incomes: Float = 0.0f,
    val expenses: Float = 0.0f,
    val date: String = "",
    val category: String = "",
    val isAddingInvoice: Boolean = false,
    val isEditInvoice: Boolean = false,
    val selectedInvoice: Invoices? = null,
    val sortType: SortedType = SortedType.Invoices
)