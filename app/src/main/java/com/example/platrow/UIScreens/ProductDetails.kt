package com.example.extendsdailytasks

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.example.platrow.R
import com.example.platrow.model.Product
import com.example.platrow.ui.theme.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProductDetailView(product: Product, modifier: Modifier, onClick : () -> Unit){

    val product = Product(1,
        listOf(
            "https:\\/\\/www.themealdb.com\\/images\\/category\\/beef.png",
            "https:\\/\\/www.themealdb.com\\/images\\/category\\/chicken.png",
            "https:\\/\\/www.themealdb.com\\/images\\/category\\/dessert.png",
            "https:\\/\\/www.themealdb.com\\/images\\/category\\/lamb.png"),
        "Kitkate Choclate Flavored",
        "122", 30.00, "400", "Choclate")

    var checked by remember {
        mutableStateOf(false)
    }

        Column(modifier = Modifier.fillMaxHeight().background(Color.White)) {
            Image(painter =    if (checked)
                painterResource(id = R.drawable.red_heart)
            else painterResource(id =R.drawable.ic_dimmed_heart),
                contentDescription = "Share",modifier =
                Modifier
                    .align(Alignment.End)
                    .padding(top = 12.dp, end = 24.dp)
                    .width(24.dp)
                    .height(32.dp)
                    .clickable {
                        checked = !checked
                    })

                ImageSlider(product = product)


//            Image(painter = painterResource(id = R.drawable.kitkat),
//                contentDescription =null, modifier = Modifier.size(380.dp),)
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = product.name, fontSize = 18.sp,
                modifier = Modifier.padding(start = 24.dp, top = 18.dp))
                Text(text = "- ${product.wieght} x 12", fontSize = 18.sp,
                    modifier = Modifier.padding(top = 18.dp, start = 4.dp,end = 24.dp))
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = product.calories, fontSize = 12.sp,
                    modifier = Modifier.padding(start = 24.dp, top = 12.dp),
                color = TextGrayColor)
                Text(text = "  SR per unit", fontSize = 12.sp,
                    modifier = Modifier.padding(top = 12.dp),
                color = TextGrayColor)
            }
            Text(text = product.type, fontSize = 18.sp,
                modifier = Modifier.padding(start = 24.dp, top = 12.dp), color = dividerColor)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement =
            Arrangement.SpaceBetween) {

                Text(text = product.price.toString(), fontSize = 24.sp,
                    modifier = Modifier.padding(start = 26.dp, top = 12.dp),
                    color = Color.Black)

                Text(text = "Including VAT", fontSize = 16.sp,
                    modifier = Modifier.padding(end = 24.dp, top = 12.dp),
                    color = dividerColor)
            }
        }

    }

@Preview(showBackground = true)
@Composable
fun ProductDetailsPreview() {
    PlatrowTheme() {
        val product1 = Product(1,
    listOf("https:\\/\\/www.themealdb.com\\/images\\/category\\/beef.png",
        "https:\\/\\/www.themealdb.com\\/images\\/category\\/beef.png",
        "https:\\/\\/www.themealdb.com\\/images\\/category\\/chicken.png",
        "https:\\/\\/www.themealdb.com\\/images\\/category\\/dessert.png",
        "https:\\/\\/www.themealdb.com\\/images\\/category\\/lamb.png"),
    "Kikate CHoclate Flavored",
            "122", 30.00, "400", "Choclate")
    ProductDetailView(product = product1, modifier = Modifier) {}
        }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SliderView(state: PagerState, product: Product) {

//    Column(modifier = Modifier
//        .height(380.dp)
//        .fillMaxWidth()) {

        val imageUrl =
            remember { mutableStateOf("") }
        HorizontalPager(
            state = state,
            count = product.listImageUrl.count(), modifier = Modifier
                .height(360.dp)
                .fillMaxWidth()
        ) { page ->
            imageUrl.value = product.listImageUrl[page]

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(contentAlignment = Alignment.BottomCenter) {

                    val painter = rememberImagePainter(data = imageUrl.value, builder = {
                        placeholder(R.drawable.placeholder)
                        scale(Scale.FILL)
                    })
                    Image(
                        painter = rememberAsyncImagePainter(model = product.listImageUrl[page]), contentDescription = "",
                        Modifier
                            .padding(8.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .fillMaxSize(), contentScale = ContentScale.Crop
                    )
                }
            }

        }
        //DotsIndicator(totalDots = product.listImageUrl.size, selectedIndex = state.currentPage )
//    }

}


@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int
) {

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(), horizontalArrangement = Arrangement.Center
    ) {

        items(totalDots) { index ->
            if (index == selectedIndex) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(color = BtnColor)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(color = Color.Gray)
                )
            }

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}
@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImageSlider(product: Product) {
    Surface(
        modifier = Modifier.height(380.dp),
        color = MaterialTheme.colors.background
    ) {


        val state = rememberPagerState()
        Column (modifier = Modifier.height(380.dp)){
            SliderView(state, product)
            Spacer(modifier = Modifier.padding(4.dp))

            DotsIndicator(
                totalDots = product.listImageUrl.size,
                selectedIndex = state.currentPage
            )

        }
        LaunchedEffect(key1 = state.currentPage) {
            delay(3000)
            var newPosition = state.currentPage + 1
            if (newPosition > product.listImageUrl.size - 1) newPosition = 0
            // scrolling to the new position.
            state.animateScrollToPage(newPosition)
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Preview(showSystemUi = true)
@Composable
fun SliderPreview() {
//    val product = Product(1,
//        listOf("https:\\/\\/www.themealdb.com\\/images\\/category\\/beef.png",
//            "https:\\/\\/www.themealdb.com\\/images\\/category\\/beef.png",
//            "https:\\/\\/www.themealdb.com\\/images\\/category\\/chicken.png",
//            "https:\\/\\/www.themealdb.com\\/images\\/category\\/dessert.png",
//            "https:\\/\\/www.themealdb.com\\/images\\/category\\/lamb.png"),
//        "Kikate CHoclate Flavored",
//        "122", "300", "400", "Choclate")
//    Column {
//        SliderView(state = rememberPagerState(), product)
//        DotsIndicator(
//            totalDots = 5,
//            selectedIndex = 1
//        )
//    }

}

