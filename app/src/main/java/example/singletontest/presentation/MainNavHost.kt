package example.singletontest.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import example.singletontest.domain.type.ScreenRouteType
import example.singletontest.presentation.screen_datastore.DataStoreScreen
import example.singletontest.presentation.screen_home.HomeScreen
import example.singletontest.presentation.screen_room.RoomScreen

@Composable
fun MainNavHost(
    navController: NavHostController,
    startDestination: ScreenRouteType
) = NavHost(
    navController = navController,
    startDestination = startDestination.route
) {
    homeScreen(navController = navController)
    roomScreen(navController = navController)
    dataStoreScreen(navController = navController)
}

private fun NavGraphBuilder.homeScreen(
    navController: NavController
) = composable(
    route = ScreenRouteType.Home.route
) {
    HomeScreen(
        navToRoomScreen = {
            navController.navigate(route = ScreenRouteType.Room.route) {
                launchSingleTop = true
            }
        },
        navToDataStoreScreen = {
            navController.navigate(route = ScreenRouteType.DataStore.route) {
                launchSingleTop = true
            }
        }
    )
}

private fun NavGraphBuilder.roomScreen(
    navController: NavController
) = composable(
    route = ScreenRouteType.Room.route
) {
    RoomScreen(
        navBackToHomeScreen = {
            navController.popBackStack()
        }
    )
}

private fun NavGraphBuilder.dataStoreScreen(
    navController: NavController
) = composable(
    route = ScreenRouteType.DataStore.route
) {
    DataStoreScreen(
        navBackToHomeScreen = {
            navController.popBackStack()
        }
    )
}