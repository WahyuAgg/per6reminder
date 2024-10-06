package com.example.per6reminder

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.per6reminder.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi binding
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)


        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Ambil data dari Intent
        val title = intent.getStringExtra("TITLE")
        val date = intent.getStringExtra("DATE")
        val time = intent.getStringExtra("TIME")
        val repeat = intent.getStringExtra("SET_REPEAT")

        // Tampilkan data di TextView
        binding.displayTitle.text = "$title"
        binding.displayDate.text = "$date"
        binding.displayTime.text = "$time"
        binding.displayRepeat.text = "Repeated: $repeat"

        // Tombol Add Task untuk membuat task baru
        binding.btnAddTask3.setOnClickListener {
            val intent = Intent(this@ThirdActivity, SecondActivity::class.java)
            startActivity(intent)
        }


    }
}