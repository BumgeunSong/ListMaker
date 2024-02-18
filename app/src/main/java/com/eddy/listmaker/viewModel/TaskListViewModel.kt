package com.eddy.listmaker.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.preference.PreferenceManager
import com.eddy.listmaker.data.TaskList

class TaskListViewModel(val application: Application) : AndroidViewModel(application) {

    fun save(list: TaskList) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(application).edit()
        sharedPreferences.putStringSet(list.name, list.tasks.toHashSet())
        sharedPreferences.apply()
    }

    fun readList(): ArrayList<TaskList> {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(application)
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