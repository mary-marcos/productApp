package com.example.theviewmodel.UI

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.theviewmodel.R
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.theviewmodel.data.Repo.LocalDB
import com.example.theviewmodel.data.Repo.MyRepo
import com.example.theviewmodel.data.Repo.RemoteDS
import com.example.theviewmodel.databinding.ActivityAllProductBinding
import com.example.theviewmodel.model.Product
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AllProductActivity : AppCompatActivity(),clicklistener {
    lateinit var binding: ActivityAllProductBinding
    lateinit var viewModel:allproductViewmodel
    lateinit var myAdapter:AdapterAllProd
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAllProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        myAdapter = AdapterAllProd(this@AllProductActivity)

        binding.recyclerView.apply {
            adapter=myAdapter
            layoutManager = LinearLayoutManager(context)
        }
        var factory:AllProFactory=AllProFactory(MyRepo.getInstance(RemoteDS.getInstance(), LocalDB.getInstance(this)))

        viewModel = ViewModelProvider(this,factory)[allproductViewmodel::class.java]

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.productDataObserve.collect{ result ->
                    when (result) {
                        is StateManager.Success -> {

//                            val productData = result.data
//                            var items = productData
                            myAdapter.submitList(result.data)

                        }
                        is StateManager.Loading -> {
                            Toast.makeText(this@AllProductActivity,"loading",Toast.LENGTH_SHORT).show()
                        }
                        is StateManager.Failure -> {

                            val errorMessage = result.error
                            Toast.makeText(this@AllProductActivity,errorMessage,Toast.LENGTH_SHORT).show()

                        }
                    }
                }
            }
        }
//        lifecycleScope.launch {
//            viewModel.productDataObserve.collectLatest { result ->
//                when (result) {
//                    is StateManager.Success -> {
//
//                        val productData = result.data
//                       var items = productData.getAllProduct()
//                        myAdapter.submitList(items)
//
//                    }
//                    is StateManager.Loading -> {
//                       Toast.makeText(this@AllProductActivity,"loading",Toast.LENGTH_SHORT).show()
//                    }
//                    is StateManager.Failure -> {
//
//                        val errorMessage = result.error
//                        Toast.makeText(this@AllProductActivity,errorMessage,Toast.LENGTH_SHORT).show()
//
//                    }
//                }
//            }
//        }
        viewModel.getCurrentWeatherData()
//        var items : List<Product> = listOf()
//        myAdapter.submitList(items)
    }


//    private fun observeCurrentweather() {
//        viewModel.productdataopserve.observe(this,Observer{
//            var items = it.getAllProduct()
//            myAdapter.submitList(items)
//
//
//        }
//        )
//
//    }



    override fun listener(prod: Product) {
       Toast.makeText(this,"",Toast.LENGTH_SHORT).show()
        viewModel.inserProd(prod)
    }




}