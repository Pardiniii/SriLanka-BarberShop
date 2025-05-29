package com.example.srilankabarbershop.UI

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.srilankabarbershop.R
import com.example.srilankabarbershop.databinding.ActivityEsqueciSenhaBinding

class EsqueciSenhaActivity : AppCompatActivity() {

    lateinit var binding: ActivityEsqueciSenhaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        supportActionBar?.hide()

        binding = ActivityEsqueciSenhaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val enviado = binding.enviadoParaEmailTV
        val btnEnviarCod = binding.confirmBtn
        btnEnviarCod.setOnClickListener{
            enviado.visibility = View.VISIBLE
        }


    }
}