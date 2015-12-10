package com.romansl.backgroundloadmanager

interface Task<in A> {
    fun run(args: A)
}

