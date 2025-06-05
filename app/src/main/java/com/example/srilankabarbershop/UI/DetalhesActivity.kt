package com.example.srilankabarbershop.UI

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.srilankabarbershop.R
import com.example.srilankabarbershop.databinding.ActivityAvaliacaoBinding
import com.example.srilankabarbershop.databinding.ActivityDetalhesBinding

class DetalhesActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetalhesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetalhesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val avaliacoesTV = binding.telaAvaliacaoAvaliacoesTV
        val servicosTV = binding.telaAvaliacaoServicosTV

        servicosTV.setOnClickListener{
            val intent = Intent(this, EscolhaCorteActivity::class.java)
            startActivity(intent)
        }
        avaliacoesTV.setOnClickListener {
            val intent = Intent(this, AvaliacaoActivity::class.java)
            startActivity(intent)
        }
    }
}