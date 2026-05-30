package com.example.azraapps.Home.pertemuan_10

/**
 * Data Class Model untuk menampung data produk secara dinamis.
 * Kelas ini akan digunakan oleh RecyclerView Adapter untuk menampilkan list produk.
 */
data class ProductModel(
    val name: String,     // Menampung nama produk
    val price: String,    // Menampung harga produk
    val imageUrl: String  // Menampung URL gambar produk (yang akan di-load menggunakan Glide)
)