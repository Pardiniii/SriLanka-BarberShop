package com.example.srilankabarbershop.UI

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.srilankabarbershop.R

class AvaliacaoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_avaliacao)
        val detalhesTV = findViewById<TextView>(R.id.tela_avaliacao_detalhes)

        detalhesTV.setOnClickListener {
            val intent = Intent(this, DetalhesActivity::class.java)
            startActivity(intent)
        }
    }
}