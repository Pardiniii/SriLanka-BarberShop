package com.example.srilankabarbershop.UI

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.srilankabarbershop.R
import com.example.srilankabarbershop.databinding.ActivityMarcarCorteBinding

class MarcarCorteActivity : AppCompatActivity() {

    lateinit var binding: ActivityMarcarCorteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMarcarCorteBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        supportActionBar?.hide()

        val calendario = binding.calendario
        val corteEscolhido = binding.corteAtualTV

        intent?.let {
            val corteRecebido = intent.getStringExtra("CHAVE")
            corteEscolhido.text = corteRecebido
        }


        calendario.setOnDateChangeListener { _, year, month, day ->
            val dataSelecionada = ("%02d".format(day) + "/"
                    + "%02d".format((month + 1)) + "/"
                    + year)
            val dataCorte = binding.dataCorteTV
            dataCorte.text = dataSelecionada
        }


    }
}