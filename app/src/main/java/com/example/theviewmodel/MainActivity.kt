package com.example.theviewmodel

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.theviewmodel.UI.AllProductActivity
import com.example.theviewmodel.UI.FavActivity
import com.example.theviewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //  setContentView(R.layout.activity_main)
        binding.toallProd.setOnClickListener {
            val intent = Intent(this, AllProductActivity::class.java)
            startActivity(intent)
        }
        binding.tofavProd.setOnClickListener {  val intent = Intent(this, FavActivity::class.java)
            startActivity(intent) }
    }
}