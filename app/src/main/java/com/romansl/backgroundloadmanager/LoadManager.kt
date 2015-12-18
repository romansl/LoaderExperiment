package com.romansl.backgroundloadmanager

import android.os.Bundle
import android.support.v4.util.SparseArrayCompat

class Completed(result: Any)

class TaskFuture<A> {
    var isRunning: Boolean = false
        private set

    fun run(args: A) {

    }

    fun cancel() {

    }
}

class LoadManager {
    fun onStart() {

    }

    fun onStop() {

    }

    fun <A> register(clazz: Class<out Task<A>>, onCompleted: Completed.() -> Unit): TaskFuture<A> {
        return TaskFuture()
    }

    fun onSave(outState: Bundle) {
        val saveId = save(this)
        outState.putInt(EXTRA_SAVE_ID, saveId)
    }

    companion object {
        private const val EXTRA_SAVE_ID = "com.romansl.saveId"
        private val managers = SparseArrayCompat<LoadManager>()
        private var id = 0

        fun create(savedInstanceState: Bundle?): LoadManager {
            return if (savedInstanceState == null) {
                LoadManager()
            } else {
                val id = savedInstanceState.getInt(EXTRA_SAVE_ID, -1)
                if (id < 0) {
                    LoadManager()
                } else {
                    managers.removeOrCreate(id) {
                        LoadManager()
                    }
                }
            }
        }

        private fun save(loadManager: LoadManager): Int {
            val newId = id++
            managers.append(newId, loadManager)
            return newId
        }
    }
}

