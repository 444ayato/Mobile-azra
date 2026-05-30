package com.example.azraapps.Home.pertemuan_10

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.azraapps.databinding.ItemProductBinding

class ProductAdapter(
    private val productList: List<ProductModel>,
    private val onItemClick: (ProductModel) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    // ViewHolder untuk memegang referensi view dari item_product.xml menggunakan View Binding
    inner class ProductViewHolder(val binding: ItemProductBinding)
        : RecyclerView.ViewHolder(binding.root)

    // Membuat instance ViewHolder baru dan menghubungkannya dengan layout item_product
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ProductViewHolder(binding)
    }

    // Mengikat data dari list ke komponen UI (TextView, ImageView, dll) pada tiap baris item
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = productList[position]
        with(holder.binding) {
            tvProductName.text = item.name
            tvProductPrice.text = item.price

            // Memuat gambar dari URL internet secara dinamis menggunakan library Glide
            Glide.with(holder.itemView.context)
                .load(item.imageUrl)
                .into(imgProduct)

            // Mengatur aksi klik pada item kontainer utama (root)
            root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    // Mengembalikan jumlah total data yang ada di dalam list
    override fun getItemCount(): Int = productList.size
}