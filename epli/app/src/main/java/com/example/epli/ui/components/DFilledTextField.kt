package com.example.epli.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.epli.ui.theme.Palette

@Composable
fun DFilledTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    secure: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = {},
    singleLine: Boolean = true,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    TextField(
        modifier = modifier.height(50.dp),
        value = value,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(16.dp),

        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color(0x19F5F4F4),
            textColor = Palette.grey,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,

        ),
        visualTransformation = if (secure) { PasswordVisualTransformation() } else {
            VisualTransformation.None },
        leadingIcon = leadingIcon,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        placeholder = {
            Text(
                text = placeholder,
                style = TextStyle(
                    color = Palette.grey,
                    fontSize = 14.sp
                )
            )
        }
    )

}