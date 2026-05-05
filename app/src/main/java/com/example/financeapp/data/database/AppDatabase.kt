package com.example.financeapp.data.database

import BudgetDao
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.financeapp.data.dao.ExpenseDao
import com.example.financeapp.data.dao.UserDao
import com.example.financeapp.data.model.Budget
import com.example.financeapp.data.model.Expense
import com.example.financeapp.data.model.SharedExpense
import com.example.financeapp.data.model.User
import com.pae.app_finanzas_virtual.data.dao.SharedExpenseDao

@Database(
    entities = [User::class, Expense::class, Budget::class, SharedExpense::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao() : UserDao
    abstract fun expenseDao() : ExpenseDao
    abstract fun budgetDao() : BudgetDao
    abstract fun sharedExpenseDao() : SharedExpenseDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "finance_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}