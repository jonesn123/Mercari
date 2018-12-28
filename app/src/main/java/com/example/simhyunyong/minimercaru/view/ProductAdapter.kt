package com.example.simhyunyong.minimercaru.view

import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.simhyunyong.minimercaru.R
import com.example.simhyunyong.minimercaru.data.Product
import com.example.simhyunyong.minimercaru.databinding.ItemProductBinding

class ProductAdapter(private val listener: ProductClickListener?): PagedListAdapter<Product, ProductAdapter.ProductViewHolder>(DIFF_CALLBACK) {
    private lateinit var recyclerView: RecyclerView

    inner class ProductViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding: ItemProductBinding = DataBindingUtil.bind(itemView)!!

        fun bindData(product: Product, position: Int) {
            binding.product = product
            itemView.setOnClickListener {
                listener?.onProductItemClick(itemView, product, position)
            }
        }

        fun clear() {
            binding.product = null
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
        ProductViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false))


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        if (product != null) {
            holder.bindData(product, position)
        } else {
            holder.clear()
        }
    }


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean =
                oldItem == newItem

        }
    }
}