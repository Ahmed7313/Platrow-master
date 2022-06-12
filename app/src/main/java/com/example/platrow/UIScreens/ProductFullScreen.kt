package com.example.platrow

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraphNavigator
import com.example.extendsdailytasks.ProductDetailView
import com.example.extendsdailytasks.ProductListScreen
import com.example.extendtaskfoodapp.navigation.Screen
import com.example.platrow.destinations.MyCartScreenDestination
import com.example.platrow.model.Product
import com.example.platrow.ui.theme.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@Composable
fun ProductFullScreen(navigator: DestinationsNavigator){
    val boolean = true
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Product Details") },
                backgroundColor = Color.White,
                navigationIcon = if (boolean) {
                    {
                        IconButton(onClick = { }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                } else {
                    null
                }

            )
        }, backgroundColor = Color.White,
        content = {
            PlatrowTheme {
                Column(modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(color = dividerColor)) {

                    val productMainActivity = Product(1,
                        listOf(
                            "https:\\/\\/www.themealdb.com\\/images\\/category\\/beef.png",
                            "https:\\/\\/www.themealdb.com\\/images\\/category\\/chicken.png",
                            "https:\\/\\/www.themealdb.com\\/images\\/category\\/dessert.png",
                            "https:\\/\\/www.themealdb.com\\/images\\/category\\/lamb.png"),
                        "Kikate CHoclate Flavored",
                        "122", 30.00, "400", "Choclate")

                    Divider(color = TextFieldColor, thickness = 24.dp,
                        modifier = Modifier)
                    ProductDetailView(product = productMainActivity, modifier =
                    Modifier.padding(top = 24.dp) ) {}

                    Divider(color = TextFieldColor, thickness = 24.dp,
                        modifier = Modifier)
                    Column(modifier = Modifier
                        .background(color = Color.White)
                        .fillMaxWidth()) {
                        Text(
                            text = "More From Cadbory", fontSize = 24.sp,
                            modifier = Modifier.padding(start = 30.dp, top = 12.dp)
                        )
                        ProductListScreen{}

                        Divider(color = TextFieldColor, thickness = 24.dp,
                            modifier = Modifier)
                        Text(
                            text = "Similar Products", fontSize = 24.sp,
                            modifier = Modifier.padding(start = 30.dp, top = 12.dp)
                        )
                        ProductListScreen(){}

                        Divider(color = TextFieldColor, thickness = 24.dp,
                            modifier = Modifier)

                        MainBtn(text = "Add to Cart", navigator = navigator)
                    }

                }
            }
        })
        }

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    PlatrowTheme {
        val productMainActivity = Product(1,
            listOf(
                "https:\\/\\/www.themealdb.com\\/images\\/category\\/beef.png",
                "https:\\/\\/www.themealdb.com\\/images\\/category\\/chicken.png",
                "https:\\/\\/www.themealdb.com\\/images\\/category\\/dessert.png",
                "https:\\/\\/www.themealdb.com\\/images\\/category\\/lamb.png"),
            "Kikate CHoclate Flavored",
            "122", 30.00, "400", "Choclate")

        //ProductFullScreen()
    }
    
}

@Composable
fun MainBtn (text : String, navigator: DestinationsNavigator){
    val context = LocalContext.current

    OutlinedButton(onClick = {
        navigator.navigate(MyCartScreenDestination)
    },
        modifier = Modifier
            .fillMaxWidth(1f)
            .height(100.dp)
            .padding(top = 16.dp, start = 24.dp, end = 24.dp, bottom = 24.dp),
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