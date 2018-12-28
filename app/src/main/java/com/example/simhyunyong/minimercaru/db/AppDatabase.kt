package com.example.simhyunyong.minimercaru.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.simhyunyong.minimercaru.data.Product
import com.example.simhyunyong.minimercaru.db.dao.ProductDao

@Database(entities = arrayOf(Product::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object {

        private const val DATABASE_NAME = "mercaru-db"
        private var INSTANCE: AppDatabase? = null

        fun getDataBase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, DATABASE_NAME)
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}