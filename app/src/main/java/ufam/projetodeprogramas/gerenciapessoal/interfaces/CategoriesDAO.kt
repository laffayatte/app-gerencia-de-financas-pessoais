package ufam.projetodeprogramas.gerenciapessoal.interfaces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import ufam.projetodeprogramas.gerenciapessoal.dataclasses.Categories

@Dao
interface CategoriesDAO {

    @Upsert
    suspend fun upsertCategory(category: Categories)

    @Delete
    suspend fun deleteCategory(category: Categories)

    @Query("SELECT * FROM Categories ORDER BY category ASC")
    fun getAllCategories(): Flow<List<Categories>>

    @Query("SELECT * FROM Categories WHERE category = :category")
    fun findCategory(category: String): Categories?
}