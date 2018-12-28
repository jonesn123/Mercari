package com.example.simhyunyong.minimercaru.view

import android.view.View
import com.example.simhyunyong.minimercaru.data.Product

interface ProductClickListener {
    fun onProductItemClick(view: View, product: Product, position: Int)
}