package com.example.srilankabarbershop.UI

import android.content.Intent
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

        supportActionBar?.hide()
        enableEdgeToEdge()
        setContentView(binding.root)
        supportActionBar?.hide()

        val calendario = binding.calendario
        val corteEscolhido = binding.corteAtualTV
        val precoDoCorte = binding.valorCorteTV
        val botaoConfirmar = binding.confirmaCorteBtn
        val dataCorte = binding.dataCorteTV

        intent?.let {
            val corteRecebido = intent.getStringExtra("CHAVE")
            val precoRecebido = intent.getStringExtra("CHAVE2")
            corteEscolhido.text = corteRecebido
            precoDoCorte.text = precoRecebido
        }

        calendario.setOnDateChangeListener { _, year, month, day ->
            val dataSelecionada = ("%02d".format(day) + "/"
                    + "%02d".format((month + 1)) + "/"
                    + year)
            dataCorte.text = dataSelecionada
        }

        botaoConfirmar.setOnClickListener {
            val intent = Intent(this, TelaDePagamentoActivity::class.java)
            intent.putExtra("CHAVE_CORTE", corteEscolhido.text)
            intent.putExtra("CHAVE_PRECO", precoDoCorte.text)
            intent.putExtra("CHAVE_DATA", dataCorte.text)
            startActivity(intent)
        }

    }
}