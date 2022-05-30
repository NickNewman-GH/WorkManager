package com.example.workmanagerdemo

import android.content.Context
import android.util.Log
import android.widget.EditText
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.FileNotFoundException
import java.io.InputStream
import java.net.URL
import java.net.UnknownHostException
import java.util.*
import kotlin.collections.HashMap

class WeatherWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams)
{
    override fun doWork(): Result {
        val cities = inputData.getStringArray("cities")

        cities?.let {
            for (city in it) {
                GlobalScope.launch (Dispatchers.IO) {
                    loadWeather(city)
                }
                Log.d("mytag", "city $city")
            }
        }
        Log.d("mytag", "work success")
        return Result.success()
    }


    suspend fun loadWeather(city: String): String {
        val API_KEY = "d6843ab8ee963f5d372296dfff62aed7"
        //val editText = findViewById<EditText>(R.id.city)

        val weatherURL =
            "https://api.openweathermap.org/data/2.5/weather?q=$city&appid=$API_KEY&units=metric";
        try{
            val stream = URL(weatherURL).getContent() as InputStream
            val data = Scanner(stream).nextLine()
            Log.d("mytag", data)
            val jObject = JSONObject(data)
            val sky = jObject.getJSONArray("weather").getJSONObject(0)["main"].toString()
            val temp = jObject.getJSONObject("main")["temp"].toString() + " °C"
            //Log.d("mytag", sky)
            val wind = jObject.getJSONObject("wind")
            val windSpeed = wind["speed"].toString() + " m/s"
            return "Weather is $sky, temperature = $temp, wind speed = $windSpeed"
            //Log.d("mytag", wind.toString())
        } finally {

        }
    }
}

data class WeatherInfoFirst(val message:String = "")
data class WeatherInfoSecond(val message:String = "")
data class WeatherInfoThird(val message:String = "")