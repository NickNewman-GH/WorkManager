package com.example.workmanagerdemo

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.workDataOf


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val cities = arrayOf("Irkutsk", "Moscow", "Omsk")
        findViewById<TextView>(R.id.city1).text = cities[0]
        findViewById<TextView>(R.id.city2).text = cities[1]
        findViewById<TextView>(R.id.city3).text = cities[2]

        findViewById<Button>(R.id.getWeather).setOnClickListener() {
            val citiesData = workDataOf("cities" to cities)
            val workRequest = OneTimeWorkRequest.Builder(WeatherWorker::class.java)
                .setInputData(citiesData)
                .build()

            WorkManager.getInstance(this).enqueue(workRequest)
        }

        // TODO: реализовать запрос погоды
        // TODO: какие параметры позволяет задать WorkManager (макс число одновр заданий)
        // TODO: реализовать выполнение заданий по очереди
    }
}