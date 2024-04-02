package com.example.coroutinesex

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL


suspend fun main()
{
    val url = "http://example.com"

    val result = fetchUserData(url)

    if (result.startsWith("Error:"))
    {
        println("네트워크 요청 실패: $result")
    }
    else
    {
        println("네트워크 요청 성공:\n$result")
    }
}


suspend fun fetchUserData(url: String): String {
    return withContext(Dispatchers.IO){
        try {
            val connection = URL(url).openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            // 타임아웃 설정 (옵션)
            connection.connectTimeout = 10000 // 10초
            connection.readTimeout = 10000 // 10초

            // 요청이 성공적으로 수행되었는지 확인
            if (connection.responseCode == HttpURLConnection.HTTP_OK)
            {
                // 성공: 응답 바디를 문자열로 변환
                connection.inputStream.bufferedReader().use { it.readText() }
            }
            else
            {
                // 실패: 예외 발생
                throw IOException("HTTP error code: ${connection.responseCode}")
            }
        }
        catch (e: IOException)
        {
            // 네트워크 요청 중 발생한 오류 처리
            "Error: ${e.message}"
        }
    }
}