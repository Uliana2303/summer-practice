package com.example.epli.ui.screens.login.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.epli.R
import com.example.epli.ui.components.DOutlinedTextField
import com.example.epli.ui.screens.login.models.LoginViewState
import com.example.epli.ui.theme.AppTheme
import com.example.epli.ui.theme.Palette

@Composable
fun SingUpView(
    viewState: LoginViewState,
    onUsernameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onPasswordRepeatChanged: (String) -> Unit,
    onSignInClicked: () -> Unit,
    onSignUpClicked: () -> Unit
) {

    val focusManager = LocalFocusManager.current



    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding()
            .fillMaxSize()
    ) {
        DOutlinedTextField(
            modifier = Modifier
                .padding(top = 40.dp, start = 40.dp, end = 40.dp)
                .fillMaxWidth(),
            value = viewState.loginValue,
            placeholder = stringResource(id = R.string.username_hint),
            onValueChange = onUsernameChanged,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )
        DOutlinedTextField(
            modifier = Modifier
                .padding(top = 10.dp, start = 40.dp, end = 40.dp)
                .fillMaxWidth(),
            value = viewState.emailValue,
            placeholder = stringResource(id = R.string.email_hint),
            onValueChange = onEmailChanged,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )
        DOutlinedTextField(
            modifier = Modifier
                .padding(top = 10.dp, start = 40.dp, end = 40.dp)
                .fillMaxWidth(),
            value = viewState.passwordValue,
            placeholder = stringResource(id = R.string.password_hint),
            onValueChange = onPasswordChanged,
            secure = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )
        DOutlinedTextField(
            modifier = Modifier
                .padding(top = 10.dp, start = 40.dp, end = 40.dp)
                .fillMaxWidth(),
            value = viewState.passwordRepeatValue,
            placeholder = stringResource(id = R.string.password_repeat_hint),
            onValueChange = onPasswordRepeatChanged,
            secure = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            )
        )

        Spacer(Modifier.weight(1f))

        Button(
            onClick = onSignUpClicked,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = AppTheme.colors.blue
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 40.dp, end = 40.dp, top = 10.dp)
                .height(50.dp)
        ) {
            Text(
                text = stringResource(R.string.perform_sign_up),
                color = Palette.white
            )

        }
        OutlinedButton(
            onClick = onSignInClicked,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Palette.black
            ),
            border = BorderStroke(1.dp, AppTheme.colors.blue),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 40.dp, end = 40.dp, top = 10.dp, bottom = 8.dp)
                .height(50.dp)
        ) {
            Text(
                text = stringResource(R.string.already_have_account),
                color = Palette.white
            )

        }



    }
}