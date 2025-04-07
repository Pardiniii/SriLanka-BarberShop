package com.example.srilankabarbershop.UI

import android.graphics.Typeface
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.srilankabarbershop.R
import com.example.srilankabarbershop.databinding.ActivityEscolhaCorteBinding

class EscolhaCorteActivity : AppCompatActivity() {

    lateinit var binding: ActivityEscolhaCorteBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEscolhaCorteBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}