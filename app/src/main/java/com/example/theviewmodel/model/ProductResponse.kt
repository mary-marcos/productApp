package com.example.theviewmodel.model

class ProductResponse {
    private var products: List<Product>? = null

    fun getAllProduct(): List<Product>? {
        return products
    }

    fun setAllProducts(products: List<Product>?) {
        this.products = products
    }
}