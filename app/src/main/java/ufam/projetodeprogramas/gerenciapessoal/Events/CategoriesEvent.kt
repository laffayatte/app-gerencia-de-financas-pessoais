package ufam.projetodeprogramas.gerenciapessoal.Events

import ufam.projetodeprogramas.gerenciapessoal.dataclasses.Categories
import ufam.projetodeprogramas.gerenciapessoal.viewmodels.SortType

sealed interface CategoriesEvent{
    object saveCategory: CategoriesEvent
    data class editCategory(val category: String) : CategoriesEvent
    object AddHideDialog: CategoriesEvent
    object AddShowDialog: CategoriesEvent
    object editHideDialog: CategoriesEvent
    object editShowDialog: CategoriesEvent
    data class setCategory(val category: String): CategoriesEvent
    data class setSelectedCategory(val category: Categories): CategoriesEvent
    data class deleteCategory(val category: Categories): CategoriesEvent
    data class getAllCategories(val sortType: SortType): CategoriesEvent
    data class findCategory(val category: String): CategoriesEvent
}