package com.example.epli.ui.screens.login.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.epli.R
import com.example.epli.ui.components.DButton
import com.example.epli.ui.components.DOutlinedTextField
import com.example.epli.ui.screens.login.models.LoginViewState

@Composable
fun SingInView (
    viewState: LoginViewState,
    onTextFieldChange: (String) -> Unit
) {
    Column{
        DOutlinedTextField(
            modifier = Modifier
                .padding(top = 40.dp, bottom = 5.dp, start = 40.dp, end = 40.dp)
                .fillMaxSize(),
            value = viewState.emailValue,
            placeholder = stringResource(id = R.string.email_hint),
            onValueChange = onTextFieldChange
        )
        DOutlinedTextField(
            modifier = Modifier
                .padding(top = 10.dp, start = 40.dp, end = 40.dp)
                .fillMaxSize(),
            value = viewState.emailValue,
            placeholder = stringResource(id = R.string.password_hint),
            onValueChange = onTextFieldChange
        )

    }
}