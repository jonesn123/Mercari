package com.example.simhyunyong.minimercaru.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.simhyunyong.minimercaru.data.ProductRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(private val productRepository: ProductRepository) : ViewModel() {

    fun fetchProducts() = productRepository.getProductsFromWebServer()

    fun deleteProducts() {
        productRepository.localProductDeleteAll()
    }

    fun getCategories()  = productRepository.getCategories()
}