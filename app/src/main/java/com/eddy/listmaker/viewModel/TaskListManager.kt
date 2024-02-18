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

    fun readLists(): ArrayList<TaskList> {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
        val contents = sharedPrefs.all
        val taskLists = ArrayList<TaskList>()
        for (taskList in contents) {
            val tasks = ArrayList(taskList.value as HashSet<String>)
            val list = TaskList(name = taskList.key, tasks = tasks)
            taskLists.add(list)
        }
        return taskLists
    }
}