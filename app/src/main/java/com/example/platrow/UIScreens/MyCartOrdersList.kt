package com.example.platrow.UIScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.platrow.model.Product
import com.example.platrow.R
import androidx.compose.runtime.*
import coil.compose.rememberAsyncImagePainter
import com.example.platrow.dim_button_height
import com.example.platrow.dim_large
import com.example.platrow.dim_raduis
import com.example.platrow.ui.theme.*

val product1 = Product(1,
    listOf("https:\\/\\/www.themealdb.com\\/images\\/category\\/beef.png",
        "https:\\/\\/www.themealdb.com\\/images\\/category\\/beef.png",
        "https:\\/\\/www.themealdb.com\\/images\\/category\\/chicken.png",
        "https:\\/\\/www.themealdb.com\\/images\\/category\\/dessert.png",
        "https:\\/\\/www.themealdb.com\\/images\\/category\\/lamb.png"),
    "Kikate CHoclate Flavored",
    "20*30", 30.00, "400", "Choclate")

val product2 = Product(1,
    listOf("https:\\/\\/www.themealdb.com\\/images\\/category\\/beef.png",
        "https:\\/\\/www.themealdb.com\\/images\\/category\\/beef.png",
        "https:\\/\\/www.themealdb.com\\/images\\/category\\/chicken.png",
        "https:\\/\\/www.themealdb.com\\/images\\/category\\/dessert.png",
        "https:\\/\\/www.themealdb.com\\/images\\/category\\/lamb.png"),
    "Kikate CHoclate Flavored",
    "20*30", 30.00, "400", "Choclate")
val listOfProducts = listOf<Product>(product1, product2,)

 var quantityNumber : String = "0"

@Composable
fun OrderItemListColumn(){

    LazyColumn( verticalArrangement = Arrangement.spacedBy(dim_large.dp),
        contentPadding = PaddingValues(vertical = 24.dp),
        modifier = Modifier){
        items(listOfProducts){ item ->
            OrderListItem(product = item){}
        }
    }
}

@Composable
fun OrderListItem(product: Product, onClick : ()-> Unit){
    Card(modifier = Modifier
        .padding(start = 24.dp, end = 24.dp).height(150.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(dim_raduis.dp)) {

        Row(modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)) {
            Image(painter =
            rememberAsyncImagePainter(model = product.listImageUrl[1]),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .align(Alignment.CenterVertically)
                    .clip(RoundedCornerShape(dim_raduis.dp)))

            Column(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.fillMaxWidth()) {

                    Text(
                        modifier = Modifier
                            .requiredWidthIn(max = 184.dp)
                            .padding(top = dim_large.dp),
                        text = product.name,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 18.sp
                    )

                    IconButton(modifier = Modifier
                        .padding(
                            start = 24.dp,
                            top = dim_large.dp,
                            end = 8.dp
                        )
                        .then(Modifier.size(24.dp)),
                        onClick = {onClick}) {
                        Image(
                            painter = painterResource(
                                id = R.drawable.ic_baseline_cancel_presentation_24),
                            "contentDescription")
                    }

                }
                Text(text = product.wieght + " CM",
                    modifier = Modifier.padding(top = 4.dp)
                    , fontSize = 14.sp, color = TextGrayColor
                )
                Incrementer(product = product)
            }
        }

    }
}

@Composable
fun Incrementer (product : Product){

    var totalPrice by  rememberSaveable {
        mutableStateOf(product.price)
    }
    var number by  rememberSaveable {
        mutableStateOf(1)
    }

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 24.dp),
        horizontalArrangement = Arrangement.Center) {

        Row(modifier = Modifier
            .width(120.dp)
            .align(Alignment.CenterVertically)
            .clip(shape = RoundedCornerShape(dim_raduis.dp))
            .height(38.dp)
            .background(color = TextFieldColor),
            horizontalArrangement = Arrangement.SpaceEvenly,) {

            IconButton(modifier = Modifier.
            then(Modifier.size(34.dp)),
                onClick = {
                    if (number == 1){
                        number = 1
                    }else{
                        number -= 1
                        totalPrice = priceCalculator(product.price, number)
                        quantityNumber = number.toString()
                    }
                }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_horizontal_rule_24),
                    "contentDescription")
            }

            Text(text = number.toString(), modifier = Modifier
                .padding(start = 8.dp, end = 8.dp), textAlign = TextAlign.Center
                , fontSize = 24.sp)

            IconButton(modifier = Modifier.
            then(Modifier.size(34.dp)),
                onClick = { number += 1
                    totalPrice = priceCalculator(product.price, number)
                    quantityNumber = number.toString()
                }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_add_24),
                    "contentDescription")
            }
        }

        Column(modifier = Modifier
            .align(Alignment.CenterVertically)
            .padding(start = 24.dp,)) {
            Text(text =" 12.00 * 2",
                modifier = Modifier
                    .padding(end = 24.dp)
                    .align(Alignment.End),
                fontSize = 8.sp)
            Text(text =  "${totalPrice} SAR",
                modifier = Modifier.padding( top = 8.dp, end = 24.dp),
                fontSize = 14.sp, color = MainColor)
        }
    }
}
@Preview(showBackground = true)
@Composable
fun showCartOrderList(){
    Column(modifier = Modifier.fillMaxWidth()) {
        OrderItemListColumn()
        CheckOutButton ("1")
    }
}

fun priceCalculator(price : Double, numberOfItems : Int) : Double{
    return price * numberOfItems
}

@Composable
fun CheckOutButton (numberOfItems: String){
    Box(modifier = Modifier
        .width(390.dp)
        .height(68.dp)
        .padding(start = 24.dp, end = 24.dp, top = dim_large.dp, bottom = dim_button_height.dp)
        .clip(RoundedCornerShape(dim_raduis.dp))
        .background(color = MainColor)
        .clickable {

        }) {
        Text(text = "CEHCKOUT NOW", modifier = Modifier
            .align(Alignment.Center),
            fontSize = 18.sp, color = Color.White,
        )
        Text(text = numberOfItems ,
            modifier = Modifier
            .align(Alignment.CenterStart).padding(start = 24.dp)
            .clip(CircleShape)
            .width(34.dp)
            .height(34.dp)
            .background(color = Red900),
            fontSize = 22.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}