package com.example.srilankabarbershop.UI

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.srilankabarbershop.R

class TelaDePagamentoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tela_de_pagamento)

        val botaoCadastrarCartao = findViewById<Button>(R.id.botaoCadastrarCartao)

        botaoCadastrarCartao.setOnClickListener {
            val intent = Intent(this, CadastrarCartaoActivity::class.java)
            startActivity(intent)
        }

        val botaoPagamento = findViewById<Button>(R.id.botaoPagar)

        botaoPagamento.setOnClickListener{
            val intent = Intent(this, TelaDeAgradecimentoActivity::class.java)
            startActivity(intent)
        }
    }
}