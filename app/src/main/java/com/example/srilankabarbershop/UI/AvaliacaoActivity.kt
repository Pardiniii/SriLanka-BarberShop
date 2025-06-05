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

class AvaliacaoActivity : AppCompatActivity() {

    lateinit var binding: ActivityAvaliacaoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAvaliacaoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detalhesTV = binding.telaAvaliacaoDetalhes
        val servicosTV = binding.telaAvaliacaoServicosTV

        servicosTV.setOnClickListener{
            val intent = Intent(this, EscolhaCorteActivity::class.java)
            startActivity(intent)
        }

        detalhesTV.setOnClickListener {
            val intent = Intent(this, DetalhesActivity::class.java)
            startActivity(intent)
        }
    }
}