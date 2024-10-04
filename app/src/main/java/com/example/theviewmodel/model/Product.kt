package com.example.theviewmodel.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "product_table")
data class Product(@PrimaryKey var id:Int,
                   var title: String, var description: String, var price: Float, var thumbnail: String? = null
): Serializable

