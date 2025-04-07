package com.example.srilankabarbershop.UI

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.srilankabarbershop.R
import com.example.srilankabarbershop.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide() //esconde a toolbar de cima

        val btnCadastrar = binding.cadastrarTV
        btnCadastrar.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }

        val btnEsquciSenha = binding.esqueciMinhaSenhaTV
        btnEsquciSenha.setOnClickListener{
            val intent = Intent(this, EsqueciSenhaActivity::class.java)
            startActivity(intent)
        }

        val btnLogin = binding.confirmBtn
        btnLogin.setOnClickListener {
            val intent = Intent(this, EscolhaCorteActivity::class.java)
            startActivity(intent)
        }


    }
}