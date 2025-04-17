package com.example.srilankabarbershop.ui.forgotpassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.srilankabarbershop.R
import com.example.srilankabarbershop.databinding.FragmentForgotPasswordBinding


class ForgotPasswordFragment : Fragment(R.layout.fragment_forgot_password) {

    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentForgotPasswordBinding.bind(view)

        setup()
    }

    private fun setup() {
        with(binding) {
            confirmBtn.setOnClickListener {
                navigateToNewPassword()
            }
        }

    }

    private fun navigateToNewPassword() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, NewPasswordFragment())
            .addToBackStack("Email confirmation")
            .setReorderingAllowed(true)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

    }
}