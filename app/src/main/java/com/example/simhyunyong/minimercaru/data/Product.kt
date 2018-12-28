package com.example.simhyunyong.minimercaru.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.Random

@Entity(tableName = "product")
data class Product(
    @PrimaryKey val id: Int,
    val albumId: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String) {

    companion object {
        @JvmStatic
        fun getProductType(albumId: Int): String = when (albumId) {
            1 -> "MEN"
            2 -> "WOMEN"
            3 -> "BABY"
            4 -> "ELECTRIC"
            5 -> "SHOES"
            6 -> "CLOTHES"
            7 -> "CARS"
            8 -> "FURNITURE"
            9 -> "HOUSE"
            10 -> "COMPUTER"
            else -> "ETC"
        }

        @JvmStatic
        fun getRandomPrice(): Int =
            Random().nextInt(1000)
    }

}