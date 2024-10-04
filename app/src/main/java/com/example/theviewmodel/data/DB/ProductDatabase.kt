package com.example.theviewmodel.data.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.theviewmodel.model.Product


@Database(entities = [Product::class], version = 1)
abstract class ProductDatabase : RoomDatabase() {


    abstract fun productDao(): ProductDao

    companion object {
        // Singleton instance of the database
        @Volatile
        private var INSTANCE: ProductDatabase? = null


        fun getInstance(context: Context): ProductDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductDatabase::class.java,
                    "product_database"
                )

                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}