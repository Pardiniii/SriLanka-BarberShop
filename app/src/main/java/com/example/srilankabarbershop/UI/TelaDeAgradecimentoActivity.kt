package com.example.srilankabarbershop.UI

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.srilankabarbershop.R
import com.example.srilankabarbershop.databinding.ActivityDetalhesBinding
import com.example.srilankabarbershop.databinding.ActivityTelaDeAgradecimentoBinding

class TelaDeAgradecimentoActivity : AppCompatActivity() {

    lateinit var binding: ActivityTelaDeAgradecimentoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTelaDeAgradecimentoBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        supportActionBar?.hide()

        val botaoVoltar = binding.botaoVoltar

        botaoVoltar.setOnClickListener {
            val intent = Intent(this, EscolhaCorteActivity::class.java)
            startActivity(intent)
        }

    }
}