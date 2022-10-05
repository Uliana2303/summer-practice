package com.example.epli.ui.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.epli.R
import com.example.epli.common.EventHandler
import com.example.epli.ui.screens.login.models.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel(), EventHandler<LoginEvent> {

    private val _viewState = MutableLiveData(LoginViewState())
    val viewState: LiveData<LoginViewState> = _viewState

    override fun obtainEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.UsernameChanged -> usernameChanged(event.value)
            is LoginEvent.EmailChanged -> emailChanged(event.value)
            is LoginEvent.PasswordChanged -> passwordChanged(event.value)
            is LoginEvent.PasswordRepeatChanged -> passwordRepeatChanged(event.value)
            LoginEvent.SignInClicked -> signInClicked()
            LoginEvent.SignUpClicked -> signUpClicked()
            LoginEvent.DialogOkClicked -> dialogOkClicked()
        }
    }

    private fun dialogOkClicked() {
        _viewState.value =
            _viewState.value?.copy(
                dialogOptions = DialogOptions(
                    openDialog = false,
                    titleId = R.string.wrong_register_values_title,
                    textId = R.string.wrong_register_values
                )
            )

    }

    private fun usernameChanged(value: String) {
        _viewState.postValue(
            _viewState.value?.copy(
                loginValue = value
            )
        )
    }

    private fun emailChanged(value: String) {
        _viewState.postValue(_viewState.value?.copy(emailValue = value))
    }

    private fun passwordChanged(value: String) {
        _viewState.postValue(_viewState.value?.copy(passwordValue = value))
    }

    private fun passwordRepeatChanged(value: String) {
        _viewState.postValue(
            _viewState.value?.copy(
                passwordRepeatValue = value
            )
        )
    }

    private fun signInClicked() {
        when (_viewState.value?.loginSubState) {
            LoginSubState.SignUp -> _viewState.postValue(
                _viewState.value?.copy(
                    loginSubState = LoginSubState.SignIn
                )
            )
            LoginSubState.SignIn -> performSignIn()
            null -> Unit
        }

    }

    private fun signUpClicked() {
        when (_viewState.value?.loginSubState) {
            LoginSubState.SignIn -> _viewState.postValue(
                _viewState.value?.copy(
                    loginSubState = LoginSubState.SignUp
                )
            )
            LoginSubState.SignUp -> performSignUp()

            null -> Unit
        }
    }

    private fun validateSignUpFields() {
        val usernameError = LoginValidator.getUsernameErrorOrNull(_viewState.value!!.loginValue)
        val emailError = LoginValidator.getEmailErrorOrNull(_viewState.value!!.emailValue)
        val passwordError = LoginValidator.getPasswordErrorOrNull(_viewState.value!!.passwordValue)
        val passwordRepeatError = LoginValidator.getPasswordRepeatErrorOrNull(
            _viewState.value!!.passwordValue,
            _viewState.value!!.passwordRepeatValue
        )

        when {
            usernameError != null -> _viewState.postValue(
                _viewState.value?.copy(
                    dialogOptions = DialogOptions(
                        openDialog = true,
                        titleId = R.string.wrong_register_values_title,
                        textId = usernameError
                    )
                )
            )
            emailError != null -> _viewState.postValue(
                _viewState.value?.copy(
                    dialogOptions = DialogOptions(
                        openDialog = true,
                        titleId = R.string.wrong_register_values_title,
                        textId = emailError
                    )
                )
            )
            passwordError != null -> _viewState.postValue(
                _viewState.value?.copy(
                    dialogOptions = DialogOptions(
                        openDialog = true,
                        titleId = R.string.wrong_register_values_title,
                        textId = passwordError
                    )
                )
            )
            passwordRepeatError != null -> _viewState.postValue(
                _viewState.value?.copy(
                    dialogOptions = DialogOptions(
                        openDialog = true,
                        titleId = R.string.wrong_register_values_title,
                        textId = passwordRepeatError
                    )
                )
            )

        }
    }

    private fun validateSignInFields() {
        val emailError = LoginValidator.getEmailErrorOrNull(_viewState.value!!.emailValue)
        val isPasswordEmpty = _viewState.value!!.passwordValue.isEmpty()

        when {
            emailError != null -> _viewState.postValue(
                _viewState.value?.copy(
                    dialogOptions = DialogOptions(
                        openDialog = true,
                        titleId = R.string.wrong_login_values_title,
                        textId = emailError
                    )
                )
            )
            isPasswordEmpty -> _viewState.postValue(
                _viewState.value?.copy(
                    dialogOptions = DialogOptions(
                        openDialog = true,
                        titleId = R.string.wrong_login_values_title,
                        textId = R.string.password_required
                    )
                )
            )
        }
    }

    private fun performSignUp() {
        validateSignUpFields()

    }

    private fun performSignIn() {
        validateSignInFields()
        _viewState.postValue(
            _viewState.value?.copy(
                authenticationPassed = true
            )
        )
    }

}