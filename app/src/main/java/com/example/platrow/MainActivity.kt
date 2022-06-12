package com.example.platrow

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.extendsdailytasks.ProductDetailView
import com.example.extendtaskfoodapp.navigation.Screen
import com.example.platrow.model.Product
import com.example.platrow.ui.theme.PlatrowTheme
import com.example.platrow.ui.theme.dividerColor
import com.example.platrow.ui.theme.textColor
import com.ramcosta.composedestinations.DestinationsNavHost
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lateinit var navController: NavHostController

        setContent {
            navController = rememberNavController()
            PlatrowTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //SetupNavGraph(navController)

                    DestinationsNavHost(navGraph = NavGraphs.root)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PlatrowTheme {
        //ProductFullScreen()
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StartModelBottomSheet( scope: CoroutineScope, modelBottomSheetState: ModalBottomSheetState){
    ModalBottomSheetLayout(
        sheetContent = {
            BottomSheetContent(scope, modelBottomSheetState)
        },
        sheetState = modelBottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
        sheetBackgroundColor = colorResource(id = R.color.white),
        // scrimColor = Color.Red,  // Color for the fade background when you open/close the drawer
    ) {
        Scaffold(
            topBar = { TopBar() },
            backgroundColor = colorResource(id = R.color.white)
        ) {
            MainScreen(scope = scope, state = modelBottomSheetState)
        }
    }
}

@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name),
            fontSize = 18.sp) },
        backgroundColor = colorResource(id = R.color.purple_700),
        contentColor = Color.White
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(scope: CoroutineScope, state: ModalBottomSheetState) {
    Column(
        Modifier
            .fillMaxSize()
            .background(colorResource(R.color.white)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.white),
                contentColor = Color.White
            ),
            onClick = {
                scope.launch {
                    state.show()
                }
            }) {
            Text(text = "Open Modal Bottom Sheet Layout")
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    val modalBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    MainScreen(scope = scope, state = modalBottomSheetState)
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetContent(scope: CoroutineScope, state: ModalBottomSheetState) {
    val context = LocalContext.current
    Column(modifier = Modifier) {

        Text(text = "Sort By", modifier =
        Modifier
            .padding(start = 24.dp, top = 24.dp, bottom = 24.dp)
            .align(Alignment.Start)
            .width(57.dp)
            .height(20.dp))
        Divider(color = dividerColor, thickness = 1.dp,
            modifier = Modifier.padding(start = 23.dp))
        BottomSheetListItem(
            icon = R.drawable.ic_u_subject,
            title = "Share",
            onItemClick = { title ->
                Toast.makeText(
                    context,
                    title,
                    Toast.LENGTH_SHORT
                ).show()
            })
        Divider(color = dividerColor, thickness = 1.dp,
            modifier = Modifier.padding(start = 23.dp))
        BottomSheetListItem(
            icon = R.drawable.ic_u_sort_amount_down,
            title = "Price: Low to High",
            onItemClick = { title ->
                Toast.makeText(
                    context,
                    title,
                    Toast.LENGTH_SHORT
                ).show()
            })
        Divider(color = dividerColor, thickness = 1.dp,
            modifier = Modifier.padding(start = 23.dp))
        BottomSheetListItem(
            icon = R.drawable.ic_u_sort_amount_up,
            title = "Price: High to Low",
            onItemClick = { title ->
                Toast.makeText(
                    context,
                    title,
                    Toast.LENGTH_SHORT
                ).show()
            })
        Divider(color = dividerColor, thickness = 1.dp,
            modifier = Modifier.padding(start = 23.dp))

        Box(modifier = Modifier
            .width(140.dp)
            .height(48.dp)
            .align(Alignment.CenterHorizontally)
            .clickable {
                scope.launch { state.hide() }
            }) {
            Text(text = "Cancel", modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp),
                fontSize = 14.sp, color = textColor,
            )
        }
        Divider(color = dividerColor, thickness = 4.dp,
            modifier = Modifier
                .padding(top = 4.dp, bottom = 18.dp)
                .align(Alignment.CenterHorizontally)
                .width(34.dp)
                .clip(RoundedCornerShape(8.dp)))

    }
}

@Preview(showBackground = true)
@Composable
fun BottomSheetContentPreview() {
    //BottomSheetContent()
}

@Composable
fun BottomSheetListItem(icon: Int, title: String,
                        onItemClick: (String) -> Unit) {
    var checked by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = {
                onItemClick(title)
                checked = !checked
            })
            .height(55.dp)
            .padding(start = 15.dp),
    ) {
        Image(painter = painterResource(id = icon),
            contentDescription = "Share",modifier =
            Modifier.align(Alignment.CenterStart))
        Spacer(modifier = Modifier.width(20.dp))
        androidx.compose.material.Text(text = title, color = Color.Black,
            modifier = Modifier
                .padding(start = 45.dp)
                .align(Alignment.CenterStart))
        Image(painter =
        if (checked) {
            painterResource(id = R.drawable.ic_group_60421)
        }
        else {
            painterResource(id =R.drawable.ic_group_60421_2)
        }
            ,
            contentDescription = "Share", modifier =
            Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun BottomSheetListItemPreview() {
    BottomSheetListItem(icon = R.drawable.ic_u_subject,
        title = "Share", onItemClick = { })
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {

}