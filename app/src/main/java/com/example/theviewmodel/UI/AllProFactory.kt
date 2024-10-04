package com.example.theviewmodel.UI

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.theviewmodel.data.Repo.MyRepo

class AllProFactory(var repo: MyRepo) :
   ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(allproductViewmodel::class.java)) {
            return allproductViewmodel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
   }


class FavProdFactory(var repo: MyRepo) :
    ViewModelProvider.Factory{


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavViewModel::class.java)) {
            return FavViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}