package com.berkaykurtoglu.worktracker.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.berkaykurtoglu.worktracker.presentation.mainscreen.MainScreen
import com.berkaykurtoglu.worktracker.presentation.signin.LogInScreen
import com.berkaykurtoglu.worktracker.presentation.theme.TaskManagerTheme
import com.berkaykurtoglu.worktracker.presentation.theme.md_theme_light_background
import com.berkaykurtoglu.worktracker.presentation.theme.md_theme_light_inverseOnSurface
import com.berkaykurtoglu.worktracker.presentation.theme.md_theme_light_inversePrimary
import com.berkaykurtoglu.worktracker.presentation.theme.md_theme_light_outlineVariant
import com.berkaykurtoglu.worktracker.presentation.theme.md_theme_light_primaryContainer
import com.berkaykurtoglu.worktracker.presentation.theme.md_theme_light_secondaryContainer
import com.berkaykurtoglu.worktracker.presentation.theme.md_theme_light_surface
import com.berkaykurtoglu.worktracker.presentation.theme.md_theme_light_surfaceVariant
import com.berkaykurtoglu.worktracker.presentation.theme.md_theme_light_tertiaryContainer
import com.berkaykurtoglu.worktracker.util.Constants
import com.berkaykurtoglu.worktracker.util.Screens
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            TaskManagerTheme {
                // A surface container using the 'background' color from the theme
                val systemController = rememberSystemUiController()


                SideEffect {
                    systemController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = true
                    )
                    systemController.setNavigationBarColor(
                        color = md_theme_light_surfaceVariant,
                        darkIcons = true
                    )
                }

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
    TaskManagerTheme {

    }
}