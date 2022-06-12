package com.example.loginappcomposesmaat.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.platrow.Greeting

import com.example.platrow.R
import com.example.platrow.UIScreens.mainText
import com.example.platrow.ui.theme.*

@Composable
fun RegisterScreen(modifier: Modifier){


    Surface() {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(color = MainColor),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Greeting( "Welcome to Platraw ...")
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
                        mainText(modifier = Modifier,
                            "Register",
                            "Start your journey with Platrow...")
                        EnterNameFieldEnd()
                        chooseCountryEndView()
                        PasswordField()
                        TermsAndConditionsText()
                        RegisterButton ("Register",
                            navController =rememberNavController())
                        LoginText()
                    }
                }
            }
        }
    }
}

@Composable
fun RegisterButton (text : String, navController : NavController){
    OutlinedButton(onClick =
    {
        //navController.navigate(route = Screen.CategoryScreen.route)
    },
        modifier = Modifier
            .fillMaxWidth(1f)
            .height(68.dp)
            .padding(top = 4.dp, end = 16.dp, start = 16.dp, bottom = 4.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = BtnColor),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(text = text,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            color = lightColors().onPrimary
        )

    }
}

@Composable
fun chooseCountryEndView(){
    Surface(modifier = Modifier
        .fillMaxWidth()
        .height(78.dp)
        .padding(8.dp)
        .clip(RoundedCornerShape(16.dp))) {
        chooseCountry()
    }
}
@Composable
fun chooseCountry(){

    OutlinedTextFieldBackground(color = TextFieldColor) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(color = Gray),
            horizontalArrangement  =  Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically) {

            var textState by remember {
                mutableStateOf("")
            }

            Image(painter = painterResource(id = R.drawable.ksa)
                , contentDescription ="",
                modifier = Modifier
                    .size(48.dp)
                    .padding(4.dp))

            Text(text = "+996", modifier = Modifier.padding(4.dp))

                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Phone Number") },
                    value = textState,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Gray,
                        cursorColor = Color.Black,
                        disabledLabelColor = Gray,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    onValueChange = {
                        textState = it
                    },
                    singleLine = true
                )
        }
    }
}

@Composable
fun OutlinedTextFieldBackground(
    color: Color,
    content: @Composable () -> Unit
) {
    // This box just wraps the background and the OutlinedTextField
    Box (modifier = Modifier.clip(shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center){
        // This box works as background
        Box(
            modifier = Modifier
                .matchParentSize()
                .padding(16.dp) // adding some space to the label
                .background(
                    color
                )
                .clip(shape = RoundedCornerShape(8.dp)),
        )
        // OutlineTextField will be the content...
        content()
    }
}

@Composable
fun MyTextField() {

    var textFieldState by remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .padding(start = 16.dp, end = 16.dp),
        value = textFieldState,
        onValueChange = {
            textFieldState = it
        },
        placeholder = {
            Text(text = "Enter your name")
        },
        shape = RoundedCornerShape(8.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Phone)
    )
}

@Composable
fun EnterNameTextField() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    TextField(modifier = Modifier
        .fillMaxWidth(1f)
        .height(78.dp)
        .padding(8.dp),
        value = text,
        label = { Text(text = "Enter you name") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        shape = RoundedCornerShape(8.dp),
        onValueChange = { it ->
            text = it
        }
    )
}

@Composable
fun EnterNameFieldEnd(){
    Surface(modifier = Modifier
        .fillMaxWidth()
        .height(78.dp)
        .padding(8.dp)
        .clip(RoundedCornerShape(16.dp))) {
        EnterNameField()
    }
}

@Composable
fun EnterNameField (){
    OutlinedTextFieldBackground(color = TextFieldColor) {
        var textState by remember { mutableStateOf(TextFieldValue("")) }

        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Enter your Name...") },
            value = textState,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Gray,
                cursorColor = Color.Black,
                disabledLabelColor = Gray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            onValueChange = {
                textState = it
            },
            singleLine = true
        )
    }
}

@Composable
fun TermsAndConditionsText() {

    val annotatedString = buildAnnotatedString {
        append("By registering you agree to the")
        withStyle(style = SpanStyle(Red700)) {
            append(" Terms and Condition")
        }
        append("and the ")
        withStyle(style = SpanStyle(Red700)){
            append("Privacy policy")
        }
    }

    Surface(modifier = Modifier.padding(16.dp)) {
        Text(text = annotatedString, fontSize = 16.sp)
    }

}

@Composable
fun LoginText(){
    val annotatedString = buildAnnotatedString {
        append("Have an Account? ")
        withStyle(style = SpanStyle(Red700)){
            append("LOGIN")
        }
    }
    Surface(modifier = Modifier.padding(4.dp)) {
        Text(text = annotatedString, fontSize = 16.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewRegister() {
    PlatrowTheme() {
        RegisterScreen(modifier = Modifier.fillMaxSize())
    }
    }