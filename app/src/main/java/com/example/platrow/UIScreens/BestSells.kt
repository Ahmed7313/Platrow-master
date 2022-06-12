package com.example.extendsdailytasks

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.platrow.model.Product
import com.example.platrow.ui.theme.Red700


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
    "122", 30.00, "400", "Choclate")

val product3 = Product(1,
    listOf("https:\\/\\/www.themealdb.com\\/images\\/category\\/beef.png",
        "https:\\/\\/www.themealdb.com\\/images\\/category\\/beef.png",
        "https:\\/\\/www.themealdb.com\\/images\\/category\\/chicken.png",
        "https:\\/\\/www.themealdb.com\\/images\\/category\\/dessert.png",
        "https:\\/\\/www.themealdb.com\\/images\\/category\\/lamb.png"),
    "Kikate CHoclate Flavored",
    "122", 30.00, "400", "Choclate")

val product4 = Product(1,
    listOf("https:\\/\\/www.themealdb.com\\/images\\/category\\/beef.png",
        "https:\\/\\/www.themealdb.com\\/images\\/category\\/beef.png",
        "https:\\/\\/www.themealdb.com\\/images\\/category\\/chicken.png",
        "https:\\/\\/www.themealdb.com\\/images\\/category\\/dessert.png",
        "https:\\/\\/www.themealdb.com\\/images\\/category\\/lamb.png"),
    "Kikate CHoclate Flavored",
    "122", 30.00, "400", "Choclate")

val product5 = Product(1,
    listOf("https:\\/\\/www.themealdb.com\\/images\\/category\\/beef.png",
        "https:\\/\\/www.themealdb.com\\/images\\/category\\/beef.png",
        "https:\\/\\/www.themealdb.com\\/images\\/category\\/chicken.png",
        "https:\\/\\/www.themealdb.com\\/images\\/category\\/dessert.png",
        "https:\\/\\/www.themealdb.com\\/images\\/category\\/lamb.png"),
    "Kikate CHoclate Flavored",
    "122", 30.00, "400", "Choclate")
val listOfProducts = listOf<Product>(product1,
    product2,
    product3,
    product4,
    product5)
@Composable
fun ProductListScreen(
    onItemClicked : (String) -> Unit
){
//    val viewModel = ViewModelCompat.getViewModel<CategoryViewModel>()
//    val viewModel : CategoryViewModel by viewModel()

    //val viewState by remember { viewModel.viewState }

    LazyRow(  horizontalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = PaddingValues(horizontal = 24.dp),
        modifier = Modifier){
        items(listOfProducts){ item ->
            SingleTaskItem(product = item){ product ->
                onItemClicked(product)
            }
        }
    }
}

@Composable
fun SingleTaskItem (
    product: Product,
    onclick : (String) -> Unit
){
    val image = product.listImageUrl[1]
    Card(modifier = Modifier
        .padding(8.dp)
        .width(125.dp)
        .clickable { onclick("") },
        elevation = 8.dp) {
        Column (modifier = Modifier.padding(16.dp)){

            Image(
                modifier = Modifier
                    .height(80.dp)
                    .fillMaxWidth(),
                painter = rememberAsyncImagePainter(model = image),
                contentDescription = null,
            contentScale = ContentScale.Fit)

            Text(text = product.name, fontSize = 14.sp)

            Text(text = product.wieght, fontSize = 10.sp)

            Text(text = product.price.toString(),
                fontSize = 16.sp,
                color = Red700)
        }
    }

}


@Preview(showBackground = true)
@Composable
fun preview(){
    ProductListScreen{}
    //SingleTaskItem2(product = product1){}
}