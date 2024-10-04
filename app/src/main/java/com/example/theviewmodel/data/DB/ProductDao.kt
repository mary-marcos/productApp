package com.example.theviewmodel.data.DB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.theviewmodel.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * FROM product_table")
    fun getAllProd():Flow<List<Product>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setAllProd(vararg prod: Product): List<Long>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setOneProd(prod: Product): Long


    @Delete
    suspend fun deleteOneProd(prod: Product): Int
}