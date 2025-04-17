package com.example.srilankabarbershop.ui.onboarding

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class OnboardingUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val canGoToNextScreen: Boolean = false,
    val errorMessage: String? = null
)

class OnboardingViewModel() : ViewModel() {

    private val _uiState = MutableStateFlow<OnboardingUiState>(OnboardingUiState())
    val uiState: StateFlow<OnboardingUiState> = _uiState


    fun verifyInputs(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        val error = when {
            name.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank() ->
                false

            else -> true
        }
        return error
    }

    fun verifyPassword(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }

    fun onConfirmClicked(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {
        val errorMessage = when {
            !verifyInputs(name, email, password, confirmPassword) -> "Preencha todos os campos"
            !verifyPassword(password, confirmPassword) -> "As senhas não coincidem"
            else -> null
        }

        _uiState.update {
            it.copy(
                name = name,
                email = email,
                password = password,
                confirmPassword = confirmPassword,
                canGoToNextScreen = errorMessage == null,
                errorMessage = errorMessage
            )
        }
    }

}
