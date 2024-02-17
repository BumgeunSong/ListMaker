package com.eddy.listmaker.views

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.eddy.listmaker.data.TaskList

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