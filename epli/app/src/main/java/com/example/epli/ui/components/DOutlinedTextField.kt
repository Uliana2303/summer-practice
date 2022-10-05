package com.example.epli.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.epli.ui.theme.Palette

@Composable
fun DOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    secure: Boolean = false,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    OutlinedTextField(
        modifier = modifier.height(50.dp),
        value = value,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(4.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = Palette.black,
            focusedBorderColor = Palette.grey,
            unfocusedBorderColor = Palette.grey,
            textColor = Palette.grey
        ),
        visualTransformation = if (secure) { PasswordVisualTransformation() } else {VisualTransformation.None },

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
