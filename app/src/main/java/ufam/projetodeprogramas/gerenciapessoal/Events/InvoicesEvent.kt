package ufam.projetodeprogramas.gerenciapessoal.Events

sealed interface InvoicesEvent{
    object saveInvoice: InvoicesEvent
    data class setInvoice(val invoice: String): InvoicesEvent
    data class setDate(val date: String): InvoicesEvent
    data class setCategory(val category: String): InvoicesEvent
    object deleteInvoices: InvoicesEvent
    object getAllInvoices: InvoicesEvent
    object getAllIncomes: InvoicesEvent
    object getAllExpenses: InvoicesEvent
}