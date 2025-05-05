package com.example.srilankabarbershop.UI

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.srilankabarbershop.R

class DetalhesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalhes)

        val avaliacoesTV = findViewById<TextView>(R.id.tela_avaliacao_avaliacoes_TV)

        avaliacoesTV.setOnClickListener {
            val intent = Intent(this, AvaliacaoActivity::class.java)
            startActivity(intent)
        }
    }
}