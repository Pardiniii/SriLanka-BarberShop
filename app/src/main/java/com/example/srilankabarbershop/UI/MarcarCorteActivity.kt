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
//import java.time.LocalDate
//import java.time.format.DateTimeFormatter
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime


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

        var horaSelecionada: String? = null

        val buttons = listOf(btn10h30, btn14h00, btn17h45)

        intent?.let {
            val corteRecebido = intent.getStringExtra("CHAVE")
            val precoRecebido = intent.getStringExtra("CHAVE2")
            corteEscolhido.text = corteRecebido
            precoDoCorte.text = precoRecebido
        }

        buttons.forEach { button ->
            button.setOnClickListener {
                buttons.forEach {
                    it.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FF8532")) // Cor padrão
                    it.setTextColor(Color.BLACK) // Texto padrão
                }

                button.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FF5722")) // Cor quando selecionado
                button.setTextColor(Color.WHITE) // Texto quando selecionado

                // Salva a hora do botão clicado
                horaSelecionada = button.text.toString()
            }
        }

        calendario.setOnDateChangeListener { _, year, month, day ->
            // Crie um LocalDate com o ano, mês e dia selecionados
            val localDate = LocalDate.of(year, month + 1, day)  // mês começa em 0, por isso +1

            // Formate para exibição na tela no formato "dd/MM/yyyy"
            val formatterExibicao = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            val dataExibicao = localDate.format(formatterExibicao)
            dataCorte.text = dataExibicao // Exibe no formato "20/05/2025"

            if (horaSelecionada != null) {
                val (hora, minuto) = horaSelecionada!!.split(":").map { it.toInt() }

                val localDateTime = localDate.atTime(hora, minuto)
                val formatterEnvio = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
                val dataEnvio = localDateTime.format(formatterEnvio)

                dataCorte.tag = dataEnvio
            } else {
                dataCorte.tag = null
            }
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
                Toast.makeText(this, "Data ou horário inválido.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (horaSelecionada == null) {
                Toast.makeText(this, "Selecione um horário.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            // Converte a parte da data (yyyy-MM-dd) para LocalDate
            val localDate = LocalDate.parse(dataSelecionada.substring(0, 10))

            val (hora, minuto) = horaSelecionada!!.split(":").map { it.toInt() }

            val dataHora = localDate.atTime(hora, minuto).minusHours(3)

            val formatterEnvio = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
            val dataEnvio = dataHora.format(formatterEnvio)

            val clienteId = 11L // Seu cliente

            val agendamentoRequest = AgendamentoRequest(
                clienteId = clienteId,
                servicoId = corteId,
                dataHora = dataEnvio//dataSelecionada
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
                        //Toast.makeText(this@MarcarCorteActivity, "Falha na conexão: ${t.message}", Toast.LENGTH_LONG).show()
                        val intent = Intent(this@MarcarCorteActivity,TelaDePagamentoActivity::class.java)
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
