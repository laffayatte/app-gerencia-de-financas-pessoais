package ufam.projetodeprogramas.gerenciapessoal.dataclasses

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Invoices(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val invoice: Float,
    val date: String,
    val category: String,
)
