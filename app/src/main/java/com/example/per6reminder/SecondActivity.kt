package com.example.per6reminder

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.TimePicker
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.per6reminder.databinding.ActivitySecondBinding
import java.util.Calendar

class SecondActivity : AppCompatActivity() {

    // Deklarasi binding untuk mengakses elemen layout
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi binding
        binding = ActivitySecondBinding.inflate(layoutInflater)

        // Mengatur tampilan konten menggunakan binding.root
        setContentView(binding.root)

        //margin default
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Opsi Spinner Repeat
        val opt_repeat = resources.getStringArray(R.array.opt_repeat) // Opsi reminder

        // Set adapter untuk spinner opsi reminder
        val adapterOptRepeat = ArrayAdapter(
            this,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            opt_repeat
        )

        // Menghubungkan spinner dengan adapter
        binding.spinnerRepeat.adapter = adapterOptRepeat

        // pilih tanggal
        binding.editDate.setOnClickListener{
            showDatePickerDialog()
        }

        // pilih waktu
        binding.editTime.setOnClickListener{
            showTimePickerDialog()
        }

        // Add Task
        binding.btnAddTask2.setOnClickListener {
            AddTask()
        }


    }

    // Fungsi untuk menampilkan DatePickerDialog
    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                // Membuat String Format tanggal yang dipilih
                val formattedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                // Tampilkan tanggal di EditText
                binding.editDate.setText(formattedDate)
            },
            year, month, day
        )
        datePickerDialog.show()
    }

    // Fungsi untuk menampilkan TimePickerDialog
    private fun showTimePickerDialog() {
        // Mengambil instance Calendar dengan waktu saat ini
        val currentCalendar = Calendar.getInstance()

        // Mendapatkan jam dan menit saat ini
        val currentHour = currentCalendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = currentCalendar.get(Calendar.MINUTE)

        // Membuat TimePickerDialog untuk memilih waktu
        val timePickerDialog = TimePickerDialog(
            this, // Menggunakan konteks saat ini (Activity)
            { _: TimePicker, selectedHour: Int, selectedMinute: Int -> // Lambda untuk menangani pemilihan waktu
                // Membuat string format waktu dengan dua digit untuk jam dan menit
                val formattedTime = String.format("%02d:%02d", selectedHour, selectedMinute)

                // Mengatur hasil format waktu ke dalam EditText
                binding.editTime.setText(formattedTime)
            },
            currentHour, // Jam awal yang ditampilkan di dialog
            currentMinute, // Menit awal yang ditampilkan di dialog
            true // Menentukan format 24 jam
        )

        // Menampilkan dialog pemilih waktu kepada pengguna
        timePickerDialog.show()
    }

    //fungsi popup
    private fun AddTask(){


        // Membuat alert dialog dengan BUILDER
        val builder = AlertDialog.Builder(this)

        // Menetapkan judul dialog
        builder.setTitle("SimpliRemind")

        // Menetapkan pesan dialog

        builder.setMessage("Do you want to add this as new taks?")

        // Menetapkan tombol No
        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }

        // Menetapkan tombol Yes
        builder.setPositiveButton("Yes") { dialog, _ ->

            // Inisialisasi intent ke ThirdActivity
            val intent = Intent(this, ThirdActivity::class.java)

            // Masukkan data ke intent
            intent.putExtra("TITLE", binding.editTitle.text.toString())
            intent.putExtra("DATE", binding.editDate.text.toString())
            intent.putExtra("TIME", binding.editTime.text.toString())
            intent.putExtra("SET_REPEAT", binding.spinnerRepeat.selectedItem.toString())

            // Menjalankan intent
            startActivity(intent)

            dialog.dismiss()
        }

        // Menjalankan builder
        val dialog = builder.create()

        // Menampilkan dialog
        dialog.show()



    }

}