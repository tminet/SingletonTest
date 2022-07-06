package example.singletontest.presentation.screen_datastore

sealed class DataStoreAction {
    data class ChangeAppTheme(val darkMode: Boolean) : DataStoreAction()
}