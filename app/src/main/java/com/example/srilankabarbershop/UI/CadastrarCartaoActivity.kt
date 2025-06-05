package com.example.srilankabarbershop.UI

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.srilankabarbershop.R
import com.example.srilankabarbershop.databinding.ActivityCadastrarCartaoBinding

class CadastrarCartaoActivity : AppCompatActivity() {

    lateinit var binding: ActivityCadastrarCartaoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCadastrarCartaoBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        supportActionBar?.hide()
        setContentView(binding.root)

        val botaoCadastrar = binding.cadastrar

        botaoCadastrar.setOnClickListener {
//            val intent = Intent(this, TelaDePagamentoActivity::class.java)
//            startActivity(intent)
            finish()
        }

    }
}