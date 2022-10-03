package com.example.epli.ui.screens.login


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.epli.R
import com.example.epli.ui.screens.login.models.LoginEvent
import com.example.epli.ui.screens.login.models.LoginSubState
import com.example.epli.ui.screens.login.models.LoginViewState
import com.example.epli.ui.screens.login.views.SingInView
import com.example.epli.ui.screens.login.views.SingUpView
import com.example.epli.ui.theme.Palette
import com.example.epli.ui.theme.Shapes


@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel
){
    val viewState = loginViewModel.viewState.observeAsState(LoginViewState())
    with(viewState.value) {
        LazyColumn (
            horizontalAlignment = CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(Palette.black),
        ){
            item {
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_icon_1024),
                    contentDescription = null,
                    contentScale =ContentScale.FillBounds,
                    modifier = Modifier
                        .height(190.dp)
                        .padding(top = 30.dp)
                        .width(160.dp)
                        .fillMaxSize()
                )
            }
            item {
                Text(modifier = Modifier.padding(top = 1.dp),
                    text = "epli",
                    style = TextStyle(
                        color = Palette.blue,
                        fontSize = 40.sp,
                        fontStyle = FontStyle.Italic
                    )

                )
            }
            item {
                when (loginSubState) {
                    LoginSubState.SingIn -> SingInView(
                        viewState = this@with,
                        onTextFieldChange = {
                            loginViewModel.obtainEvent(LoginEvent.EmailChanged(it))
                        }
                    )
                    LoginSubState.SingUp -> SingUpView()
                }
            }
        }

    }

}