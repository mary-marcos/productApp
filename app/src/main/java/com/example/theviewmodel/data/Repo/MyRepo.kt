package com.example.theviewmodel.data.Repo

import com.example.theviewmodel.model.Product
import com.example.theviewmodel.model.ProductResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow


class MyRepo(private val RemoteSource: RemoteDS,private val localDB: LocalDB) {


    companion object {
        // Singleton instance
        @Volatile
        private var INSTANCE: MyRepo? = null


        fun getInstance( RemoteSource: RemoteDS,localDB:LocalDB ): MyRepo {

            return INSTANCE ?: synchronized(this) {
                val instance = MyRepo(RemoteSource,localDB)

                INSTANCE = instance
                instance
            }
        }
    }



    suspend fun  getProduct(): Flow <List<Product>?>
    { val prodData = RemoteSource.getProduct()
        return prodData
    }


    suspend fun  insertProduct(Prod:Product): Long
    { val result = localDB.insertProd(Prod)
        return result
    }

    suspend fun  getLocalProduct(): Flow<List<Product>>
    { val prodData = localDB.getStoredProd()
        return prodData
    }
}