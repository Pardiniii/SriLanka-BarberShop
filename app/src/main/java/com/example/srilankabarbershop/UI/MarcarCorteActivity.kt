package com.example.srilankabarbershop.UI

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
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
import com.jakewharton.threetenabp.AndroidThreeTen
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

class MarcarCorteActivity : AppCompatActivity() {

    lateinit var binding: ActivityMarcarCorteBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidThreeTen.init(this)

        binding = ActivityMarcarCorteBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        enableEdgeToEdge()
        setContentView(binding.root)

        val calendario = binding.calendario
        val corteEscolhido = binding.corteAtualTV
        val precoDoCorte = binding.valorCorteTV
        val botaoConfirmar = binding.confirmaCorteBtn
        val dataCorte = binding.dataCorteTV
        val btn10h30 = binding.horas1030
        val btn14h00 = binding.horas1400
        val btn17h45 = binding.horas1745

        // Variáveis para armazenar data e hora escolhidas
        var horaSelecionada: String? = null
        var dataSelecionada: LocalDate? = null

        val buttons = listOf(btn10h30, btn14h00, btn17h45)

        // Recebe dados do corte da activity anterior
        intent?.let {
            val corteRecebido = intent.getStringExtra("CHAVE")
            val precoRecebido = intent.getStringExtra("CHAVE2")
            corteEscolhido.text = corteRecebido
            precoDoCorte.text = precoRecebido
        }

        // Função que junta data + hora sempre que qualquer um dos dois for alterado
        fun atualizarDataHoraSelecionada() {
            if (dataSelecionada != null && horaSelecionada != null) {
                val (hora, minuto) = horaSelecionada!!.split(":").map { it.toInt() }
                val localDateTime = dataSelecionada!!.atTime(hora, minuto)

                // Mostra no formato dd/MM/yyyy
                val formatterExibicao = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                dataCorte.text = dataSelecionada!!.format(formatterExibicao)

                // Salva no formato correto para envio na API
                val formatterEnvio = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
                val dataEnvio = localDateTime.format(formatterEnvio)
                dataCorte.tag = dataEnvio
            } else {
                dataCorte.tag = null
            }
        }

        // Quando clicar em um botão de horário
        buttons.forEach { button ->
            button.setOnClickListener {
                buttons.forEach {
                    it.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FF8532")) // Cor padrão
                    it.setTextColor(Color.BLACK)
                }

                button.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FF5722")) // Cor do botão selecionado
                button.setTextColor(Color.WHITE)

                horaSelecionada = button.text.toString()

                // Atualiza a data + hora combinadas
                atualizarDataHoraSelecionada()
            }
        }

        // Quando selecionar uma data no calendário
        calendario.setOnDateChangeListener { _, year, month, day ->
            dataSelecionada = LocalDate.of(year, month + 1, day) // Mês começa em 0, por isso +1
            atualizarDataHoraSelecionada() // Atualiza a data + hora combinadas
        }

        // Quando clicar no botão de confirmar agendamento
        botaoConfirmar.setOnClickListener {
            val corteId = intent.getLongExtra("CORTE_ID", -1L)
            if (corteId == -1L) {
                Toast.makeText(this, "Erro ao identificar o corte selecionado", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val dataSelecionadaStr = dataCorte.tag?.toString() ?: ""
            if (dataSelecionadaStr.isEmpty()) {
                Toast.makeText(this, "Data ou horário inválido.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (horaSelecionada == null) {
                Toast.makeText(this, "Selecione um horário.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            // Remove 3 horas para ajustar fuso
            val localDate = LocalDate.parse(dataSelecionadaStr.substring(0, 10))
            val (hora, minuto) = horaSelecionada!!.split(":").map { it.toInt() }
            val dataHora = localDate.atTime(hora, minuto).minusHours(3)

            val formatterEnvio = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
            val dataEnvio = dataHora.format(formatterEnvio)

            val clienteId = 7L // Exemplo fixo

            val agendamentoRequest = AgendamentoRequest(
                clienteId = clienteId,
                servicoId = corteId,
                dataHora = dataEnvio
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
                            intent.putExtra("CHAVE_DATA", dataCorte.text)
                            startActivity(intent)
                        } else {
                            Log.d("API_ERRO", "Erro ao agendar: Código ${response.code()}, mensagem: ${response.message()}, corpo: ${response.errorBody()?.string()}")
                            Toast.makeText(this@MarcarCorteActivity, "Erro ao agendar! Verifique o log.", Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        Log.d("API_ERRO", "Falha na conexão: ${t.message}")
                        Toast.makeText(this@MarcarCorteActivity, "Falha na conexão: ${t.message}", Toast.LENGTH_LONG).show()
                        val intent = Intent(this@MarcarCorteActivity, TelaDePagamentoActivity::class.java)
                        intent.putExtra("CHAVE_CORTE", corteEscolhido.text)
                        intent.putExtra("CHAVE_PRECO", precoDoCorte.text)
                        intent.putExtra("CHAVE_DATA", dataCorte.text)
                        startActivity(intent)
                    }
                })
        }
    }

    private fun getToken(): String? {
        val sharedPreferences = getSharedPreferences("USER_PREFS", Context.MODE_PRIVATE)
        return sharedPreferences.getString("USER_TOKEN", null)
    }
}
