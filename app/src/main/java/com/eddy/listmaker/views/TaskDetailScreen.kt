package com.eddy.listmaker.views

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.eddy.listmaker.R
import com.eddy.listmaker.data.TaskList
import com.eddy.listmaker.viewModel.TaskListManager

@Composable
fun TaskDetailScreen(
    taskListName: String?,
    onBackPressed: () -> Unit
) {
    val context = LocalContext.current
    val viewModel = TaskListManager(context)

    var taskTodos by remember {
        mutableStateOf(viewModel.readList(taskListName ?: "") ?: emptyList())
    }

    Scaffold(topBar = {
        ListMakerTopAppBar(
            title = taskListName ?: stringResource(id = R.string.label_detail),
            showBackButton = true,
            onBackPressed = onBackPressed
        )
    },
        content = {
            TaskDetailScreenContent(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                tasks = taskTodos
            )
        },
        floatingActionButton = {
            ListMakerFloatingActionButton(
                title = stringResource(id = R.string.task_to_add),
                inputHint = stringResource(id = R.string.task_hint),
                onFabPressed = { todoName ->
                    val newTaskTodos = taskTodos + listOf(todoName)
                    viewModel.save(TaskList(taskListName ?: "default", newTaskTodos))
                    taskTodos = viewModel.readList(taskListName ?: "default") ?: emptyList()
                }
            )
        }
    )
}

@Composable
fun TaskDetailScreenContent(
    modifier: Modifier,
    tasks: List<String>,
    ) {
    LazyColumn(content = {
        items(tasks) {
            ListItemView(value = it, onClick = {})
        }
    })
}