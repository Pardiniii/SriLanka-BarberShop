package com.example.srilankabarbershop.ui.payment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.srilankabarbershop.R
import com.example.srilankabarbershop.databinding.FragmentFinishPaymentBinding


class FinishPaymentFragment : Fragment(R.layout.fragment_finish_payment) {

    private var _binding : FragmentFinishPaymentBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFinishPaymentBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

    }
}