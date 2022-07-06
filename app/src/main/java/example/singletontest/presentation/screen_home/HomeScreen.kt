package example.singletontest.presentation.screen_home

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import example.singletontest.R
import example.singletontest.presentation.common.theme.spacing

@Composable
fun HomeScreen(
    navToRoomScreen: () -> Unit,
    navToDataStoreScreen: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ComposeNavButton(text = R.string.navigateToRoom) {
            navToRoomScreen()
        }

        ComposeNavButton(text = R.string.navigateToDataStore) {
            navToDataStoreScreen()
        }
    }
}

@Composable
private fun ComposeNavButton(
    @StringRes text: Int,
    onClick: () -> Unit
) = Button(
    modifier = Modifier
        .fillMaxWidth()
        .padding(
            horizontal = MaterialTheme.spacing.medium,
            vertical = MaterialTheme.spacing.none
        ),
    onClick = onClick
) {
    Text(text = stringResource(id = text))
}