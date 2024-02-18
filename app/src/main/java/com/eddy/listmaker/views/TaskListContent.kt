package com.eddy.listmaker.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.AndroidViewModel
import com.eddy.listmaker.R
import com.eddy.listmaker.data.TaskList
import com.eddy.listmaker.viewModel.TaskListViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TaskListScreen(
    viewModel: TaskListViewModel = viewModel()
) {
    val tasks = viewModel.taskListState.collectAsState().value
    Scaffold(
        topBar = {
            ListMakerTopAppBar(
                title = stringResource(id = R.string.label_list_maker),
                showBackButton = false,
                onBackPressed = {}
            )
        },
        content = {
            if (tasks.isEmpty()) {
                EmptyView(stringResource(id = R.string.no_task_in_list))
            } else {
                TaskListContent(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize(),
                    tasks = tasks,
                    onClick = { taskName ->
                        println(taskName)
                    })
            }
        },
        floatingActionButton = {
            ListMakerFloatingActionButton(
                title = stringResource(id = R.string.name_of_list),
                inputHint = stringResource(id = R.string.task_hint),
                onFabPressed = {
                    println(it)
                }
            )
        }
    )
}

@Composable
fun TaskListContent(
    modifier: Modifier,
    tasks: List<TaskList>,
    onClick: (String) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        content = {
            items(tasks) {
                ListItemView(value = it.name, onClick = onClick)
            }
        }
    )
}