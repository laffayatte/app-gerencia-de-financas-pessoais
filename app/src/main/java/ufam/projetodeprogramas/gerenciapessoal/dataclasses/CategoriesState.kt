package ufam.projetodeprogramas.gerenciapessoal.dataclasses

import ufam.projetodeprogramas.gerenciapessoal.viewmodels.SortType

data class CategoriesState(
    val categories: List<Categories> = emptyList(),
    val category: String = "",
    val isAddingCategory: Boolean = false,
    val isEditCategory: Boolean = false,
    val selectedCategory: Categories? = null,
    val sortType: SortType = SortType.BY_NAME
)
