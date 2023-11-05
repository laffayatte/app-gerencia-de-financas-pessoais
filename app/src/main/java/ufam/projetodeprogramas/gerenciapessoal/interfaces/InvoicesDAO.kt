package ufam.projetodeprogramas.gerenciapessoal.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ufam.projetodeprogramas.gerenciapessoal.dataclasses.Categories
import ufam.projetodeprogramas.gerenciapessoal.dataclasses.Invoices

@Dao
interface InvoicesDAO {
    @Insert
    suspend fun insertInvoice(invoice: Invoices)

    @Query("DELETE FROM Invoices")
    suspend fun deleteInvoices()

    @Query("SELECT SUM(Invoices.invoice)  FROM Invoices")
    fun getAllInvoicesTogether(): Float

    @Query("SELECT SUM(Invoices.invoice)  FROM Invoices WHERE invoice >= 0")
    fun getAllIncomes(): Float

    @Query("SELECT SUM(Invoices.invoice)  FROM Invoices WHERE invoice < 0")
    fun getAllExpenses():Float

    @Query("SELECT SUM(Invoices.invoice)  FROM Invoices WHERE category = :category")
    fun getExpensesByCategory(category: String): Float

    @Query("SELECT COUNT(Invoices.invoice)  FROM Invoices WHERE category = :category")
    fun getQuantityOfExpensesByCategory(category: String): Int

}