package com.example.azraapps.pertemuan_3

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.azraapps.R
import com.example.azraapps.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.buttonSubmit.setOnClickListener {
            //Mengambil value dari inputNama dan menampilkan di Logcat
            val nama = binding.inputNama.text
            Log.e("Klik btnSubmit","Tombol berhasil di tekan. Isi dari inputNama = $nama")

            Toast.makeText(this, "Anda telah melakukan klik pada tombol Submit", Toast.LENGTH_SHORT).show()

            val intent = Intent(createPackageContext = this, cls = ThirdResultActivity::class.java)
            startActivity(intent)
        }
    }
}