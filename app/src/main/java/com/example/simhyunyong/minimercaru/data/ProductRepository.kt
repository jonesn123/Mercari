package com.example.simhyunyong.minimercaru.data

import android.util.Log
import com.example.simhyunyong.minimercaru.db.dao.ProductDao
import retrofit2.Call
import retrofit2.Response

class ProductRepository(private val productDao: ProductDao,
                        private val webService: WebService) {

    fun getProductsFromWebServer() {
        val thread = Thread(Runnable {
            try {
                webService.getProducts().enqueue(object : retrofit2.Callback<List<Product>> {
                    override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                    }

                    override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                        val products = response.body()
                        products?.also {
                            productDao.insertAll(it)
                        }
                    }
                })
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
        thread.start()
    }

    fun localProductDeleteAll() {
        productDao.deleteAll()
    }

    fun getCategories() = productDao.getCategories()
}