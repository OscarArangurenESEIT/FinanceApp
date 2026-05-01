
import androidx.room.*
import com.example.financeapp.data.model.Budget
import com.example.financeapp.data.model.ExpenseCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface BudgetDao {

    // 1. Insertar (reemplaza si existe)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(budget: Budget)

    // 2. Obtener todos los presupuestos por mes y año
    @Query("""
        SELECT * FROM budgets 
        WHERE userId = :userId 
        AND month = :month 
        AND year = :year
    """)
    fun getBudgetsByMonthYear(
        userId: Long,
        month: Int,
        year: Int
    ): Flow<List<Budget>>

    // 3. Obtener presupuesto por categoría, mes y año
    @Query("""
        SELECT * FROM budgets 
        WHERE userId = :userId 
        AND category = :category 
        AND month = :month 
        AND year = :year
        LIMIT 1
    """)
    fun getBudgetByCategoryMonthYear(
        userId: Long,
        category: ExpenseCategory,
        month: Int,
        year: Int
    ): Flow<Budget?>

    // 4. Actualizar
    @Update
    suspend fun update(budget: Budget)

    // 5. Eliminar
    @Delete
    suspend fun delete(budget: Budget)
}