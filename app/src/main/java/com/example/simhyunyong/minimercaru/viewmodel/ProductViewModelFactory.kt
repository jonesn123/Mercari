package com.example.simhyunyong.minimercaru.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.simhyunyong.minimercaru.db.dao.ProductDao

class ProductViewModelFactory(private val mProductDao: ProductDao, private val categoryId: Int) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            return ProductViewModel(mProductDao, categoryId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
