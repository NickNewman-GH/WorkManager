package com.example.workmanagerdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.workDataOf


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val citiesData = workDataOf("cities" to intArrayOf(123, 234, 456))
        val workRequest = OneTimeWorkRequest.Builder(WeatherWorker::class.java)
            .setInputData(citiesData)
            .build()

        WorkManager.getInstance(this).enqueue(workRequest)

        // TODO: реализовать запрос погоды
        // TODO: какие параметры позволяет задать WorkManager (макс число одновр заданий)
        // TODO: реализовать выполнение заданий по очереди

    }
}