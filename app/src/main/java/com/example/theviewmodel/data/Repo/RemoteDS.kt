package com.example.theviewmodel.data.Repo

import com.example.theviewmodel.data.Api.ApiServices
import com.example.theviewmodel.model.Product
import com.example.theviewmodel.model.ProductResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RemoteDS(private val apiService: ApiServices) {

    companion object {
        @Volatile
        private var INSTANCE: RemoteDS? = null

        fun getInstance(): RemoteDS {
            return INSTANCE ?: synchronized(this) {
                val instance = RemoteDS(RetrofitHelper.apiServiceInstance)
                INSTANCE = instance
                instance
            }
        }
    }

    // API call method
    suspend fun getProduct(): Flow <List<Product>?> {
        return flow {val result= apiService.getAllProducts().getAllProduct()
        emit(result)
        }

    }
}

object RetrofitHelper {
    private const val BASE_URL = "https://dummyjson.com/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiServiceInstance: ApiServices by lazy {
        retrofit.create(ApiServices::class.java)
    }
}