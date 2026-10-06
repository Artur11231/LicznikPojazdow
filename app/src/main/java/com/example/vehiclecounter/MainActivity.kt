package com.example.vehiclecounter

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.vehiclecounter.databinding.ActivityMainBinding
import com.google.gson.Gson
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var carCount = 0
    private var truckCount = 0
    private var busCount = 0

    private val historyList = mutableListOf<String>()

    private val gson = Gson()
    private val historyFileName = "history.json"

    data class ClickHistory(
        val history: List<String>,
        val carCount: Int,
        val truckCount: Int,
        val busCount: Int
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadHistoryFromFile()
        updateUI()
        setupListeners()
    }

    private fun setupListeners() {

        binding.settingsToggle.setOnClickListener {
            val visible = binding.settingsSection.visibility == View.VISIBLE

            binding.settingsSection.visibility =
                if (visible) View.GONE else View.VISIBLE

            binding.settingsToggle.text =
                if (visible) "Ustawienia" else "Ukryj ustawienia"
        }

        binding.carButton.setOnClickListener {
            updateCount("Samochód osobowy")
        }

        binding.truckButton.setOnClickListener {
            updateCount("Ciężarówka")
        }

        binding.busButton.setOnClickListener {
            updateCount("Autobus")
        }

        binding.resetButton.setOnClickListener {
            carCount = 0
            truckCount = 0
            busCount = 0

            saveHistoryToFile()
            updateUI()
        }

        binding.historyButton.setOnClickListener {
            val message = if (historyList.isEmpty()) {
                "Brak zapisanych kliknięć."
            } else {
                historyList.joinToString("\n")
            }

            AlertDialog.Builder(this)
                .setTitle("Historia kliknięć")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show()
        }

        binding.switchTheme.setOnCheckedChangeListener { _, isChecked ->
            AppCompatDelegate.setDefaultNightMode(
                if (isChecked) {
                    AppCompatDelegate.MODE_NIGHT_YES
                } else {
                    AppCompatDelegate.MODE_NIGHT_NO
                }
            )
        }

        binding.setBackgroundColor.setOnClickListener {
            showColorPicker { color ->
                binding.root.setBackgroundColor(color)
            }
        }

        binding.setButtonColor.setOnClickListener {
            showColorPicker { color ->
                listOf(
                    binding.carButton,
                    binding.truckButton,
                    binding.busButton,
                    binding.resetButton,
                    binding.historyButton,
                    binding.settingsToggle,
                    binding.setBackgroundColor,
                    binding.setButtonColor
                ).forEach { button ->
                    button.setBackgroundColor(color)
                }
            }
        }
    }

    private fun updateCount(vehicle: String) {

        val timestamp = SimpleDateFormat(
            "HH:mm:ss",
            Locale.getDefault()
        ).format(Date())

        historyList.add("[$timestamp] Kliknięto: $vehicle")

        when (vehicle) {
            "Samochód osobowy" -> carCount++
            "Ciężarówka" -> truckCount++
            "Autobus" -> busCount++
        }

        saveHistoryToFile()
        updateUI()
    }

    private fun updateUI() {
        binding.carCount.text = "Samochód osobowy: $carCount"
        binding.truckCount.text = "Ciężarówka: $truckCount"
        binding.busCount.text = "Autobus: $busCount"
    }

    private fun saveHistoryToFile() {
        val file = File(filesDir, historyFileName)

        val data = ClickHistory(
            history = historyList,
            carCount = carCount,
            truckCount = truckCount,
            busCount = busCount
        )

        file.writeText(gson.toJson(data))
    }

    private fun loadHistoryFromFile() {
        val file = File(filesDir, historyFileName)

        if (!file.exists()) return

        try {
            val json = file.readText()
            val data = gson.fromJson(json, ClickHistory::class.java)

            if (data != null) {
                historyList.clear()
                historyList.addAll(data.history)

                carCount = data.carCount
                truckCount = data.truckCount
                busCount = data.busCount
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun showColorPicker(onColorChosen: (Int) -> Unit) {

        val dialogView = LayoutInflater.from(this)
            .inflate(R.layout.dialog_color_picker, null)

        val redSeek = dialogView.findViewById<SeekBar>(R.id.seekRed)
        val greenSeek = dialogView.findViewById<SeekBar>(R.id.seekGreen)
        val blueSeek = dialogView.findViewById<SeekBar>(R.id.seekBlue)
        val colorPreview =
            dialogView.findViewById<TextView>(R.id.colorPreview)

        val listener = object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(
                seekBar: SeekBar?,
                progress: Int,
                fromUser: Boolean
            ) {
                val color = Color.rgb(
                    redSeek.progress,
                    greenSeek.progress,
                    blueSeek.progress
                )

                colorPreview.setBackgroundColor(color)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) = Unit

            override fun onStopTrackingTouch(seekBar: SeekBar?) = Unit
        }

        redSeek.setOnSeekBarChangeListener(listener)
        greenSeek.setOnSeekBarChangeListener(listener)
        blueSeek.setOnSeekBarChangeListener(listener)

        AlertDialog.Builder(this)
            .setTitle("Wybierz kolor")
            .setView(dialogView)
            .setPositiveButton("OK") { _, _ ->
                val chosenColor = Color.rgb(
                    redSeek.progress,
                    greenSeek.progress,
                    blueSeek.progress
                )

                onColorChosen(chosenColor)
            }
            .setNegativeButton("Anuluj", null)
            .show()
    }
}
