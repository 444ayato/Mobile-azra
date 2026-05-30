package com.example.azraapps.Home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.azraapps.AuthActivity
import com.example.azraapps.Home.pertemuan_2.SecondActivity
import com.example.azraapps.Home.pertemuan_3.ThirdActivity
import com.example.azraapps.Home.pertemuan_4.FourthActivity
import com.example.azraapps.Home.pertemuan_5.FifthActivity
import com.example.azraapps.Home.pertemuan_7.SeventhActivity
import com.example.azraapps.Home.pertemuan_9.NinthActivity
import com.example.azraapps.Home.pertemuan_10.TenthActivity // 1. TAMBAHKAN IMPORT INI
import com.example.azraapps.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

        // --- NAVIGASI KE PERTEMUAN 10 (BARU) ---
        binding.btnToTenth.setOnClickListener {
            val intent = Intent(requireContext(), TenthActivity::class.java)
            startActivity(intent)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}