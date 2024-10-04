package com.example.theviewmodel.data.Api

import com.example.theviewmodel.model.ProductResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.http.GET



interface  ApiServices {
        @GET("products")
        suspend fun  getAllProducts(): ProductResponse
    }

