package ufam.projetodeprogramas.gerenciapessoal.dataclasses

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Categories(
    var category: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)
