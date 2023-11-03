package ufam.projetodeprogramas.gerenciapessoal.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import ufam.projetodeprogramas.gerenciapessoal.interfaces.CategoriesDAO

@Database(
    entities = [ufam.projetodeprogramas.gerenciapessoal.dataclasses.Categories::class],
    version = 1,
    exportSchema = false
)
abstract class CategoriesDatabase: RoomDatabase(){
    abstract val categoriesDAO: CategoriesDAO


}