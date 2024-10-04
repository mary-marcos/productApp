package com.example.theviewmodel.data.Repo

import android.content.Context
import com.example.theviewmodel.data.DB.ProductDao
import com.example.theviewmodel.data.DB.ProductDatabase
import com.example.theviewmodel.model.Product
import kotlinx.coroutines.flow.Flow

class LocalDB(private val myproductDao: ProductDao) {

    companion object {
        @Volatile
        private var INSTANCE: LocalDB? = null

        fun getInstance(context: Context): LocalDB {
            return INSTANCE ?: synchronized(this) {
                val dao = ProductDatabase.getInstance(context).productDao()
                val instance = LocalDB(dao)
                INSTANCE = instance
                instance
            }
        }
    }

    suspend fun insertProd(prod: Product):Long {
       var result= myproductDao.setOneProd(prod)
        return result
    }

    suspend fun deleteProd(prod: Product) {
        myproductDao.deleteOneProd(prod)  // Add delete operation in DAO
    }

    suspend fun getStoredProd(): Flow<List<Product>> {
        return myproductDao.getAllProd()
    }
}
