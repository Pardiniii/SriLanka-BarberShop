package com.example.srilankabarbershop.ui.forgotpassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.srilankabarbershop.R
import com.example.srilankabarbershop.databinding.FragmentNewPasswordBinding
import com.example.srilankabarbershop.ui.LoginFragment

class NewPasswordFragment : Fragment(R.layout.fragment_new_password) {

    private var _binding: FragmentNewPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNewPasswordBinding.bind(view)

        setup()
    }

    private fun setup() {
        binding.confirmBtn.setOnClickListener {
            navigateToLogin()
        }
    }

    private fun navigateToLogin() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, LoginFragment())
            .addToBackStack("New Password screen")
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