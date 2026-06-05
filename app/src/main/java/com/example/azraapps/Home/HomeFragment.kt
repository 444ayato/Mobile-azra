package com.example.azraapps.Home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.azraapps.AuthActivity
import com.example.azraapps.Home.pertemuan_2.SecondActivity
import com.example.azraapps.Home.pertemuan_3.ThirdActivity
import com.example.azraapps.Home.pertemuan_4.FourthActivity
import com.example.azraapps.Home.pertemuan_5.FifthActivity
import com.example.azraapps.Home.pertemuan_7.SeventhActivity
import com.example.azraapps.Home.pertemuan_9.NinthActivity
import com.example.azraapps.Home.pertemuan_10.TenthActivity
import com.example.azraapps.Home.photo.PhotoAdapter
import com.example.azraapps.data.api.CatFactApiClient
import com.example.azraapps.data.api.PhotoApiClient
import com.example.azraapps.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Home"
        }

        // --- Navigasi ke Pertemuan 2 ---
        binding.btnToSecond.setOnClickListener {
            val intent = Intent(requireContext(), SecondActivity::class.java)
            startActivity(intent)
        }

        // --- Navigasi ke Pertemuan 3 ---
        binding.btnToThird.setOnClickListener {
            val intent = Intent(requireContext(), ThirdActivity::class.java)
            startActivity(intent)
        }

        // --- Navigasi ke Pertemuan 4 ---
        binding.btnToFourth.setOnClickListener {
            val intent = Intent(requireContext(), FourthActivity::class.java)
            intent.putExtra("nama", "Politeknik Caltex Riau")
            intent.putExtra("asal", "Rumbai")
            intent.putExtra("usia", 25)
            startActivity(intent)
        }

        // --- Navigasi ke Pertemuan 5 ---
        binding.btnToFifth.setOnClickListener {
            val intent = Intent(requireContext(), FifthActivity::class.java)
            startActivity(intent)
        }

        // --- Navigasi ke Pertemuan 7 ---
        binding.btnToSeventh.setOnClickListener {
            val intent = Intent(requireContext(), SeventhActivity::class.java)
            startActivity(intent)
        }

        // --- Navigasi ke Pertemuan 9 ---
        binding.btnToNinth.setOnClickListener {
            val intent = Intent(requireContext(), NinthActivity::class.java)
            startActivity(intent)
        }

        // --- Navigasi ke Pertemuan 10 ---
        binding.btnToTenth.setOnClickListener {
            val intent = Intent(requireContext(), TenthActivity::class.java)
            startActivity(intent)
        }

        // --- Memuat Fakta Kucing & Foto Pertama Kali ---
        loadCatFact()
        loadPhoto()

        // --- Aksi Klik Tombol Refresh ---
        binding.btnRefresh.setOnClickListener {
            loadCatFact()
        }

        // --- Aksi Tombol Logout ---
        binding.btnLogout.setOnClickListener {
            androidx.appcompat.app.AlertDialog.Builder(requireContext())
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin logout?")
                .setPositiveButton("Ya") { dialog, _ ->
                    val sharedPref = requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)
                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()

                    dialog.dismiss()

                    val intent = Intent(requireContext(), AuthActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                }
                .setNegativeButton("Tidak", null)
                .show()
        }
    }

    private fun loadCatFact() {
        binding.tvCatFact.text = "Loading cat fact..."
        lifecycleScope.launch {
            try {
                val response = CatFactApiClient.apiService.getCatFact()
                binding.tvCatFact.text = "\"${response.fact}\""
            } catch (e: Exception) {
                binding.tvCatFact.text = "Gagal mengambil fakta kucing."
            }
        }
    }

    // Fungsi Mengambil data foto dari Picsum API dan memasangnya ke RecyclerView
    private fun loadPhoto() {
        lifecycleScope.launch {
            try {
                val photos = PhotoApiClient.apiService.getPhotos()
                val adapter = PhotoAdapter(photos)
                binding.rvGallery.adapter = adapter

                /** * SILAHKAN PILIH SALAH SATU MODE DI BAWAH INI:
                 * Hapus tanda // pada mode yang ingin diaktifkan,
                 * dan tambahkan // pada mode yang ingin dimatikan.
                 */

                /** [Mode 1] List Tampil Vertical */
                // binding.rvGallery.layoutManager = LinearLayoutManager(requireContext())

                /** [Mode 2] List Tampil Horizontal */
                // binding.rvGallery.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

                /** [Mode 3] List Tampil Grid (Sesuai Hasil Akhir Gambar 2 Kolom) */
                binding.rvGallery.layoutManager = GridLayoutManager(requireContext(), 2)

            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Gagal memuat gambar", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}