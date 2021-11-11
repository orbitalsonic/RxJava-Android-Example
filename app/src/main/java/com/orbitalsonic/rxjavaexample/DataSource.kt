package com.orbitalsonic.rxjavaexample

object DataSource {

    fun getTaskData():List<Task>{
        val taskList:ArrayList<Task> = ArrayList()
        taskList.add(Task("Take out the trash",true,3))
        taskList.add(Task("Walk the dog",false,2))
        taskList.add(Task("Make my bed",true,1))
        taskList.add(Task("Unload the dishwasher",false,0))
        taskList.add(Task("Make Dinner",true,5))

        return taskList
    }
}