package com.example.srilankabarbershop.ui.payment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.srilankabarbershop.R
import com.example.srilankabarbershop.databinding.FragmentPaymentBinding

class PaymentFragment : Fragment(R.layout.fragment_payment) {

    private var _binding : FragmentPaymentBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentPaymentBinding.bind(view)
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