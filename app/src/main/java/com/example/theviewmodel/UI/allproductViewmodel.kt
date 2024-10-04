package com.example.theviewmodel.UI

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.theviewmodel.data.Repo.MyRepo
import com.example.theviewmodel.data.Repo.RemoteDS
import com.example.theviewmodel.model.ProductResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import androidx.lifecycle.viewModelScope
import com.example.theviewmodel.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.cache
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class allproductViewmodel (var myRepo:MyRepo): ViewModel() {
  //  var myRepo:MyRepo= MyRepo(RemoteDS.getInstance())

    private val _productDataObserve = MutableStateFlow<StateManager>(StateManager.Loading)


    val productDataObserve = _productDataObserve.asStateFlow()

    fun getCurrentWeatherData(){


        viewModelScope.launch(Dispatchers.IO) {
            myRepo.getProduct().catch {
                _productDataObserve.value=StateManager.Failure("error")
            }.collect{data->_productDataObserve.value = StateManager.Success(data!!)}




        }

    }
    fun inserProd(prod:Product){
        viewModelScope.launch(Dispatchers.IO) {

            var rs=myRepo.insertProduct(prod)
            Log.d("TAG", "inserProd: $rs ")
            try {}catch (e:Exception){}}

    }


}