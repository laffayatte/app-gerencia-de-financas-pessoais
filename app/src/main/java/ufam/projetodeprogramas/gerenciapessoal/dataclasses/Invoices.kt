package ufam.projetodeprogramas.gerenciapessoal.dataclasses

data class Invoices(
    val id: Int,
    val invoice: Float,
    val date: String,
    val category: String,
)
