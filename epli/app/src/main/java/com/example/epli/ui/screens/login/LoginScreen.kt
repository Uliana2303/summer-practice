package com.example.epli.ui.screens.login


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.epli.R
import com.example.epli.ui.screens.login.models.LoginEvent
import com.example.epli.ui.screens.login.models.LoginSubState
import com.example.epli.ui.screens.login.models.LoginViewState
import com.example.epli.ui.screens.login.views.SingInView
import com.example.epli.ui.screens.login.views.SingUpView
import com.example.epli.ui.theme.Palette


@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel,
    onAuthenticationPassed: () -> Unit
) {
    val viewState = loginViewModel.viewState.observeAsState(LoginViewState())




    with(viewState.value) {

        if (dialogOptions.openDialog) {
            AlertDialog(
                onDismissRequest = {
//                loginViewModel.obtainEvent(LoginEvent.DialogOkClicked)
                },
                confirmButton = {
                    TextButton(onClick = {
                        loginViewModel.obtainEvent(LoginEvent.DialogOkClicked)
                    }) {
                        Text(
                            text = "OK"
                        )
                    }
                },
                title = {
                    Text(
                        text = dialogOptions.titleId?.let { titleId ->
                            stringResource(titleId)
                        } ?: ""
                    )
                },
                text = {
                    Text(
                        text = dialogOptions.textId?.let { textId ->
                            stringResource(textId)
                        } ?: ""
                    )
                },
            )
        }

        Column(
            horizontalAlignment = CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(Palette.black),
        ) {
//            item {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.ic_icon_1024),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(190.dp)
                    .padding(top = 30.dp)
                    .width(160.dp)
                    .fillMaxSize()
            )
//            }
//            item {
            Text(
                modifier = Modifier.padding(top = 1.dp), text = "epli", style = TextStyle(
                    color = Palette.blue, fontSize = 40.sp, fontStyle = FontStyle.Italic
                )

            )
//            }
//            item {
            when (loginSubState) {
                LoginSubState.SignIn -> SingInView(viewState = this@with, onEmailChanged = {
                    loginViewModel.obtainEvent(LoginEvent.EmailChanged(it))
                }, onPasswordChanged = {
                    loginViewModel.obtainEvent(LoginEvent.PasswordChanged(it))
                }, onSignInClicked = {
                    loginViewModel.obtainEvent(LoginEvent.SignInClicked)
                }, onSignUpClicked = {
                    loginViewModel.obtainEvent(LoginEvent.SignUpClicked)
                })
                LoginSubState.SignUp -> SingUpView(viewState = this@with, onUsernameChanged = {
                    loginViewModel.obtainEvent(LoginEvent.UsernameChanged(it))
                }, onEmailChanged = {
                    loginViewModel.obtainEvent(LoginEvent.EmailChanged(it))
                }, onPasswordChanged = {
                    loginViewModel.obtainEvent(LoginEvent.PasswordChanged(it))
                }, onPasswordRepeatChanged = {
                    loginViewModel.obtainEvent(LoginEvent.PasswordRepeatChanged(it))
                }, onSignInClicked = {
                    loginViewModel.obtainEvent(LoginEvent.SignInClicked)
                }, onSignUpClicked = {
                    loginViewModel.obtainEvent(LoginEvent.SignUpClicked)
                })
            }
//            }
        }

        LaunchedEffect(key1 = authenticationPassed) {
            when (authenticationPassed) {
                true -> onAuthenticationPassed()
                false -> Unit
            }
        }

    }

}