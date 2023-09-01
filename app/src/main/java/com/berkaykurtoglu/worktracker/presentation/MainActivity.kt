package com.berkaykurtoglu.worktracker.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.berkaykurtoglu.worktracker.presentation.mainscreen.MainScreen
import com.berkaykurtoglu.worktracker.presentation.signin.screen.LogInScreen
import com.berkaykurtoglu.worktracker.presentation.theme.WorkTrackerTheme
import com.berkaykurtoglu.worktracker.util.Constants
import com.berkaykurtoglu.worktracker.util.Screens
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WorkTrackerTheme {
                // A surface container using the 'background' color from the theme


                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()

                    val currentUser = FirebaseAuth.getInstance().currentUser
                    val startRoot = if (currentUser != null) Screens.MainScreen.route+currentUser.email
                    else Screens.LoginScreen.route


                    NavHost(navController = navController, startDestination = startRoot){


                        composable(Screens.LoginScreen.route){
                            LogInScreen(navController)
                        }
                        composable(
                            Screens.MainScreen.route+"{${Constants.EMAIL}}",
                            arguments = listOf(
                              navArgument(Constants.EMAIL){
                                  type = NavType.StringType
                              }
                            )
                        ){

                            MainScreen(navController)

                        }

                    }

                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WorkTrackerTheme {



    }
}