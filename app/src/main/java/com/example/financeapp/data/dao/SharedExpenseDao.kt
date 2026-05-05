package com.pae.app_finanzas_virtual.data.dao

import androidx.room.*
import com.example.financeapp.data.model.SharedExpense
import kotlinx.coroutines.flow.Flow

@Dao
interface SharedExpenseDao {

    // 1. Insertar gasto compartido
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(sharedExpense: SharedExpense)

    // 2. Obtener todos los gastos compartidos de un usuario (ordenados por fecha DESC)
    @Query("""
        SELECT * FROM shared_expenses
        WHERE creatorUserId = :userId
        ORDER BY date DESC
    """)
    fun getSharedExpensesByUser(userId: Long): Flow<List<SharedExpense>>

    // 3. Obtener gastos NO liquidados (settled = false → 0)
    @Query("""
        SELECT * FROM shared_expenses
        WHERE creatorUserId = :userId
        AND settled = 0
        ORDER BY date DESC
    """)
    fun getUnsettledSharedExpenses(userId: Long): Flow<List<SharedExpense>>

    // 4. Actualizar
    @Update
    suspend fun update(sharedExpense: SharedExpense)

    // 5. Eliminar
    @Delete
    suspend fun delete(sharedExpense: SharedExpense)
}