package example.singletontest.domain.type

sealed class ScreenRouteType(val route: String) {
    object Home : ScreenRouteType(route = "home_screen")
    object Room : ScreenRouteType(route = "room_screen")
    object DataStore : ScreenRouteType(route = "datastore_screen")
}