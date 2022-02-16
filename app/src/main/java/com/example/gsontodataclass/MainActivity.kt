package com.example.gsontodataclass

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gsontodataclass.Adapter.RecyclerAdapter
import com.example.gsontodataclass.databinding.ActivityMainBinding
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import okhttp3.*
import java.io.IOException

class MainActivity() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        binding.recyclerViewMain.layoutManager = LinearLayoutManager(this)

        fetchJson()

    }

    fun fetchJson() {

        val url = "https://picsum.photos/v2/list?limit=20"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {

            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()

                val gson = GsonBuilder().create()
                val home: List<PicsumDataClass> = gson.fromJson(body, Array<PicsumDataClass>::class.java).toList()

                runOnUiThread {
                    val RecyclerView = findViewById<RecyclerView>(R.id.recyclerView_main)

                    RecyclerView.adapter =
                        RecyclerAdapter(home)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed To Execute Request")
            }
        })
    }
}

