package com.example.simhyunyong.minimercaru.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.view.ViewPager
import com.example.simhyunyong.minimercaru.R
import com.example.simhyunyong.minimercaru.data.Product
import com.example.simhyunyong.minimercaru.viewmodel.MainViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val mainViewModel: MainViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = getString(R.string.title)

        tabs.setupWithViewPager(viewpager)
        fab.setOnClickListener { view ->
            Snackbar.make(view, getString(R.string.snackbar_message), Snackbar.LENGTH_LONG).show()
        }

        viewpager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                tabs.getTabAt(position)?.select()
            }
        })

        mainViewModel.deleteProducts()
        // fetch products from viewModel
        mainViewModel.fetchProducts()

        mainViewModel.getCategories().observe(this, Observer { categories ->
            categories?.also {
                setupViewPager(viewpager, it)
            }
        })
    }

    private fun setupViewPager(viewPager: ViewPager, categories: List<Int>) {
        val adapter = TabFragmentPagerAdapter(supportFragmentManager).apply {
            addFragment(ProductListFragment(0), "all")
            categories.forEach { categoryId ->
                val categoryName = Product.getProductType(categoryId)
                addFragment(ProductListFragment(categoryId), categoryName)
            }

        }
        viewPager.adapter = adapter
    }
}
