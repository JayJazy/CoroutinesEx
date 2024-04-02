package com.example.coroutinesex

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main()
{
   async()
}


fun async() = runBlocking {

    val deffered : Deferred<Int> = async{
        var num = 0
        for(i in 0..3){
            num += i
            delay(1000)
        }
        num
    }

    val result = deffered.await()
    println(result)
}