package com.example.srilankabarbershop.UI

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.srilankabarbershop.R
import com.example.srilankabarbershop.databinding.ActivityCadastroBinding

class CadastroActivity : AppCompatActivity() {

    lateinit var binding: ActivityCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        supportActionBar?.hide()

        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var nomeET = binding.nomeET
        var emailET = binding.emailET
        var passwordET = binding.passwordET
        var confirmPasswordET = binding.confirmPasswordET
        val confirmBtn = binding.confirmBtn
        var respostaET = binding.respostaTV

        fun verificaCamposVazios(): Boolean {
            if (nomeET.text.isEmpty() || emailET.text.isEmpty() || passwordET.text.isEmpty() || confirmPasswordET.text.isEmpty()) {
                return true
            }
            return false
        }

        fun verificaSenha(): Boolean {
            var senha = passwordET.text.toString()
            var confirmaSenha = confirmPasswordET.text.toString()
            if (senha.equals(confirmaSenha)) {
                Log.i("senha", "${passwordET}" + " - "+ "${confirmPasswordET}")
                return true
            } else {
                Log.i("senha", "Se for falso ${passwordET}" + " - "+ "${confirmPasswordET}")
                return false
            }
        }

        confirmBtn.setOnClickListener {
            if (verificaCamposVazios()) {
                respostaET.setText("Preencha todos os campos")
            } else {
                if (verificaSenha()) {
                    val intent = Intent(this, EscolhaCorteActivity::class.java)
                    startActivity(intent)
                } else {
                    respostaET.setText("As senhas não coincidem")
                }
            }
        }

    }
}