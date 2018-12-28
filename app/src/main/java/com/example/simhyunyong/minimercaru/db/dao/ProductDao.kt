package com.example.simhyunyong.minimercaru.db.dao

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.arch.paging.PagedList
import android.arch.persistence.room.*
import com.example.simhyunyong.minimercaru.data.Product

@Dao
interface ProductDao {

    @Query("SELECT * FROM product")
    fun getAllProducts(): DataSource.Factory<Int, Product>

    @Query("SELECT * FROM product WHERE `albumId` = :albumId")
    fun getProductsOfAlbumId(albumId: Int): DataSource.Factory<Int, Product>

    @Query("SELECT DISTINCT albumId FROM product LIMIT 11")
    fun getCategories(): LiveData<List<Int>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(user: List<Product>)

    @Query("DELETE FROM product")
    fun deleteAll()

}