package com.example.azraapps.Home.pertemuan_3

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.azraapps.databinding.ActivityThirdBinding
import com.example.azraapps.utils.PermissionHelper
import com.example.azraapps.utils.ReminderHelper
import java.util.Calendar

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding

    // Launcher untuk menangani pop-up konfirmasi izin notifikasi (Android 13+)
    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(this, "Notifikasi diizinkan", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Notifikasi ditolak", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Inisialisasi View Binding
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2. Mengaktifkan Edge-to-Edge (Layar Penuh)
        enableEdgeToEdge()

        // 3. Menangani Padding System Bars (Status Bar & Navigasi)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 4. Request Izin Notifikasi Saat Halaman Diakses (Khusus Android 13 Ke Atas)
        if (PermissionHelper.isNotificationPermissionRequired()) {
            val permission = Manifest.permission.POST_NOTIFICATIONS
            if (!PermissionHelper.hasPermission(this, permission)) {
                PermissionHelper.requestPermission(
                    notificationPermissionLauncher,
                    permission
                )
            }
        }

        // 5. Logika Tombol Kirim / Submit (Memicu Reminder Jangka Waktu 1 Menit)
        binding.buttonSubmit.setOnClickListener {
            val nama = binding.inputNama.text.toString().trim()

            if (nama.isEmpty()) {
                binding.inputNama.error = "Nama tidak boleh kosong"
            } else {
                Log.e("Klik btnSubmit", "Tombol berhasil ditekan. Isi nama = $nama")

            }

            // Mengambil waktu saat ini dan menambahkan durasi 1 menit ke depan
            val calendar = Calendar.getInstance().apply {
                add(Calendar.MINUTE, 1)
            }

            // Memanggil ReminderHelper untuk menjadwalkan alarm background
            ReminderHelper.setReminder(
                context = this,
                hour = calendar.get(Calendar.HOUR_OF_DAY),
                minute = calendar.get(Calendar.MINUTE),
                title = "Reminder 1 Menit",
                message = "Halo $nama, reminder ini muncul 1 menit setelah tombol ditekan",
                targetActivity = ThirdResultActivity::class.java
            )

            // Memberi informasi transisi yang jelas kepada pengguna
            Toast.makeText(
                this,
                "Silahkan tunggu 1 Menit untuk menerima Notifikasi...",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
