package com.example.azraapps.Message

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.azraapps.Home.Message.tutorial.TutorialMessageActivity
import com.example.azraapps.R
import com.example.azraapps.databinding.FragmentMessageBinding

class MessageFragment : Fragment() {

    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!

    // 10 data list chat bawaan kamu tetap dipertahankan di sini
    private val messageList = listOf(
        MessageModel("Alya", "Halo! Apa kabar?", "https://api.dicebear.com/7.x/avataaars/png?seed=Alya"),
        MessageModel("Budi", "Sudah makan?", "https://api.dicebear.com/7.x/avataaars/png?seed=Budi"),
        MessageModel("Citra", "Jangan lupa tugasnya ya!", "https://api.dicebear.com/7.x/avataaars/png?seed=Citra"),
        MessageModel("Dika", "Besok kita rapat jam 9", "https://api.dicebear.com/7.x/avataaars/png?seed=Dika"),
        MessageModel("Eka", "Nice job kemarin!", "https://api.dicebear.com/7.x/avataaars/png?seed=Eka"),
        MessageModel("Fajar", "Lagi ngapain?", "https://api.dicebear.com/7.x/avataaars/png?seed=Fajar"),
        MessageModel("Gita", "Boleh minta tolong?", "https://api.dicebear.com/7.x/avataaars/png?seed=Gita"),
        MessageModel("Hana", "Lihat email ya", "https://api.dicebear.com/7.x/avataaars/png?seed=Hana"),
        MessageModel("Irfan", "Oke noted", "https://api.dicebear.com/7.x/avataaars/png?seed=Irfan"),
        MessageModel("Joko", "Sampai jumpa besok", "https://api.dicebear.com/7.x/avataaars/png?seed=Joko")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup Toolbar dan Judul Halaman
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(binding.toolbar)
        activity.supportActionBar?.apply {
            title = "Message"
        }

        // PENTING: Mengaktifkan fitur option menu agar tombol menu ikon Info bisa muncul di toolbar
        setHasOptionsMenu(true)

        // Pasang data list ke adapter bawaan kamu (ListView / RecyclerView)
        val adapter = MessageAdapter(requireContext(), messageList)
        binding.listMessageItems.adapter = adapter
    }

    // Meng-inflate menu XML (message_toolbar_menu.xml) ke dalam toolbar
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.message_toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    // Menangani aksi klik pada icon info untuk pindah ke halaman TutorialMessageActivity
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_tutorial -> {
                val intent = Intent(requireContext(), TutorialMessageActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}