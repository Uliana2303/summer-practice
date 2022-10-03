package com.example.epli.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.epli.ui.theme.Palette

@Composable
fun DOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier.height(50.dp),
        value = value,
        placeholder = {
              Text(
                  text = placeholder,
                  style = TextStyle(
                      color = Palette.grey,
                      fontSize = 14.sp
                  )
              )
        },
        shape = RoundedCornerShape(4.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = Palette.black,
            focusedBorderColor = Palette.grey,
            unfocusedBorderColor = Palette.grey,
            textColor = Palette.grey
        ),
        onValueChange = onValueChange)
}
