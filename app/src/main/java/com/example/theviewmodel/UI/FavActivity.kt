package com.example.theviewmodel.UI

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.theviewmodel.R
import com.example.theviewmodel.data.Repo.LocalDB
import com.example.theviewmodel.data.Repo.MyRepo
import com.example.theviewmodel.data.Repo.RemoteDS
import com.example.theviewmodel.databinding.ActivityAllProductBinding
import com.example.theviewmodel.databinding.ActivityFavBinding
import com.example.theviewmodel.model.Product
import kotlinx.coroutines.launch

class FavActivity : AppCompatActivity(),clicklistener {
    lateinit var binding: ActivityFavBinding
    lateinit var viewModel:FavViewModel
    lateinit var myAdapter:AdapterAllProd
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityFavBinding.inflate(layoutInflater)
        setContentView(binding.root)
        myAdapter = AdapterAllProd(this@FavActivity)

        binding.recyclerView.apply {
            adapter=myAdapter
            layoutManager = LinearLayoutManager(context)
        }

        var factory:FavProdFactory=FavProdFactory(MyRepo.getInstance(RemoteDS.getInstance(), LocalDB.getInstance(this)))

        viewModel = ViewModelProvider(this,factory)[FavViewModel::class.java]

        lifecycleScope.launch {  observeproduct() }
        viewModel.getLocalProd()
    }

    private fun observeproduct() {
        viewModel.productdataopserve.observe(this,Observer{
            var items = it
            myAdapter.submitList(items)


        }
        )

    }

    override fun listener(prod: Product) {
        Toast.makeText(this,"delete later",Toast.LENGTH_SHORT ).show()
    }
}