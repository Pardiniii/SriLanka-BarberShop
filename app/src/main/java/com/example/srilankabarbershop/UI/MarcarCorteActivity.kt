package com.example.srilankabarbershop.UI

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.srilankabarbershop.api.RetrofitClient
import com.example.srilankabarbershop.databinding.ActivityMarcarCorteBinding
import com.example.srilankabarbershop.model.AgendamentoRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MarcarCorteActivity : AppCompatActivity() {

    lateinit var binding: ActivityMarcarCorteBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMarcarCorteBinding.inflate(layoutInflater)

        supportActionBar?.hide()
        enableEdgeToEdge()
        setContentView(binding.root)

        val calendario = binding.calendario
        val corteEscolhido = binding.corteAtualTV
        val precoDoCorte = binding.valorCorteTV
        val botaoConfirmar = binding.confirmaCorteBtn
        val dataCorte = binding.dataCorteTV

        intent?.let {
            val corteRecebido = intent.getStringExtra("CHAVE")
            val precoRecebido = intent.getStringExtra("CHAVE2")
            corteEscolhido.text = corteRecebido
            precoDoCorte.text = precoRecebido
        }

        calendario.setOnDateChangeListener { _, year, month, day ->
            // Crie um LocalDate com o ano, mês e dia selecionados
            val localDate = LocalDate.of(year, month + 1, day)  // mês começa em 0, por isso +1

            // Formate para exibição na tela no formato "dd/MM/yyyy"
            val formatterExibicao = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            val dataExibicao = localDate.format(formatterExibicao)
            dataCorte.text = dataExibicao // Exibe no formato "20/05/2025"

            // Converta para ISO-8601 com hora fixa (14:30) para envio
            val localDateTime = localDate.atTime(14, 30) // Define horário fixo
            val formatterEnvio = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
            val dataEnvio = localDateTime.format(formatterEnvio)

            // Armazene a data no formato ISO-8601 para envio
            dataCorte.tag = dataEnvio
        }


        botaoConfirmar.setOnClickListener {
            val corteId = intent.getLongExtra("CORTE_ID", -1L)
            if (corteId == -1L) {
                Toast.makeText(this, "Erro ao identificar o corte selecionado", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            // Obtenha a data formatada para envio
            val dataSelecionada = dataCorte.tag?.toString() ?: ""
            if (dataSelecionada.isEmpty()) {
                Toast.makeText(this, "Selecione uma data válida.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val clienteId = 17L // Seu cliente

            val agendamentoRequest = AgendamentoRequest(
                clienteId = clienteId,
                servicoId = corteId,
                dataHora = dataSelecionada
            )

            val token = getToken()
            if (token.isNullOrEmpty()) {
                Toast.makeText(this, "Usuário não autenticado. Faça login novamente.", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
                return@setOnClickListener
            }

            RetrofitClient.instance.agendar("Bearer $token", agendamentoRequest)
                .enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        if (response.isSuccessful) {
                            Toast.makeText(this@MarcarCorteActivity, "Agendamento realizado!", Toast.LENGTH_LONG).show()
                            val intent = Intent(this@MarcarCorteActivity, TelaDePagamentoActivity::class.java)
                            intent.putExtra("CHAVE_CORTE", corteEscolhido.text)
                            intent.putExtra("CHAVE_PRECO", precoDoCorte.text)
                            intent.putExtra("CHAVE_DATA", dataCorte.text) // Usa o formato exibido na tela
                            startActivity(intent)
                        } else {
                            Log.d("API_ERRO", "Erro ao agendar: Código ${response.code()}, mensagem: ${response.message()}, corpo: ${response.errorBody()?.string()}")
                            Toast.makeText(this@MarcarCorteActivity, "Erro ao agendar! Verifique o log.", Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        Log.d("API_ERRO", "Falha na conexão: ${t.message}")
                        Toast.makeText(this@MarcarCorteActivity, "Falha na conexão: ${t.message}", Toast.LENGTH_LONG).show()
                    }
                })
        }
    }

    private fun getToken(): String? {
        val sharedPreferences = getSharedPreferences("USER_PREFS", Context.MODE_PRIVATE)
        return sharedPreferences.getString("USER_TOKEN", null)
    }
}
