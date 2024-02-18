package com.eddy.listmaker.viewModel

import android.content.Context
import androidx.preference.PreferenceManager
import com.eddy.listmaker.data.TaskList

class TaskListManager(val context: Context) {
    fun save(list: TaskList) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context).edit()
        sharedPreferences.putStringSet(list.name, list.tasks.toHashSet())
        sharedPreferences.apply()
    }
}