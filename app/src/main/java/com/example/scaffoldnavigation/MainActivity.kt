package com.example.scaffoldnavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.scaffoldnavigation.ui.theme.ScaffoldNavigationTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScaffoldNavigationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScaffoldNavigation()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(title: String, navController: NavController){
    var expanded by remember { mutableStateOf(false) }
    TopAppBar(
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = { Text(title) },
        actions = {
            IconButton(onClick = {
                expanded = !expanded
            }
            ) {
                Icon(Icons.Filled.MoreVert, contentDescription = null )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {expanded = false }) {
                DropdownMenuItem(text = {
                    Text(text = "Info")
                }, onClick = { navController.navigate("info")})

                DropdownMenuItem(text = {
                    Text(text = "Setting")
                }, onClick = {navController.navigate("setting")})

            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTopBar(title: String, navController: NavController){
    TopAppBar(title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = null)

            }
        }
    )
}

@Composable
fun MainScreen(navController: NavController){
    Scaffold (
        topBar = { MainTopBar(title = "Assignment 6", navController)})
    {
            contentPending ->
        Column(modifier = Modifier.padding(contentPending).fillMaxSize().background(color = Color.White)) {
            Text(
                text = "HOME PAGE",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                textAlign = TextAlign.Center

            )
            Text(
                text = "Content For Home screen",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun InfoScreen(navController: NavController){
    Scaffold(
        topBar = {
            ScreenTopBar(
                title = "Info",
                navController = navController
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize().background(color = Color.Yellow)
        ) {
            Text(
                text = "INFO PAGE",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                textAlign = TextAlign.Center

            )
            Text(
                text = "Content For INFO screen",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun SettingsScreen(navController: NavController){
    Scaffold (
        topBar = { ScreenTopBar(title = "Screen", navController)})
    {
            contentPending ->
        Column(modifier = Modifier.padding(contentPending).fillMaxSize().background(color = Color.Cyan)) {
            Text(
                text = "SETTINGS PAGE",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                textAlign = TextAlign.Center

            )
            Text(
                text = "Content For SETTINGS screen",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}


@Composable
fun ScaffoldNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "Home"
    ){
        composable(route = "Home"){
            MainScreen(navController)
        }
        composable(route = "info"){
            InfoScreen(navController)
        }
        composable(route = "setting") {
            SettingsScreen(navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ScaffoldNavigationTheme {
        ScaffoldNavigation()
    }
}