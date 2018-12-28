package com.example.simhyunyong.minimercaru.view

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.simhyunyong.minimercaru.R
import com.example.simhyunyong.minimercaru.data.Product
import com.example.simhyunyong.minimercaru.databinding.FragmentListBinding
import com.example.simhyunyong.minimercaru.db.dao.ProductDao
import com.example.simhyunyong.minimercaru.viewmodel.ProductViewModel
import com.example.simhyunyong.minimercaru.viewmodel.ProductViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

@SuppressLint("ValidFragment")
open class ProductListFragment(private val categoryId: Int): DaggerFragment() {
    lateinit var binding: FragmentListBinding
    private lateinit var productAdapter: ProductAdapter

    @Inject
    lateinit var productDao: ProductDao

    private val productViewModel: ProductViewModel by lazy {
        ViewModelProviders.of(this, ProductViewModelFactory(productDao, categoryId)).get(ProductViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_list, null, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() {
        recycler_view.layoutManager = GridLayoutManager(context, 3)
        productAdapter = ProductAdapter(object: ProductClickListener {
            override fun onProductItemClick(view: View, product: Product, position: Int) {
                Toast.makeText(view.context, "${product.id} product is sold out!!", Snackbar.LENGTH_LONG).show()
            }
        })

        recycler_view.adapter = productAdapter
        productViewModel.pagedListLiveData.observe(this, Observer { products ->
            products?.also {
                productAdapter.submitList(it)
            }
        })
    }
}