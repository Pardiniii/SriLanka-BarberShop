package com.example.srilankabarbershop.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.srilankabarbershop.R
import com.example.srilankabarbershop.databinding.FragmentLoginBinding
import com.example.srilankabarbershop.ui.forgotpassword.ForgotPasswordFragment
import com.example.srilankabarbershop.ui.onboarding.OnboardingFragment

class LoginFragment : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLoginBinding.bind(view)

        setupView()
    }

    private fun setupView() {
        with(binding){
            cadastrarTV.setOnClickListener {
                navigateToOnboarding()
            }
            esqueciMinhaSenhaTV.setOnClickListener {
                navigateToForgotPassword()
            }
            confirmBtn.setOnClickListener {
                navigateToChooseHairCut()
            }
        }

    }

    private fun navigateToOnboarding() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, OnboardingFragment())
            .addToBackStack("Login screen")
            .setReorderingAllowed(true)
            .commit()
    }

    private fun navigateToChooseHairCut() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, ChooseHairCutFragment())
            .addToBackStack("Login screen")
            .setReorderingAllowed(true)
            .commit()
    }

    private fun navigateToForgotPassword() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, ForgotPasswordFragment())
            .addToBackStack("Login screen")
            .setReorderingAllowed(true)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
