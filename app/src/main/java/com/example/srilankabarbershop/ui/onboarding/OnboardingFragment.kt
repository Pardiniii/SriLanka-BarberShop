package com.example.srilankabarbershop.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.srilankabarbershop.R
import com.example.srilankabarbershop.databinding.FragmentOnboardingBinding
import com.example.srilankabarbershop.ui.ChooseHairCutFragment
import kotlinx.coroutines.launch

class OnboardingFragment : Fragment(R.layout.fragment_onboarding) {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: OnboardingViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentOnboardingBinding.bind(view)
        setup()
        launchViewModel()
    }

    private fun setup() {
        with(binding) {
            confirmBtn.setOnClickListener {
                viewModel.onConfirmClicked(
                    name = nomeET.text.toString(),
                    email = emailET.text.toString(),
                    password = passwordET.text.toString(),
                    confirmPassword = confirmPasswordET.text.toString()
                )
            }
        }
    }

    private fun launchViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                if (state.canGoToNextScreen) {
                    navigateToChooseHairCut()
                }
                binding.respostaTV.text = state.errorMessage.orEmpty()
            }
        }
    }


    private fun navigateToChooseHairCut() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, ChooseHairCutFragment())
            .addToBackStack("Onboarding screen")
            .setReorderingAllowed(true)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}