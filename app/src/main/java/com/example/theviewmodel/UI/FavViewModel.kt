package com.example.theviewmodel.UI

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theviewmodel.data.Repo.MyRepo
import com.example.theviewmodel.model.Product
import com.example.theviewmodel.model.ProductResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavViewModel(var myRepo: MyRepo): ViewModel()
{
    private var _productdataopserve= MutableLiveData<List<Product>>()
    var productdataopserve: LiveData<List<Product>> =_productdataopserve


    fun getLocalProd(){
        try {
        viewModelScope.launch(Dispatchers.IO) {

            var rs=myRepo.getLocalProduct()
            Log.d("TAG", "inserProd: $rs ")



               // flowOn()
                rs. collect{ value ->
                    println("Collected $value")
                    _productdataopserve.postValue(value)
                }
               // _productdataopserve.postValue(rs)

        }
            }catch (e:Exception){}

    }
}