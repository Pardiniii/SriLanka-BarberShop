package com.example.srilankabarbershop.UI

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.srilankabarbershop.R
import com.example.srilankabarbershop.databinding.ActivityTelaDePagamentoBinding

class TelaDePagamentoActivity : AppCompatActivity() {

    lateinit var binding: ActivityTelaDePagamentoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTelaDePagamentoBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        val corteAtual = binding.corteEscolhidoOfc
        val valorCorte = binding.valorTv
        val dataDaReserva = binding.dataTv

        val botaoCadastrarCartao = binding.botaoCadastrarCartao
        intent?.let {
            val corteConfirmado = intent.getStringExtra("CHAVE_CORTE")
            val precoCorte = intent.getStringExtra("CHAVE_PRECO")
            val dataCorte = intent.getStringExtra("CHAVE_DATA")
            corteAtual.text = corteConfirmado
            valorCorte.text = precoCorte
            dataDaReserva.text = dataCorte
        }

        botaoCadastrarCartao.setOnClickListener {
            val intent = Intent(this, CadastrarCartaoActivity::class.java)
            startActivity(intent)
        }

        val botaoPagamento = binding.botaoPagar

        botaoPagamento.setOnClickListener{
            val intent = Intent(this, TelaDeAgradecimentoActivity::class.java)
            startActivity(intent)
        }
    }
}