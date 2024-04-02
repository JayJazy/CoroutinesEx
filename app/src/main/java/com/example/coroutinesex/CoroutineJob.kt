package com.example.coroutinesex

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main()
{
    //job1()

    job2()
}


suspend fun job1()
{
    val myJob = Job()
    val myScope = CoroutineScope(Dispatchers.IO + myJob)

    myScope.launch {
        delay(1000)
        println("job is executing")
    }.join()

    println("job completed")
}


suspend fun job2()
{
    val job = CoroutineScope(Dispatchers.IO).launch {
        delay(1000)
        println("job is executing")
    }

    job.join()

    println("job completed")
}

