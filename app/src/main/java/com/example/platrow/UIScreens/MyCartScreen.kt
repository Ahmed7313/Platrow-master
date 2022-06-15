package com.example.platrow

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.extendsdailytasks.product1
import com.example.platrow.UIScreens.*
import com.example.platrow.ui.theme.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun MyCartScreen(navigator: DestinationsNavigator) {
    val boolean = true
    var orderList = remember    {
        mutableStateListOf(product1, product2)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "My Cart") },
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
        }, backgroundColor = TextFieldColor,
        content = {
            PlatrowTheme {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(dim_large.dp),
                    contentPadding = PaddingValues(vertical = dim_xlarge.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    item {
                        Row(
                            modifier = Modifier
                                .padding(start = dim_xlarge.dp, end = dim_xlarge.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Text(
                                text = "Order Details",
                                fontSize = 28.sp,
                                modifier = Modifier
                            )

                            Row(modifier = Modifier
                                .clickable {
                                    orderList.clear()
                                }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_delete),
                                    contentDescription = null,
                                    modifier = Modifier.size(30.dp)
                                )
                                Text(text = "Remove All", fontSize = 20.sp, color = Red700)
                            }
                        }
                    }
                    itemsIndexed(orderList) { index, item ->
                        OrderListItem(product = item){
                            orderList.removeAt(index)
                        }
                    }
                    item {
                        AddproductFromWIshLis(){
                            orderList.add(product1)
                        }
                    }
                    item {
                        Text(text = "Payment Details",
                            fontSize = 24.sp,
                            modifier = Modifier.padding(start = 24.dp, top = 16.dp))
                        PaymentDetails()
                    }
                    item {
                        Card(modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp),
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)) {
                            CheckOutButton (orderList.size.toString())
                        }
                    }
                }
            }
        }
    )
}


@Composable
fun AddproductFromWIshLis(onClick : ()-> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, top = 16.dp)) {
        Icon(
            painter = painterResource(id = R.drawable.add_circular_button_thin_symbol),
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .clickable { onClick }
        )
        Text(
            text = "Add new products from wishList", color = Red700,
            fontSize = 18.sp, modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun PaymentDetails() {

    Card(
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp, top = 16.dp),
        shape = RoundedCornerShape(16.dp)
    ) {

        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Sub Total", fontSize = 14.sp,
                )
                MultipleStylesInText(text = "48.00",
                    color = Color.Black,
                    fontSize =14.sp
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Text(
                    text = "Delivery Fees", fontSize = 14.sp,
                    color = dividerColor
                )

                MultipleStylesInText(text = "10.00",
                    color = Color.Black,
                    fontSize =14.sp
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text(
                    text = "Total", fontSize = 18.sp,
                )
                MultipleStylesInText(text = "58.00",
                    color = Color.Black,
                    fontSize =18.sp
                )
            }
        }
    }
}

@Composable
fun MultipleStylesInText(text : String, color: Color, fontSize: TextUnit) {
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(color = color, fontSize = fontSize)) {
                append(text)
            }
            withStyle(style = SpanStyle(color = dividerColor, fontSize = fontSize )) {
                append(" SAR")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun RemoveAllItemsBottomSheetContent(){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Divider(
            color = dividerColor,
            thickness = 4.dp,
            modifier = Modifier
                .padding(top = dim_xlarge.dp, bottom = dim_large.dp)
                .align(Alignment.CenterHorizontally)
                .width(34.dp)
                .clip(RoundedCornerShape(8.dp)))

        Text(
            text = "Remove All Items ?",
            fontSize = 24.sp,
            color = Color.Black
        )

        Text(
            text = "Are you sure you want to delete from the cart?",
            fontSize = 16.sp,
            color = TextGrayColor,
            modifier = Modifier.padding(start = dim_xlarge.dp, end = dim_xlarge.dp)
        )

        deleteAllItemsBSButton("Delete My Cart", BtnColor){}
        deleteAllItemsBSButton("DISCARD", BtnColor){}

        Divider(
            color = TextFieldColor,
            thickness = 6.dp,
            modifier = Modifier
                .padding(top = dim_large.dp, bottom = dim_large.dp)
                .align(Alignment.CenterHorizontally)
                .width(44.dp)
                .clip(RoundedCornerShape(8.dp)))
    }
}

@Composable
fun deleteAllItemsBSButton (text : String, color: Color, onClick: () -> Unit){
    OutlinedButton(onClick = { onClick },
        modifier = Modifier
            .fillMaxWidth(1f)
            .height(100.dp)
            .padding(16.dp),
        shape = RoundedCornerShape(dim_bottom_radius.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = color) ) {
        androidx.compose.material.Text(
            text = text,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            color = Color.White
        )

    }
}
@Preview(showBackground = true)
@Composable
fun showMycartVIew() {
    Column(modifier = Modifier.fillMaxWidth()) {
        //OrderItem(product = product1)
        //AddproductFromWIshLis()
        //PaymentDetails()
        //MyCartScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun showCartDtaaVIew() {
    Column(modifier = Modifier.fillMaxWidth()) {
        OrderItemListColumn()
        AddproductFromWIshLis(){}
        PaymentDetails()
    }
}