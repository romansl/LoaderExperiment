package com.romansl.backgroundloadmanager

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
}
