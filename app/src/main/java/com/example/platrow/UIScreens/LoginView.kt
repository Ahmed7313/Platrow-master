package com.example.loginappcomposesmaat.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.platrow.ui.theme.PlatrowTheme
import androidx.navigation.compose.rememberNavController

import com.example.platrow.R
import com.example.platrow.ui.theme.*

@Composable
fun Greeting2(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    PlatrowTheme() {
        LoginScreen(modifier = Modifier.fillMaxSize())
    }
}

@Composable
fun Greeting2() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(
            top = 160.dp,
            start = 16.dp,
            end = 24.dp
        )) {
        Text(text = "Welcome",
            color = Color.White,
            fontSize = 48.sp,
            modifier = Modifier.padding(8.dp))
        Text(text = "Back...!",
            color = Color.White,
            fontSize = 48.sp,
            modifier = Modifier.padding(8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun LoginEntryText(
    modifier: Modifier = Modifier
){

    Column(
        modifier = modifier.padding(16.dp)
    ) {
        Surface(
            modifier = modifier.semantics { heading() }
        ) {
            Text(
                text = stringResource(R.string.login_with_pass),
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                fontSize = 24.sp
            )
        }
        Surface(
            modifier = modifier.semantics { heading() }
        ) {
            Text(
                text = stringResource(R.string.enter_pass_text),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                style = MaterialTheme.typography.body2,
                fontSize = 16.sp
            )
        }
    }
}



@Composable
fun PasswordField(
) {
    var password by  rememberSaveable {
        mutableStateOf("")
    }
    var passwordVisiblity by remember {
        mutableStateOf(false)
    }

    val icon = if (passwordVisiblity)
        painterResource(id = R.drawable.design_icon_visibility)
    else
        painterResource(id = R.drawable.design_icon_visibility_off)
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(78.dp)
            .padding(8.dp),
        value = password,
        onValueChange ={ password = it},
        placeholder = { Text(text = "Password") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        shape = RoundedCornerShape(8.dp),
        label = { Text(text = "Password") },
        trailingIcon = {
            IconButton(onClick = { passwordVisiblity = !passwordVisiblity }) {
                Icon(
                    painter = icon ,
                    contentDescription = "Icon Visibility",
                    modifier = Modifier.size(24.dp))
            }
        },
        visualTransformation = if (passwordVisiblity) VisualTransformation.None
        else PasswordVisualTransformation(),
    )

}

@Composable
fun OTPLogin(){
    Text(text = "LOGIN WITH OTP",
        color = Red300,
        modifier = Modifier
            .padding(start = 8.dp)
    )
}

@Composable
fun LoginButton (text : String){
    OutlinedButton(onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth(1f)
            .height(100.dp)
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = BtnColor) ) {
        Text(text = text,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            color = Color.White
        )

    }
}

@Composable
fun LoginScreen(modifier: Modifier){

    Column (modifier =
    Modifier
        .background(color = MainColor)
        .fillMaxWidth()
        .fillMaxHeight()){
        Greeting2()
        Card(
            Modifier.fillMaxSize(),
            elevation = 4.dp,
            backgroundColor = SpacerColor,
            shape = RoundedCornerShape(
                topStart = 24.dp,
                topEnd = 24.dp)
        ){
            Card(modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp),
                elevation = 4.dp,
                shape = RoundedCornerShape(
                    topStart = 24.dp,
                    topEnd = 24.dp)
            ) {
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                    LoginEntryText()
                    PasswordField()
                    LoginButton ("Login")
                    OTPLogin()
                }
            }
        }
    }
}