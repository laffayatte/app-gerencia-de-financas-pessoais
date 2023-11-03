package ufam.projetodeprogramas.gerenciapessoal.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ufam.projetodeprogramas.gerenciapessoal.Events.CategoriesEvent
import ufam.projetodeprogramas.gerenciapessoal.dataclasses.Categories
import ufam.projetodeprogramas.gerenciapessoal.dataclasses.CategoriesState
import ufam.projetodeprogramas.gerenciapessoal.interfaces.CategoriesDAO

enum class SortType {
    BY_NAME
}

class CategoriesViewModel(
    private val dao : CategoriesDAO
): ViewModel(){
    private val _sortType = MutableStateFlow(SortType.BY_NAME)
    private val _state = MutableStateFlow(CategoriesState())

    @OptIn(ExperimentalCoroutinesApi::class)
    private val _contacts = _sortType.flatMapLatest { sortType ->
        when(sortType){
            SortType.BY_NAME -> dao.getAllCategories()
        }
        dao.getAllCategories()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val state = combine(_state, _sortType, _contacts){state, sortType, contacts ->
        state.copy(
            categories = contacts,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CategoriesState())

    fun onEvent(event: CategoriesEvent){
        when(event){
            CategoriesEvent.saveCategory -> {
                val category = state.value.category

                if(category.isBlank()){
                    return
                }
                val new_category = Categories(
                    category = category
                )
                viewModelScope.launch {
                    dao.upsertCategory(new_category)
                }
                _state.update{
                    it.copy(category = "", isAddingCategory = false)
                }
            }
            is CategoriesEvent.deleteCategory -> {
                viewModelScope.launch {
                    dao.deleteCategory(event.category)
                }
            }
            is CategoriesEvent.editCategory -> {
                val categoryToEdit = event.category

                Log.d("teste", "${_state.value.selectedCategory}")
                if (categoryToEdit.isBlank()) {
                    return
                }

                val existingCategory = _state.value.selectedCategory
                if (existingCategory != null) {
                    if (existingCategory.category == categoryToEdit) {
                        return
                    }
                }

                Log.d("teste","existingCategory: $existingCategory")

                if (existingCategory != null) {
                    existingCategory.category = state.value.category
                    viewModelScope.launch {
                        dao.upsertCategory(existingCategory)
                    }
                }
                _state.update {
                    it.copy(isEditCategory = false)
                }
            }
            is CategoriesEvent.setCategory ->{
                _state.update{
                    it.copy(category = event.category)
                }
            }
            is CategoriesEvent.setSelectedCategory -> {
                _state.update{
                    it.copy(selectedCategory = event.category)
                }
            }
            is CategoriesEvent.getAllCategories -> {
                _sortType.value = event.sortType
            }
            CategoriesEvent.AddHideDialog -> {
                _state.update{
                    it.copy(isAddingCategory = false)
                }
            }
            CategoriesEvent.AddShowDialog -> {
                _state.update{
                    it.copy(isAddingCategory = true)
                }
            }
            CategoriesEvent.editHideDialog -> {
                _state.update{
                    it.copy(isEditCategory = false, category = "")
                }
            }
            is CategoriesEvent.editShowDialog -> {
                _state.update{
                    it.copy(isEditCategory = true)
                }
            }
            else -> {}
        }
    }
}