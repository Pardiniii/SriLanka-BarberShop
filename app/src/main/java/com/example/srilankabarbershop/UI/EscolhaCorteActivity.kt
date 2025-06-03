package com.example.srilankabarbershop.UI

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.srilankabarbershop.R
import com.example.srilankabarbershop.databinding.ActivityEscolhaCorteBinding
import com.example.srilankabarbershop.databinding.ActivityLoginBinding


class EscolhaCorteActivity : AppCompatActivity() {


    lateinit var binding: ActivityEscolhaCorteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEscolhaCorteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val reservaJaca = binding.reservarJacaBTN
        reservaJaca.setOnClickListener {
            val intent = Intent(this, MarcarCorteActivity::class.java)
            intent.putExtra("CHAVE", "Corte do Jaca")
            intent.putExtra("CHAVE2", "R$ 25,00")

            startActivity(intent)
        }

        val reservaZeca = binding.reservarZecaBTN
        reservaZeca.setOnClickListener {
            val intent = Intent(this, MarcarCorteActivity::class.java)
            intent.putExtra("CHAVE", "Corte do Zeca")
            intent.putExtra("CHAVE2", "R$ 30,00")
            startActivity(intent)
        }

        val reservaCalvao = binding.reservarCalvaoBTN
        reservaCalvao.setOnClickListener {
            val intent = Intent(this, MarcarCorteActivity::class.java)
            intent.putExtra("CHAVE", "Calvão de cria")
            intent.putExtra("CHAVE2", "R$ 44,99")
            startActivity(intent)
        }

        val reservaNevou = binding.reservarNevouBTN
        reservaNevou.setOnClickListener {
            val intent = Intent(this, MarcarCorteActivity::class.java)
            intent.putExtra("CHAVE", "Nevou")
            intent.putExtra("CHAVE2", "R$ 100,00")
            startActivity(intent)
        }

        binding.avaliacoesTV.setOnClickListener {
            val intent = Intent(this, AvaliacaoActivity::class.java)
            startActivity(intent)
        }

        binding.detalhesTV.setOnClickListener {
            val intent = Intent(this, DetalhesActivity::class.java)
            startActivity(intent)
        }

    }
}