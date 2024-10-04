package com.example.theviewmodel.UI

import com.example.theviewmodel.model.Product

//
//sealed class StateManager<out T> {
//
//    object Loading : StateManager<Nothing>()
//
//
//    data class Success<out T>(val data: T) : StateManager<T>()
//
//
//    data class Failure(val error: String) : StateManager<Nothing>()
//}



sealed class StateManager{

    object Loading : StateManager()

    data class Success(val data:  List<Product>) : StateManager()


    data class Failure(val error: String) : StateManager()
}