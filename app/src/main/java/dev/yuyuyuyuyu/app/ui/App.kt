package dev.yuyuyuyuyu.app.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.yuyuyuyuyu.app.ui.opensourcelicenses.screens.OpenSourceLicensesScreen
import dev.yuyuyuyuyu.app.ui.theme.TopAppBarTheme
import dev.yuyuyuyuyu.app.ui.topappbarexample.screens.TopAppBarExampleScreen
import dev.yuyuyuyuyu.topappbar.TopAppBar

@Composable
fun App(
    navController: NavHostController = rememberNavController(),
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = NavigateDestination.valueOf(
        backStackEntry?.destination?.route ?: NavigateDestination.TopAppBarExample.name
    )

    TopAppBarTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = "TopAppBar Example",
                    navigateBackIsPossible = navController.previousBackStackEntry != null,
                    onNavigateBackButtonClick = {
                        navController.navigateUp()
                    },
                    onOpenSourceLicensesButtonClick = {
                        if (currentScreen != NavigateDestination.OpenSourceLicenses) {
                            navController.navigate(NavigateDestination.OpenSourceLicenses.name)
                        }
                    },
                )
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = NavigateDestination.TopAppBarExample.name,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            ) {
                composable(route = NavigateDestination.TopAppBarExample.name) {
                    TopAppBarExampleScreen()
                }
                composable(route = NavigateDestination.OpenSourceLicenses.name) {
                    OpenSourceLicensesScreen()
                }
            }
        }
    }
}
