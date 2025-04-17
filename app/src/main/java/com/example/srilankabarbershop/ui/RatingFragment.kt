package com.example.srilankabarbershop.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.srilankabarbershop.R
import com.example.srilankabarbershop.databinding.FragmentRatingBinding

class RatingFragment : Fragment(R.layout.fragment_rating) {

    private var _binding: FragmentRatingBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRatingBinding.bind(view)

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