package com.example.srilankabarbershop.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.srilankabarbershop.R
import com.example.srilankabarbershop.databinding.FragmentChooseHairCutBinding

class ChooseHairCutFragment : Fragment(R.layout.fragment_choose_hair_cut) {

    private var _binding: FragmentChooseHairCutBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentChooseHairCutBinding.bind(view)

        setup()
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