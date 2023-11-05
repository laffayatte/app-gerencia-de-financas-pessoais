package ufam.projetodeprogramas.gerenciapessoal.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import ufam.projetodeprogramas.gerenciapessoal.interfaces.InvoicesDAO

@Database(
    entities = [ufam.projetodeprogramas.gerenciapessoal.dataclasses.Invoices::class],
    version = 1,
    exportSchema = false
)
abstract class InvoicesDatabase: RoomDatabase(){
    abstract val invoicesDAO: InvoicesDAO
}