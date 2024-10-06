package com.example.per6reminder

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.per6reminder.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi binding
        binding = ActivityMainBinding.inflate(layoutInflater)

        // Set layout dengan root dari binding
        setContentView(binding.root)

        // kode default
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        // Gunakan binding untuk mengakses elemen UI
        with(binding) {
            btnAddTask1.setOnClickListener {
                // Membuat Intent untuk memulai SecondActivity
                val intent = Intent(this@MainActivity, SecondActivity::class.java)

                // Memulai activity kedua
                startActivity(intent)
            }
        }
    }
}
