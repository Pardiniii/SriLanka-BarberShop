package com.example.srilankabarbershop.ui.payment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.srilankabarbershop.R
import com.example.srilankabarbershop.databinding.FragmentCardRegisterBinding

class CardRegisterFragment : Fragment(R.layout.fragment_card_register) {

    private var _binding: FragmentCardRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCardRegisterBinding.bind(view)

    }

    private fun setup() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

    }
}