package com.example.epli.ui.screens.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.epli.R
import com.example.epli.common.EventHandler
import com.example.epli.common.LoginResult
import com.example.epli.common.RegisterResult
import com.example.epli.data.tokenStore
import com.example.epli.network.ApiService
import com.example.epli.ui.screens.login.models.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(application: Application) : AndroidViewModel(application), EventHandler<LoginEvent> {

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

    private val apiService by lazy {
        ApiService.create()
    }

    private val context = application.applicationContext
    private suspend fun updateToken(token: String) {
        context.tokenStore.updateData { currentToken ->
            currentToken.toBuilder().setToken(token).build()
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
            LoginSubState.SignIn -> {
                if (validateSignInFields()) {
                    performSignIn()
                }
            }
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
            LoginSubState.SignUp -> {
                if (validateSignUpFields()) {
                    performSignUp()
                }
            }

            null -> Unit
        }
    }

    private fun validateSignUpFields() : Boolean {
        val usernameError = LoginValidator.getUsernameErrorOrNull(_viewState.value!!.loginValue)
        val emailError = LoginValidator.getEmailErrorOrNull(_viewState.value!!.emailValue)
        val passwordError = LoginValidator.getPasswordErrorOrNull(_viewState.value!!.passwordValue)
        val passwordRepeatError = LoginValidator.getPasswordRepeatErrorOrNull(
            _viewState.value!!.passwordValue,
            _viewState.value!!.passwordRepeatValue
        )

        when {
            usernameError != null -> {
                _viewState.postValue(
                    _viewState.value?.copy(
                        dialogOptions = DialogOptions(
                            openDialog = true,
                            titleId = R.string.wrong_register_values_title,
                            textId = usernameError
                        )
                    )

                )
                return false
            }
            emailError != null -> {
                _viewState.postValue(
                    _viewState.value?.copy(
                        dialogOptions = DialogOptions(
                            openDialog = true,
                            titleId = R.string.wrong_register_values_title,
                            textId = emailError
                        )
                    )
                )
                return false
            }
            passwordError != null -> {
                _viewState.postValue(
                    _viewState.value?.copy(
                        dialogOptions = DialogOptions(
                            openDialog = true,
                            titleId = R.string.wrong_register_values_title,
                            textId = passwordError
                        )
                    )
                )
                return false
            }
            passwordRepeatError != null -> {
                _viewState.postValue(
                    _viewState.value?.copy(
                        dialogOptions = DialogOptions(
                            openDialog = true,
                            titleId = R.string.wrong_register_values_title,
                            textId = passwordRepeatError
                        )
                    )

                )
                return false
            }
            else -> return true

        }
    }

    private fun validateSignInFields() : Boolean {
        val emailError = LoginValidator.getEmailErrorOrNull(_viewState.value!!.emailValue)
        val isPasswordEmpty = _viewState.value!!.passwordValue.isEmpty()

        when {
            emailError != null -> {
                _viewState.postValue(
                    _viewState.value?.copy(
                        dialogOptions = DialogOptions(
                            openDialog = true,
                            titleId = R.string.wrong_login_values_title,
                            textId = emailError
                        )
                    )
                )
                return false
            }
            isPasswordEmpty -> {
                _viewState.postValue(
                    _viewState.value?.copy(
                        dialogOptions = DialogOptions(
                            openDialog = true,
                            titleId = R.string.wrong_login_values_title,
                            textId = R.string.password_required
                        )
                    )
                )
                return false
            }
            else -> return true
        }
    }

    private fun performSignUp() {
        viewModelScope.launch(Dispatchers.IO) {
            val signUpResult = with(_viewState.value!!) {
                apiService.tryRegister(
                    login = loginValue,
                    email = emailValue,
                    password = passwordValue,
                )
            }

            when (signUpResult) {
                is RegisterResult.Ok -> {
                    updateToken(signUpResult.token)
                    _viewState.postValue(
                        _viewState.value?.copy(
                            authenticationPassed = true
                        )
                    )

                }
                is RegisterResult.EmailIsNotValid -> {
                    _viewState.postValue(
                        _viewState.value?.copy(
                            dialogOptions = DialogOptions(
                                openDialog = true,
                                titleId = R.string.wrong_email_title,
                                textId = R.string.email_not_valid
                            )
                        )
                    )
                }
                is RegisterResult.EmailAlreadyExists -> {
                    _viewState.postValue(
                        _viewState.value?.copy(
                            dialogOptions = DialogOptions(
                                openDialog = true,
                                titleId = R.string.user_already_exists_title,
                                textId = R.string.user_already_exists
                            )
                        )
                    )
                }
                is RegisterResult.SomethingWentWrong ->{
                    _viewState.postValue(
                        _viewState.value?.copy(
                            dialogOptions = DialogOptions(
                                openDialog = true,
                                titleId = R.string.something_went_wrong,
                                textId = R.string.sign_up_failed
                            )
                        )
                    )
                }
            }

        }
    }

    private fun performSignIn() {
        viewModelScope.launch(Dispatchers.IO) {
            val signInResult = with(_viewState.value!!) {
                apiService.tryLogin(
                    email = emailValue,
                    password = passwordValue,
                )
            }

            when (signInResult) {
                is LoginResult.Ok -> {
                    updateToken(signInResult.token)
                    _viewState.postValue(
                        _viewState.value?.copy(
                            authenticationPassed = true
                        )
                    )

                }
                is LoginResult.IncorrectCredentials -> {
                    _viewState.postValue(
                        _viewState.value?.copy(
                            dialogOptions = DialogOptions(
                                openDialog = true,
                                titleId = R.string.wrong_login_values_title,
                                textId = R.string.wrong_login_values
                            )
                        )
                    )
                }
                is LoginResult.SomethingWentWrong -> {
                    _viewState.postValue(
                        _viewState.value?.copy(
                            dialogOptions = DialogOptions(
                                openDialog = true,
                                titleId = R.string.something_went_wrong,
                                textId = R.string.sign_up_failed
                            )
                        )
                    )
                }
            }

        }
    }

}