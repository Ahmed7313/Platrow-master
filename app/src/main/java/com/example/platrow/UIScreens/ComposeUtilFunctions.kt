package com.example.platrow.UIScreens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.platrow.ui.theme.PlatrowTheme
import androidx.navigation.compose.rememberNavController

import com.example.platrow.R
import com.example.platrow.ui.theme.*
import org.w3c.dom.Text

class ComposeUtilFunctions : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlatrowTheme() {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting3("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(firstText : String, secText : String) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(
            top = 75.dp,
            start = 16.dp,
            end = 24.dp
        )) {
        Text(text = firstText,
            color = Color.White,
            fontSize = 48.sp,
            modifier = Modifier.padding(8.dp))
        Text(text = secText,
            color = Color.White,
            fontSize = 48.sp,
            modifier = Modifier.padding(8.dp))
    }
}

@Composable
fun LoginRegisterButton (text : String, onClick : () -> Unit){
    OutlinedButton(onClick = { onClick },
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
fun PasswordField() {
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
            .fillMaxWidth(1f)
            .height(78.dp)
            .padding(top = 4.dp, end = 16.dp, start = 16.dp, bottom = 4.dp),
        value = password,
        onValueChange ={ password = it},
        placeholder = { androidx.compose.material.Text(text = "Password") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        label = { androidx.compose.material.Text(text = "Password") },
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


//fun getMenu(): MenuItem<String> {
//    val menu = dropDownMenu<String> {
//        item("about", "About") {
//            //icon(Icons.TwoTone)
//        }
//        item("copy", "Copy") {
//          //  icon(Icons.TwoTone)
//        }
//        item("share", "Share") {
//            //icon(Icons.TwoTone)
//        }
//    }
//    return menu
//}



@Composable
fun mainText(
    modifier: Modifier = Modifier,
    firstText: String,
    secText: String
){

    Column(
        modifier = modifier.padding(4.dp)
    ) {
        Surface(
            modifier = modifier.semantics { heading() }
        ) {
            Text(
                text = firstText,
                style = androidx.compose.material.MaterialTheme.typography.subtitle2,
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
                text = secText,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                style = androidx.compose.material.MaterialTheme.typography.body2,
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    PlatrowTheme() {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.background(color = Red800)) {
            Greeting("Welcome", "Back...!")
            //LoginRegisterButton("Login")
            PasswordField()
        }
    }
}

