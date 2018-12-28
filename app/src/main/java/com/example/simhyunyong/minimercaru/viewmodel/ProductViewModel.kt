package com.example.simhyunyong.minimercaru.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.simhyunyong.minimercaru.data.Product
import com.example.simhyunyong.minimercaru.db.dao.ProductDao
import javax.inject.Inject

class ProductViewModel @Inject constructor(private val productDao: ProductDao, private val category: Int) :
    ViewModel() {
    val pagedListLiveData: LiveData<PagedList<Product>> by lazy {
        val dataSourceFactory = when (category) {
            0 -> productDao.getAllProducts()
            else -> productDao.getProductsOfAlbumId(category)
        }
        val config = PagedList.Config.Builder()
            .setPageSize(3)
            .setInitialLoadSizeHint(5)
            .build()
        LivePagedListBuilder(dataSourceFactory, config).build()
    }

    companion object {
        @JvmStatic
        @BindingAdapter("android:src")
        fun setImageView(view: ImageView, url: String?) {
            url?.also {
                Glide.with(view.context).load(it).into(view)
            }
        }

    }
}