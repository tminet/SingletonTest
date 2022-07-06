package example.singletontest.presentation.screen_room

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.TaskAlt
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import example.singletontest.R
import example.singletontest.domain.model.Task
import example.singletontest.presentation.common.AppTopBarWithBack
import example.singletontest.presentation.common.theme.elevating
import example.singletontest.presentation.common.theme.spacing

@Composable
fun RoomScreen(
    viewModel: RoomViewModel = hiltViewModel(),
    navBackToHomeScreen: () -> Unit
) {
    val state = viewModel.state
    val focusManager = LocalFocusManager.current

    Column(modifier = Modifier.fillMaxSize()) {
        AppTopBarWithBack(title = R.string.screenTitleRoom) {
            navBackToHomeScreen()
        }

        ComposeLoading(isLoading = state.isLoading)

        ComposeEmptyMessage(isEmpty = state.tasks.isEmpty())

        ComposeTasksList(
            tasks = state.tasks,
            taskDescription = state.editableTaskDescription,
            onTaskDescriptionChanged = {
                viewModel.onAction(action = RoomAction.ChangeTaskDescription(description = it))
            },
            onSaveTaskClick = {
                focusManager.clearFocus()
                viewModel.onAction(action = RoomAction.SaveTask)
            },
            onFinishTaskClick = {
                viewModel.onAction(action = RoomAction.FinishTask(task = it))
            }
        )
    }
}

@Composable
private fun ComposeLoading(
    isLoading: Boolean
) = AnimatedVisibility(
    modifier = Modifier.fillMaxWidth(),
    visible = isLoading,
    enter = expandVertically(animationSpec = tween()),
    exit = shrinkVertically(animationSpec = tween())
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LinearProgressIndicator(modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(height = MaterialTheme.spacing.small))

        Text(
            text = stringResource(id = R.string.loading),
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.body2
        )

        Spacer(modifier = Modifier.height(height = MaterialTheme.spacing.medium))
    }
}

@Composable
private fun ComposeEmptyMessage(
    isEmpty: Boolean
) = AnimatedVisibility(
    modifier = Modifier.fillMaxWidth(),
    visible = isEmpty,
    enter = expandVertically(animationSpec = tween()),
    exit = shrinkVertically(animationSpec = tween())
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = MaterialTheme.spacing.medium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.emptyTasks),
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.h6
        )
    }
}

@Composable
private fun ComposeTasksList(
    tasks: List<Task>,
    taskDescription: String,
    onTaskDescriptionChanged: (String) -> Unit,
    onSaveTaskClick: () -> Unit,
    onFinishTaskClick: (Task) -> Unit
) = LazyColumn(
    modifier = Modifier.fillMaxWidth(),
    contentPadding = PaddingValues(MaterialTheme.spacing.medium),
    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
) {
    item {
        ComposeNewTask(
            description = taskDescription,
            onDescriptionChanged = { onTaskDescriptionChanged(it) },
            onSaveClick = onSaveTaskClick
        )
    }

    items(items = tasks, key = { it.createdAt }) { task ->
        ComposeTaskCard(task = task) {
            onFinishTaskClick(task)
        }
    }
}

@Composable
private fun ComposeNewTask(
    description: String,
    onDescriptionChanged: (String) -> Unit,
    onSaveClick: () -> Unit
) = Column(modifier = Modifier.fillMaxWidth()) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = Modifier.weight(weight = 1F),
            value = description,
            onValueChange = { onDescriptionChanged(it) },
            label = {
                Text(
                    text = stringResource(id = R.string.taskDescription),
                    color = MaterialTheme.colors.onBackground
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onSaveClick()
                }
            )
        )

        Spacer(modifier = Modifier.width(width = MaterialTheme.spacing.small))

        IconButton(onClick = onSaveClick) {
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = stringResource(id = R.string.saveTask),
                tint = MaterialTheme.colors.onBackground
            )
        }
    }

    Spacer(modifier = Modifier.height(height = MaterialTheme.spacing.small))

    Divider()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyItemScope.ComposeTaskCard(
    task: Task,
    onFinishClick: () -> Unit
) = Card(
    modifier = Modifier
        .fillMaxWidth()
        .animateItemPlacement(),
    shape = MaterialTheme.shapes.large,
    elevation = MaterialTheme.elevating.none,
    backgroundColor = MaterialTheme.colors.surface
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = MaterialTheme.spacing.small),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(start = MaterialTheme.spacing.extraSmall),
            text = task.description,
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold
        )

        IconButton(
            onClick = { onFinishClick() }
        ) {
            Icon(
                imageVector = Icons.Rounded.TaskAlt,
                contentDescription = stringResource(id = R.string.finishTask),
                tint = MaterialTheme.colors.onSurface
            )
        }
    }
}