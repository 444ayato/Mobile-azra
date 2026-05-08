package com.example.azraapps.Home.pertemuan_3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.azraapps.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Inisialisasi View Binding
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2. Mengaktifkan Edge-to-Edge (Layar Penuh)
        enableEdgeToEdge()

        // 3. Menangani Padding System Bars (Status Bar & Navigasi)
        // Gunakan binding.root untuk referensi layout utama agar lebih aman
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 4. Logika Tombol Submit
        binding.buttonSubmit.setOnClickListener {
            // .toString() sangat penting agar data bisa dikirim via Intent
            val nama = binding.inputNama.text.toString()

            if (nama.isEmpty()) {
                binding.inputNama.error = "Nama tidak boleh kosong"
            } else {
                Log.e("Klik btnSubmit", "Tombol berhasil ditekan. Isi nama = $nama")
                Toast.makeText(this, "Halo $nama, data dikirim!", Toast.LENGTH_SHORT).show()

                // SINTAKS INTENT YANG BENAR:
                // Intent(Context, KelasTujuan::class.java)
                val intent = Intent(this, ThirdResultActivity::class.java)

                // Menyisipkan data untuk dibawa ke activity tujuan
                intent.putExtra("nama", nama)

                startActivity(intent)
            }
        }
    }
}