package com.example.srilankabarbershop

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.srilankabarbershop.ui.LoginFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(
        savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<LoginFragment>(R.id.fragment_container_view)
            }
        }
    }
}